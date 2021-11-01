package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Department;
import com.example.demo.entity.Employee;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.EmployeeRepository;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@RequestMapping("/")
	public String index(Model model, 
			            @RequestParam(name = "department_id", required = false) Long department_id) {
		Employee employee = new Employee();
		Department department = null;
		if(department_id != null) {
			department = departmentRepository.findById(department_id).get();
			employee.setDepartment(department);
		}
		model.addAttribute("employee", employee);
		if(department == null) {
			model.addAttribute("employees", employeeRepository.findAll());
		} else {
			model.addAttribute("employees", employeeRepository.findByDepartment(department));
		}
		model.addAttribute("departments", departmentRepository.findAll());
		return "employee";
	}
	
	
	
}
