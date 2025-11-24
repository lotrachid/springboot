// ControllerTest.java
package com.example.demo;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat; // For fluent assertions [cite: 150, 186]

// Loads the full Spring context for testing [cite: 153, 180]
@SpringBootTest
// Ensures tests run in a defined order [cite: 154, 185]
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ControllerTest {

    // Injects the repository bean [cite: 156, 182]
    @Autowired
    private StudentRepository studentRepository; // [cite: 157]

    @Test
    @Order(1) // Runs first [cite: 159]
    void shouldSaveStudent() {
        Student student = new Student(); // [cite: 162, 163]
        student.setName("Charlie"); // [cite: 164]
        student.setAddress("Algeria"); // [cite: 165]
        
        studentRepository.save(student); // Saves to the H2 database [cite: 166]

        // Assert that exactly one record exists [cite: 167, 168]
        assertThat(studentRepository.count()).isEqualTo(1);
    }

    @Test
    @Order(2) // Runs second [cite: 170]
    void shouldFindAllStudents() {
        // Fetch all students [cite: 171, 172]
        List<Student> students = studentRepository.findAll();

        // Assertions [cite: 173]
        assertThat(students).hasSize(1);
        assertThat(students.get(0).getName()).isEqualTo("Charlie"); // [cite: 177, 178]
    }
}