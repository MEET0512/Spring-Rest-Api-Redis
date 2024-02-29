package com.patel.elearning.service;

import java.util.List;

import java.util.Optional;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.patel.elearning.entity.Course;
import com.patel.elearning.entity.Student;
import com.patel.elearning.repository.CourseRepository;
import com.patel.elearning.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class impStudentsService implements studentService {

	private final StudentRepository studentRepo;
	
	private final CourseRepository courseRepo;
	
	@Override
	@Cacheable("students")
	public List<Student> getAllStudents() {
		return studentRepo.findAll();
	}

	@Override
	@Cacheable("students")
	public Optional<Student> getStudentById(Long id) {
		return studentRepo.findById(id);
	}

	@Override
	@CacheEvict(value = "students", allEntries = true)
	public Student addStudent(Student student) {
		return studentRepo.save(student);
	}

	@Override
	@CacheEvict(value = "students", key = "#id")
	public Student updateStudent(Long id, Student studentDetails) {
		Optional<Student> studentOptional = studentRepo.findById(id);

		if (studentOptional.isEmpty())
			return null;

	    Student student = studentOptional.get();
	    student.setName(studentDetails.getName());
	
	    return studentRepo.save(student);

	}

	@Override
	@CacheEvict(value = "students", key = "#id")
	public String deleteStudent(Long id) {
		studentRepo.findById(id)
        .map(student -> {
            studentRepo.deleteById(id);
            return "Student Record deleted.";
        });
		
		return "Student not found";
		
	}

	@Override
	@CacheEvict(value = "students", key = "#id")
	public Student addCourse(Long studentId, Long courseId) {
		Optional<Student> optionalStudent = studentRepo.findById(studentId);
		Optional<Course> optionalCourse = courseRepo.findById(courseId);
		
		if(!optionalStudent.isEmpty() && !optionalCourse.isEmpty()) {
			Student student = optionalStudent.get();
			Course course = optionalCourse.get();
			student.setCourse(course);
			return studentRepo.save(student);
		}
		return null;
	}

	@Override
	@Cacheable("students")
	public List<Student> getCourseStudents(Long courseId) {
		
		return studentRepo.findByCourseId(courseId);
	}

}
