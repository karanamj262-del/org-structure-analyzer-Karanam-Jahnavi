package com.company.util;

import java.util.Map;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.company.model.Employee;

@SpringBootApplication
public class AnalyzerApplication {

	public static void main(String[] args) {

				Map<Integer, Employee> employees = CSVReader.readEmployees();

				EmployeeService service = new EmployeeService(employees);

				System.out.println("Salary Analysis");

				service.analyzeSalaries();

				System.out.println("Reporting Line Analysis");

				service.analyzeReportingLines();
	}
}
