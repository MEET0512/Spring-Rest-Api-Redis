package com.patel.elearning.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patel.elearning.entity.Course;
import com.patel.elearning.service.courseService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/courses")
public class CourseController {
	
	private final courseService service;
	
	@GetMapping
    public List<Course> getAllCourses() {
        return service.findAllCourse();
    }

    @GetMapping("/{id}")
    public Course getCourseById(@PathVariable("id") Long id) {
        return service.findCourseById(id);
    }

    @PostMapping
    public Course createCourse(@RequestBody Course course) {
       return service.addCourse(course);
    }

    @PutMapping("/{id}")
    public Course updateCourse(@PathVariable("id") Long id, @RequestBody Course courseDetails) {
        return service.updateCourse(id, courseDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable("id") Long id) {
        service.deleteCourse(id);
    }
}
