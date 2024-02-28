package com.patel.elearning.service;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.patel.elearning.entity.Course;
import com.patel.elearning.repository.CourseRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class impCourseService implements courseService {
	
	private final CourseRepository courseRepo;

	@Override
	public List<Course> findAllCourse() {
		System.out.print("Featchin from database");
		return courseRepo.findAll();
	}

	@Override
	@Cacheable("courses")
	public Course findCourseById(Long id) {
		System.out.print("Featchin from database");
		return courseRepo.findById(id).orElseThrow(() -> new RuntimeException("Course not found"));
	}

	@Override
	public Course addCourse(Course course) {
		 return courseRepo.save(course);
	}

	@Override
	public Course updateCourse(Long id, Course courseDetails) {
		Course course = courseRepo.findById(id).orElseThrow(() -> new RuntimeException("Course not found"));
        course.setName(courseDetails.getName());
        return courseRepo.save(course);
	}

	@Override
	public void deleteCourse(Long id) {
		Course course = courseRepo.findById(id).orElseThrow(() -> new RuntimeException("Course not found"));
        courseRepo.delete(course);
	}

}
