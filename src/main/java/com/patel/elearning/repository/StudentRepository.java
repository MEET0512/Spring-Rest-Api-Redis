package com.patel.elearning.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.patel.elearning.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

	
	List<Student> findByCourseId(Long courseId);

}
