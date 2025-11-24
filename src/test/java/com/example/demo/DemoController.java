package com.example.demo;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;

/**
 * Integration Test for the StudentRepository functionality.
 * Uses the H2 in-memory database configured in src/test/resources/application.properties.
 */
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ControllerTest {

    // Inject the repositories required for data manipulation
    @Autowired
    private StudentRepository studentRepository; 
    
    @Autowired 
    private UniversityRepository universityRepository; 

    /**
     * Test case 1: Verifies that a Student can be saved successfully.
     * This test must run first to populate the database for the next test.
     */
    @Test
    @Order(1)
    void shouldSaveStudent() {
        // --- FIX FOR DATAINTEGRITYEXCEPTION ---
        // 1. Create and save the mandatory dependency (University) first
        University university = new University();
        university.setName("USTO-MB");
        universityRepository.save(university); 

        // 2. Create the student and link the saved university object
        Student student = new Student(); 
        student.setName("Charlie"); 
        student.setAddress("Algeria");
        student.setUniversity(university); 

        // 3. Save the student
        studentRepository.save(student);

        // Assertion: Check that exactly one record exists in the database
        assertThat(studentRepository.count()).isEqualTo(1);
    }

    /**
     * Test case 2: Verifies that all students can be retrieved.
     * This depends on the record saved in shouldSaveStudent().
     */
    @Test
    @Order(2)
    void shouldFindAllStudents() {
        // Fetch all students from the repository
        List<Student> students = studentRepository.findAll();

        // Assertion 1: Check that the list contains exactly one student
        assertThat(students).hasSize(1);
        
        // Assertion 2: Check that the retrieved student's name is correct
        assertThat(students.get(0).getName()).isEqualTo("Charlie");
    }
}