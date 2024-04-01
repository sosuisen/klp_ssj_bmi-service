package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BmiDTO {
	private String cmHeight;
	private String kgWeight;
	private String bmi;
}
