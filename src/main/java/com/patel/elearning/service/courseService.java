package com.patel.elearning.service;

import java.util.List;

import com.patel.elearning.entity.Course;
import com.patel.elearning.entity.Student;

public interface courseService {

	List<Course> findAllCourse();
	
	Course findCourseById(Long id);
	
	Course addCourse(Course course);
	
	Course updateCourse(Long id, Course course);
	
	void deleteCourse(Long id);
	
}
