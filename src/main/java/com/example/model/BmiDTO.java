package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BmiDTO {
	private double cmHeight;
	private double kgWeight;
	private double bmi;
}
