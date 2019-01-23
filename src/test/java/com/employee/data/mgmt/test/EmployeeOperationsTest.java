package com.employee.data.mgmt.test;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.employee.data.mgmt.controller.EmpOpsURIConstants;
import com.employee.data.mgmt.model.EmployeeBean;

public class EmployeeOperationsTest {

	public static final String SERVER_URI = "http://localhost:8080/EmployeeDataMgmtOps";
	
	public static void main(String args[]){
		System.out.println("*****");
		testCreateEmployee();
		System.out.println("*****");
		testGetEmployee();
		System.out.println("*****");
		testGetAllEmployee();
	}

	private static void testGetAllEmployee() {
		RestTemplate restTemplate = new RestTemplate();
		List<LinkedHashMap> emps = restTemplate.getForObject(SERVER_URI+EmpOpsURIConstants.GET_ALL_EMP, List.class);
		System.out.println(emps.size());
		for(LinkedHashMap map : emps){
			System.out.println("ID="+map.get("id")+",Name="+map.get("name")+",CreatedDate="+map.get("createdDate"));;
		}
	}

	private static void testCreateEmployee() {
		RestTemplate restTemplate = new RestTemplate();
		EmployeeBean emp = new EmployeeBean();
		emp.setEmpId(1);emp.setEmpName("Pankaj Kumar");
		EmployeeBean response = restTemplate.postForObject(SERVER_URI+EmpOpsURIConstants.CREATE_EMP, emp, EmployeeBean.class);
		printEmpData(response);
	}

	private static void testGetEmployee() {
		RestTemplate restTemplate = new RestTemplate();
		EmployeeBean emp = restTemplate.getForObject(SERVER_URI+"/rest/emp/1", EmployeeBean.class);
		printEmpData(emp);
	}


	
	public static void printEmpData(EmployeeBean emp){
		System.out.println("ID="+emp.getEmpId()+",Name="+emp.getEmpName()+",CreatedDate="+emp.getCreationDate());
	}
}
