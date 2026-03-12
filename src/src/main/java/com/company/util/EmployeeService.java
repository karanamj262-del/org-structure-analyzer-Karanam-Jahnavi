package com.company.util;

import java.util.List;
import java.util.Map;

import com.company.model.Employee;

public class EmployeeService {

	private Map<Integer, Employee> employees;

	public EmployeeService(Map<Integer, Employee> employees) {
		this.employees = employees;
		buildHierarchy();
	}

	private void buildHierarchy() {

		for (Employee e : employees.values()) {

			if (e.getManagerId() != null) {

				Employee manager = employees.get(e.getManagerId());
				e.setManager(manager);
				manager.addSubordinate(e);
			}
		}
	}

	public void analyzeSalaries() {

		for (Employee manager : employees.values()) {

			List<Employee> subs = manager.getSubordinates();

			if (subs.isEmpty())
				continue;

			double avg = subs.stream().mapToDouble(Employee::getSalary).average().orElse(0);

			double min = avg * 1.2;
			double max = avg * 1.5;

			if (manager.getSalary() < min) {

				System.out.println(manager.getName() + " earns LESS by " + (min - manager.getSalary()));
			}

			if (manager.getSalary() > max) {

				System.out.println(manager.getName() + " earns MORE by " + (manager.getSalary() - max));
			}
		}
	}

	public void analyzeReportingLines() {

		for (Employee e : employees.values()) {

			int depth = 0;
			Employee current = e.getManager();

			while (current != null) {
				depth++;
				current = current.getManager();
			}

			if (depth > 4) {
				System.out.println(e.getName() + " reporting line too long by " + (depth - 4));
			}
		}
	}
}
