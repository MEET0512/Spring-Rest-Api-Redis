package com.patel.elearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.patel.elearning.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
