package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BmiDTO {
	private String height;
	private String weight;
	private String bmi;
	private String date;
}
