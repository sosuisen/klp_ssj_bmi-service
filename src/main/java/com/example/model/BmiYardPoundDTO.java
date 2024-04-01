package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BmiYardPoundDTO {
	private String feetHeight;
	private String poundsWeight;
	private String bmi;
	private String date;
}
