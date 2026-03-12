package com.company.util;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AnalyzerApplicationTests {

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

    @Test
    void testApplicationRuns() {

        AnalyzerApplication.main(new String[]{});

        String output = outputStream.toString();

        assertTrue(output.contains("Salary Analysis"));
        assertTrue(output.contains("Reporting Line Analysis"));
    }

    @Test
    void testSalaryAnalysisOutput() {

        AnalyzerApplication.main(new String[]{});

        String output = outputStream.toString();

        assertTrue(output.contains("Martin Chekov"));
    }

    @Test
    void testReportingLineSectionExists() {

        AnalyzerApplication.main(new String[]{});

        String output = outputStream.toString();

        assertTrue(output.contains("Reporting Line Analysis"));
    }
}