package com.tcm.Fruites;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.tcm.Fruites.application.WeeklyReportService;

@SpringBootApplication
public class FruitStoreTcmBfvApplication {

	public static void main(String[] args) {
		SpringApplication.run(FruitStoreTcmBfvApplication.class, args);
		new WeeklyReportService();
	}
}
