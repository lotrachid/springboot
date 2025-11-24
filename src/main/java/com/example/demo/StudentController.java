package com.example.demo; 
import com.example.demo.Student; 
import com.example.demo.StudentService; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.http.ResponseEntity; // Import for better HTTP response handling
import org.springframework.web.bind.annotation.*; 
 
import java.util.List; 
 
@RestController 
@RequestMapping("/student") 
public class StudentController { 
 
    @Autowired 
    private StudentService studentService; 
 
    @PostMapping("/add") 
    public String add(@RequestBody Student student) { 
    studentService.saveStudent(student);
        return "New student is added"; 
    } 
 
   
    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable int id, @RequestBody Student studentDetails) {
        Student updatedStudent = studentService.updateStudent(id, studentDetails);
        
        if (updatedStudent != null) {
            return ResponseEntity.ok("Student with ID " + id + " is updated");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
   
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        studentService.deleteStudent(id);
        return "Student with ID " + id + " is deleted";
    }
 
    @GetMapping("/getAll") 
    public List<Student> getAllStudents() { 
        return studentService.getAllStudents(); 
    } 
 
    @GetMapping("/getAllUniv") 
    public List<Object> getAllStudentsUniversity() { 
        return studentService.getAllStudentsUniversity(); 
    } 
 
    @GetMapping("/findStudUniv") 
    public List<Object> findStudentsByUniversity(@RequestParam String univName) { 
        return studentService.findStudentsByUniversity(univName); 
    } 

    @GetMapping("/getById/{id}")
public Student getStudentById(@PathVariable int id) {
    return studentService.getStudentByIdWithUniversity(id);
}
}