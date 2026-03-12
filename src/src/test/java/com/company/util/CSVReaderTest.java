package com.company.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;
import org.junit.jupiter.api.Test;
import com.company.model.Employee;

class CSVReaderTest {

    @Test
    void testReadEmployeesNotEmpty() {

        Map<Integer, Employee> employees = CSVReader.readEmployees();
        assertNotNull(employees);
        assertFalse(employees.isEmpty());
    }

    @Test
    void testTotalEmployeesCount() {

        Map<Integer, Employee> employees = CSVReader.readEmployees();
        assertEquals(5, employees.size());
    }

    @Test
    void testEmployeeFieldsCorrect() {

        Map<Integer, Employee> employees = CSVReader.readEmployees();

        Employee emp = employees.get(124);
        assertEquals("Martin", emp.getFirstName());
        assertEquals("Chekov", emp.getLastName());
        assertEquals(45000, emp.getSalary());
        assertEquals(123, emp.getManagerId());
    }

    @Test
    void testCEOHasNoManager() {

        Map<Integer, Employee> employees = CSVReader.readEmployees();
        Employee ceo = employees.get(123);
        assertNull(ceo.getManagerId());
    }

    @Test
    void testEmployeeExistsInMap() {

        Map<Integer, Employee> employees = CSVReader.readEmployees();
        assertTrue(employees.containsKey(305));
    }
}