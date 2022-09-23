package com.employee.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.swing.text.LabelView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.entity.Emplyoee;
import com.employee.model.EmployeeDO;
import com.employee.repository.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Emplyoee getEmployee(Integer id) {
	Optional<Emplyoee> emplyoee = null;
	try {
	    emplyoee = employeeRepository.findById(id);
	} catch (Exception e) {
	    e.printStackTrace();
	    return null;
	}
	return emplyoee.isPresent() ? emplyoee.get() : null;

    }

    public List<EmployeeDO> getAllEmployees() {
	List<Emplyoee> emplyoees =  employeeRepository.findAll();
	List<EmployeeDO> dos = new ArrayList<EmployeeDO>();
	
	for (Emplyoee emplyoee : emplyoees) {
	    EmployeeDO employeeDO = new EmployeeDO();
	    employeeDO.setId(emplyoee.getId());
	    employeeDO.setName(emplyoee.getName());
	    employeeDO.setSalary(emplyoee.getSalry());
	    employeeDO.setCurrency("Rs");
	    
	    dos.add(employeeDO);
	}
	return dos;

    }

}
