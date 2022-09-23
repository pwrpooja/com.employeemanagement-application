package com.employee.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employee.entity.Emplyoee;
import com.employee.model.EmployeeDO;
import com.employee.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/findAllEmployee")
    public List<EmployeeDO> getAllEmployees() {
	return employeeService.getAllEmployees();
    }

    @RequestMapping("/findEmployee")
    public <T> ResponseEntity<T> getEmployee(@RequestParam(name = "id") Integer employeeId) {

	ResponseEntity<T> response = null;
	Emplyoee emplyoee = employeeService.getEmployee(employeeId);

	if (emplyoee != null) {
	    EmployeeDO employeeDO = new EmployeeDO();
	    employeeDO.setId(emplyoee.getId());
	    employeeDO.setName(emplyoee.getName());
	    employeeDO.setSalary(emplyoee.getSalry());
	    response = (ResponseEntity<T>) new ResponseEntity<EmployeeDO>(employeeDO, HttpStatus.OK);
	} else {
	    response = (ResponseEntity<T>) new ResponseEntity<String>("Employee Not Found", HttpStatus.OK);
	}

	return response;
    }
}
