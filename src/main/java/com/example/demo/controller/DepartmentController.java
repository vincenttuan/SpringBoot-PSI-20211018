package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Department;
import com.example.demo.repository.DepartmentRepository;

@Controller
@RequestMapping("/department")
public class DepartmentController {
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@RequestMapping("/")
	public String index(Model model) {
		Department department = new Department();
		List<Department> departments = departmentRepository.findAll();
		model.addAttribute("department", department);
		model.addAttribute("departments", departments);
		return "department";
	}
	
}
