package com.example;

import java.io.IOException;
import java.util.List;

import com.example.model.BmiDTO;
import com.example.model.BmiManager;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BmiServlet
 */
public class BmiServlet extends HttpServlet {
	private static final BmiManager model = new BmiManager();

	private final double METER_FEET = 3.2808;
	private final double KG_POUNDS = 2.2046;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Modelから過去の結果を取得します。
		var bmiList = model.getBmiList();

		// Modelから受け取ったデータをViewで表示しやすいよう加工するのは、Controllerの役割です。
		// mをcmに変換して、順序を新しい順にします。
		List<BmiDTO> bmiDTOList = bmiList.stream().map(bmi -> 
			new BmiDTO(String.format("%.1f", bmi.getMHeight() * METER_FEET),
					String.format("%.1f", bmi.getKgWeight() * KG_POUNDS),
					String.format("%.1f", bmi.getBmi()),
					bmi.getCreatedDate().toString().substring(0, 10))
		).toList().reversed();
		
		// データをViewに渡すため、リクエストスコープへセットします。
		request.setAttribute("bmiDTOList", bmiDTOList);
		request.getRequestDispatcher("/WEB-INF/bmi.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		// Webブラウザから送られてきたデータを取得します。
		var feetHeight = Double.valueOf(request.getParameter("feetHeight"));
		var poundsWeight = Double.valueOf(request.getParameter("poundsWeight"));
		
		var mHeight = feetHeight / METER_FEET;
		var kgWeight = poundsWeight / KG_POUNDS;

		// ModelのBMI計算機能を呼び出します。
		var bmi = model.calc(mHeight, kgWeight);
		
		// 計算結果をリクエストスコープにセットします。
		request.setAttribute("bmi", String.format("%.1f", bmi));
		// 受け取った値も引き続き表示するためリクエストスコープにセットします。
		request.setAttribute("feetHeight", feetHeight);
		request.setAttribute("poundsWeight", poundsWeight);
		
		// この後の処理はdoGetメソッドと同じなので、doGetに任せます。
		doGet(request, response);
	}
}
