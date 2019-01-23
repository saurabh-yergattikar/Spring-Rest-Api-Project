package com.employee.data.mgmt.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.employee.data.mgmt.model.EmployeeBean;

/**
 * Handles requests for the Employee service.
 */
@Controller
public class EmployeeOperationsController {
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeOperationsController.class);
	
	//Map to store employees, ideally we should use database
	Map<Integer, EmployeeBean> empData = new HashMap<Integer, EmployeeBean>();
	

	
	@RequestMapping(value = EmpOpsURIConstants.GET_EMP, method = RequestMethod.GET)
	public @ResponseBody EmployeeBean getEmployeeInfoForId(@PathVariable("id") int empId) {
		logger.info("Getting Employee Infor for ID="+empId);
		return empData.get(empId);
	}
	
	@RequestMapping(value = EmpOpsURIConstants.GET_ALL_EMP, method = RequestMethod.GET)
	public @ResponseBody List<EmployeeBean> getAllEmployeesInformation() {
		logger.info("Getting All Employees Information");
		List<EmployeeBean> emps = new ArrayList<EmployeeBean>();
		Set<Integer> empIdKeys = empData.keySet();
		for(Integer i : empIdKeys){
			emps.add(empData.get(i));
		}
		return emps;
	}
	
	@RequestMapping(value = EmpOpsURIConstants.CREATE_EMP, method = RequestMethod.POST)
	public @ResponseBody EmployeeBean createEmployee(@RequestBody EmployeeBean emp) {
		logger.info("Adding Employee Information.");
		emp.setCreationDate(new Date());
		empData.put(emp.getEmpId(), emp);
		return emp;
	}
	
	@RequestMapping(value = EmpOpsURIConstants.DELETE_EMP, method = RequestMethod.PUT)
	public @ResponseBody EmployeeBean deleteEmployee(@PathVariable("id") int empId) {
		logger.info("Deleting Employee Information");
		EmployeeBean emp = empData.get(empId);
		empData.remove(empId);
		return emp;
	}
	
}
