package com.studentcourse.springboot.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.studentcourse.springboot.entity.Course;
import com.studentcourse.springboot.entity.Student;
import com.studentcourse.springboot.repository.CourseRepository;
import com.studentcourse.springboot.repository.StudentRepository;
import com.studentcourse.springboot.services.StudentCourseService;

@RestController
@RequestMapping("studentcourse")
public class StudentCourseController {

	@Autowired
	private StudentCourseService studentCourseService;
	
	@GetMapping("/listAllStudent")
	public String listAllStudent()
	{
		return studentCourseService.listAllStudent();
	}
	
	@GetMapping("/listAllCourse")
	public String listAllCourse()
	{
		return studentCourseService.listAllCourse();
	}
	
	@PostMapping("/addStudent")
	public ResponseEntity<Object> addStudent(@Valid @RequestBody Student student) {
		Student savedStudent = studentCourseService.addStudent(student);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedStudent.getId()).toUri();

		return ResponseEntity.created(location).build();
	}

	@PostMapping("/addCourse")
	public ResponseEntity<Object> addCourse(@Valid @RequestBody Course course) {
		Course savedCourse = studentCourseService.addCourse(course);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedCourse.getId()).toUri();

		return ResponseEntity.created(location).build();
	}
	
	@GetMapping("/getStudentById/{id}")
	public String getStudentById(@PathVariable(value="id") long id){
		return studentCourseService.GetStudent(id);
	}

	@PutMapping("/registerCourse/{id_student}/{id_course}")
	public String registerCourse(@PathVariable(value="id_student") long id_student, @PathVariable(value="id_course") long id_course) {
		return studentCourseService.registerCourse(id_student, id_course);
	}

	@PutMapping("/deRegisterCourse/{id_student}/{id_course}")
	public String deRegisterCourse(@PathVariable(value="id_student") long id_student, @PathVariable(value="id_course") long id_course) {
			return studentCourseService.deRegisterCourse(id_student, id_course);
	}
	
	@GetMapping("/getCourseById/{id}")
	public String getCourseById(@PathVariable(value="id") long id){
		return studentCourseService.GetCourse(id);
	}
	
	@PutMapping("/updateStudent/{id_student}")
	public String updateStudent(@PathVariable(value="id_student")long id_student, 
			@Valid @RequestBody Student detailStudent){
		return studentCourseService.updateStudent(id_student, detailStudent);
	}

	@PutMapping("/updateCourse/{id_course}")
	public String updateCourse(@PathVariable(value="id_course")long id_course, 
			@Valid @RequestBody Course detailCourse){
		return studentCourseService.updateCourse(id_course, detailCourse);
	}
	
	@GetMapping("/report")
	public String report(){
		return studentCourseService.Report();
	}

}
