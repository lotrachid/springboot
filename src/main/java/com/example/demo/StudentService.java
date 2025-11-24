package com.example.demo; 
 
import com.example.demo.Student; 
import com.example.demo.StudentRepository; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service; 
import java.util.List;
import java.util.Optional; 
 
@Service 
public class StudentService { 
    @Autowired 
    private StudentRepository studentRepository; 
 
    public Student saveStudent(Student student) { 
       
    return studentRepository.save(student); 
    } 
 
    public List<Student> getAllStudents() { 
        return studentRepository.findAll(); 
    }
    
    
    public Student updateStudent(int id, Student updatedStudent) {
        
        Optional<Student> studentOptional = studentRepository.findById(id);
        
        if (studentOptional.isPresent()) {
            Student existingStudent = studentOptional.get();
           
            existingStudent.setName(updatedStudent.getName());
            existingStudent.setAddress(updatedStudent.getAddress());
            
            existingStudent.setUniversity(updatedStudent.getUniversity());
            
            
            return studentRepository.save(existingStudent);
        } else {
            
            return null; 
        }
    }

    
    public void deleteStudent(int id) {
        
        studentRepository.deleteById(id);
    }
 
    public List<Object> getAllStudentsUniversity() { 
        return studentRepository.getAllStudentsUniversity(); 
    } 
 
    public List<Object> findStudentsByUniversity(String univName) { 
        return studentRepository.findStudentsByUniversity(univName); 
    } 

    public Student getStudentByIdWithUniversity(int id) {
        return studentRepository.findById(id).orElse(null);
    }
}