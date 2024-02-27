package com.patel.elearning.service;

import java.util.List;
import java.util.Optional;

import com.patel.elearning.entity.Student;

public interface studentService {

	List<Student> getAllStudents();
	
	Optional<Student> getStudentById(Long id);
	
	Student addStudent(Student student);
	
	Student updateStudent(Long id, Student Student);
	
	String deleteStudent(Long id);

	Student addCourse(Long studentId, Long courseId);

	List<Student> getCourseStudents(Long courseId);
}
