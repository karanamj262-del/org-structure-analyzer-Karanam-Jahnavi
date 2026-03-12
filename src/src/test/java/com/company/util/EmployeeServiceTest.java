package com.company.util;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.company.model.Employee;

class EmployeeServiceTest {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private PrintStream originalOut;

    @BeforeEach
    void setUp() {
        originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    private Map<Integer, Employee> createEmployees() {

        Employee ceo = new Employee(1, "CEO", "Boss", 100000, null);
        Employee manager = new Employee(2, "Manager", "One", 40000, 1);
        Employee emp1 = new Employee(3, "Emp", "A", 50000, 2);
        Employee emp2 = new Employee(4, "Emp", "B", 50000, 2);

        Map<Integer, Employee> map = new HashMap<>();

        map.put(1, ceo);
        map.put(2, manager);
        map.put(3, emp1);
        map.put(4, emp2);

        return map;
    }

    @Test
    void testManagerSalaryTooLow() {

        Map<Integer, Employee> map = createEmployees();
        EmployeeService service = new EmployeeService(map);
        service.analyzeSalaries();
        String output = outputStream.toString();

        assertTrue(output.contains("Manager One earns LESS"));
    }

    @Test
    void testReportingLineTooLong() {

        Map<Integer, Employee> map = new HashMap<>();

        Employee ceo = new Employee(1, "CEO", "Boss", 100000, null);
        Employee m1 = new Employee(2, "M1", "Test", 80000, 1);
        Employee m2 = new Employee(3, "M2", "Test", 70000, 2);
        Employee m3 = new Employee(4, "M3", "Test", 60000, 3);
        Employee m4 = new Employee(5, "M4", "Test", 50000, 4);
        Employee emp = new Employee(6, "Deep", "Employee", 30000, 5);

        map.put(1, ceo);
        map.put(2, m1);
        map.put(3, m2);
        map.put(4, m3);
        map.put(5, m4);
        map.put(6, emp);

        EmployeeService service = new EmployeeService(map);
        service.analyzeReportingLines();
        String output = outputStream.toString();
        assertTrue(output.contains("Deep Employee reporting line too long"));
    }

    @Test
    void testHierarchyCreation() {

        Map<Integer, Employee> map = createEmployees();
        EmployeeService service = new EmployeeService(map);
        Employee manager = map.get(2);

        assertTrue(manager.getSubordinates().size() == 2);
    }
}