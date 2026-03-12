package com.company.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import com.company.model.Employee;

public class CSVReader {

	public static Map<Integer, Employee> readEmployees() {

		Map<Integer, Employee> employees = new HashMap<>();

		try {

			InputStream input = CSVReader.class.getClassLoader().getResourceAsStream("employees.csv");

			if (input == null) {
				throw new RuntimeException("employees.csv file not found in resources folder");
			}

			try (BufferedReader br = new BufferedReader(new InputStreamReader(input))) {

				String line;

				br.readLine();

				while ((line = br.readLine()) != null) {

					String[] data = line.split(",");

					int id = Integer.parseInt(data[0]);
					String firstName = data[1];
					String lastName = data[2];
					double salary = Double.parseDouble(data[3]);

					Integer managerId = null;

					if (data.length > 4 && !data[4].isEmpty()) {
						managerId = Integer.parseInt(data[4]);
					}

					Employee emp = new Employee(id, firstName, lastName, salary, managerId);

					employees.put(id, emp);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return employees;
	}
}