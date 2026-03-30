package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.service.CalculatorManagement;

@Controller
public class Page {
	
	@Autowired
	CalculatorManagement calculatorManagement;
	
	@RequestMapping("/")
	public String home() {
		return "home";
	}
	
	@PostMapping("/calculate")
	public String calculate(@RequestParam("num1") double n1,
			@RequestParam("num2") double n2,
			@RequestParam("operation") String operation,
			Model model) {
		double result = 0;
		
		switch (operation) {
		case "add":
		result = calculatorManagement.add(n1, n2);
		break;
		case "sub":
			result = calculatorManagement.substract(n1, n2);
			break;
		case "mul":
			result = calculatorManagement.multiply(n1, n2);
			break;
		case "div":
			if (n2 != 0) {
				result = calculatorManagement.divide(n1, n2);
			} else {
				model.addAttribute("error", "Division by zero is not allowed.");
				model.addAttribute("num1", n1);
				model.addAttribute("num2", n2);
				return "result";
			}
			break;
			default:
				model.addAttribute("error", "Invalid operation");
				return "result";
		}
		
		model.addAttribute("num1", n1);
		model.addAttribute("num2", n2);
		model.addAttribute("operation", operation);
		model.addAttribute("result", result);
		return "result";
	}

}
