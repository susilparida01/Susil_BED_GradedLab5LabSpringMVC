package com.gl.employeeManagementSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.gl.employeeManagementSystem.entity.Employee;
import com.gl.employeeManagementSystem.service.EmployeeService;

@Controller
public class EmployeeController {
	
	private EmployeeService employeeService;
	
	//@autowired annotaion on constructor initializing employee service
	
	@Autowired
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	
	@GetMapping("/employees")
	public String listEmployees(Model model) {
//		model.addAttribute("message","Hello this is sent from java code from controller");
		 model.addAttribute("employees",employeeService.getAllEmployees());
		return "employees";
	}
	
	@GetMapping("/employees/new")
	public String createEmployeeForm(Model model) {
		Employee employee = new Employee();
		model.addAttribute("employee",employee);
		return "create_employee";
	}
	
	@PostMapping("/employees")
	public String saveEmployee(@ModelAttribute("employee") Employee employee) {
		employeeService.saveEmployee(employee);
		return "redirect:/employees";
	}
	
	@GetMapping("/employees/edit/{id}")
	public String updateEmployeeForm(@PathVariable Long id,  Model model) {
		model.addAttribute("employee",employeeService.getEmployeeById(id));
		return "edit_employee";
	}
	
	@PostMapping("/employees/{id}")
	public String updateEmployee(@PathVariable Long id, @ModelAttribute("employee") Employee employee) {

		// get employee from database by id
		// save updated employee object
		employeeService.updateEmployee(id, employee);
		return "redirect:/employees";
	}
	
	@GetMapping("/employees/{id}")
	public String deleteEmployee(@PathVariable Long id) {
		employeeService.deleteEmployeeById(id);
		return "redirect:/employees";
	}

}
