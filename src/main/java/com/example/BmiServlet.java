package com.example;

import java.io.IOException;
import java.util.List;

import com.example.model.BmiManager;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Servlet implementation class BmiServlet
 */
public class BmiServlet extends HttpServlet {
	private static final BmiManager model = new BmiManager();

	// BMIに関する値をJSPへ渡すためだけに用いる、Lombokで作成したDTOクラスです。
	// (Jakarta 10まで）
	// ここは、JakartaEE 11以降ではLombokの代わりにRecordクラスを用いましょう。
	@Getter
	@AllArgsConstructor
	public static class BmiDTO {
		private String height;
		private String weight;
		private String bmi;
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Modelから過去の結果を取得します。
		var bmiList = model.getBmiList();

		// Modelから受け取ったデータをViewで表示しやすいよう加工するのは、Controllerの役割です。
		// mをcmに変換して、小数点以下は１桁までとして、順序を新しい順にします。	
		List<BmiDTO> history = bmiList.stream().map(bmi -> 
			new BmiDTO(String.format("%.1f", bmi.getMHeight() * 100.0),
				String.format("%.1f", bmi.getKgWeight()),
				String.format("%.1f", bmi.getBmi()))
			).toList().reversed();
		
		// データをViewに渡すため、リクエストスコープへセットします。
		request.setAttribute("history", history);
		request.getRequestDispatcher("/WEB-INF/bmi.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		// Webブラウザから送られてきたデータを取得します。
		var cmHeight = Double.valueOf(request.getParameter("cmHeight"));
		var kgWeight = Double.valueOf(request.getParameter("kgWeight"));
		
		var mHeight = cmHeight / 100.0;

		// ModelのBMI計算機能を呼び出します。
		var bmi = model.calc(mHeight, kgWeight);

		// 入力と計算結果を表示するため、リクエストスコープにセットします。
		var current = new BmiDTO(String.valueOf(cmHeight), String.valueOf(kgWeight), String.format("%.1f", bmi));
		request.setAttribute("current", current);
		
		// この後の処理はdoGetメソッドと同じなので、doGetに任せます。
		doGet(request, response);
	}
}
