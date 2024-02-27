package com.patel.elearning.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patel.elearning.entity.Student;
import com.patel.elearning.service.studentService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/students")
public class StudentController {
	
	private final studentService service;

	@GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(service.getAllStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Student>> getStudentById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getStudentById(id));
    }
    
    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Student>> getStudentsOfCourse(@PathVariable("courseId") Long courseId) {
    	return ResponseEntity.ok(service.getCourseStudents(courseId));
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        return ResponseEntity.ok(service.addStudent(student));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable("id") Long id, @RequestBody Student studentDetails) {
    	return ResponseEntity.ok(service.updateStudent(id, studentDetails));
    }
    
    @PutMapping("{studentId}/selectedCourse/{courseId}")
    public ResponseEntity<Student> addCourse(@PathVariable("studentId") Long studentId, 
    		@PathVariable("courseId") Long courseId) {
    	Student newstudent = service.addCourse(studentId, courseId);
    	
    	if(newstudent == null) {
    		return ResponseEntity.badRequest().build();
    	} 
    	
    	return ResponseEntity.ok(newstudent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.deleteStudent(id));
    }
}
