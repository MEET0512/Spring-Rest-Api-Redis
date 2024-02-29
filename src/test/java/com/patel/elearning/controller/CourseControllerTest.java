package com.patel.elearning.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.patel.elearning.entity.Course;
import com.patel.elearning.repository.CourseRepository;	
import com.patel.elearning.service.impCourseService;

import lombok.RequiredArgsConstructor;


@SpringBootTest
@AutoConfigureMockMvc
@RequiredArgsConstructor
public class CourseControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper mapper;
	
	@MockBean
	private CourseRepository courseRepository;
	
	@InjectMocks
	private impCourseService courseService;
	
	@Test
	@DisplayName("Testing: getting all courses.")
	public void getAllCourses() throws Exception {
		when(courseRepository.findAll()).thenReturn(Collections.emptyList());
		
		mockMvc.perform(get("/api/courses"))
				.andExpect(status().isOk())
				.andExpect(content().json("[]"));
	}
	
	@Test
	@DisplayName("Testing: getting specific course using course id.")
	public void getCourseById() throws Exception {
		Long courseId = 2L;
		Course mockCourse = new Course();
		mockCourse.setId(courseId);
		mockCourse.setName("JAVA");
		
		when(courseRepository.findById(courseId)).thenReturn(Optional.of(mockCourse));
		
		String expectedJson = mapper.writeValueAsString(mockCourse);
		
		mockMvc.perform(get("/api/courses/{id}", courseId))
				.andExpect(status().isOk())
				.andExpect(content().json(expectedJson));
		
	}
	
	@Test
	@DisplayName("Testing: adding new course.")
	public void addNewCourse() throws JsonProcessingException, Exception {
		Course newCourse = new Course();
		newCourse.setId((long) 2);
		newCourse.setName("JAVA");
		newCourse.setDuration("12h 30m");
		
		when(courseRepository.save(any(Course.class))).thenReturn(newCourse);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/api/courses")
				.contentType("application/json")
				.content(mapper.writeValueAsString(newCourse)))
				.andExpect(status().isOk())
				.andExpect(content().json(mapper.writeValueAsString(newCourse)));
		
		verify(courseRepository).save(any(Course.class));
	}
	
	@Test
	@DisplayName("Testing: updating existing course.")
	public void updateCourse() throws Exception {
		Long id = 2L;
		Course existedCourse = new Course();
		existedCourse.setId(id);
		existedCourse.setName("JAVA");
		
		Course updatedCourse = new Course();
		updatedCourse.setName("Spring Boot");
		
		String expectedCourse = mapper.writeValueAsString(updatedCourse);
		
		when(courseRepository.findById(id)).thenReturn(Optional.of(existedCourse));
		when(courseRepository.save(existedCourse)).thenReturn(updatedCourse);
		
		mockMvc.perform(MockMvcRequestBuilders.put("/api/courses/{id}", id)
				                               .contentType("application/json")
				                               .content(expectedCourse))
												.andExpect(status().isOk())
												.andExpect(content().json(expectedCourse));
		
		verify(courseRepository, times(1)).findById(id);
		verify(courseRepository, times(1)).save(existedCourse);
	}
	
	@Test
	@DisplayName("Testing: deleting existing course.")
	public void deleteCourse() throws Exception {
		Long id = 2L;
		Course existedCourse = new Course();
		existedCourse.setId(id);
		existedCourse.setName("JAVA");
		
		when(courseRepository.findById(id)).thenReturn(Optional.of(existedCourse));
		
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/courses/{id}", id))
												.andExpect(status().isOk());
		
		verify(courseRepository, times(1)).findById(id);
		verify(courseRepository, times(1)).delete(existedCourse);
	}
}
