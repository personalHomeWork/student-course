package com.studentcourse.springboot.services;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.studentcourse.springboot.entity.Course;
import com.studentcourse.springboot.entity.Student;
import com.studentcourse.springboot.repository.CourseRepository;
import com.studentcourse.springboot.repository.StudentRepository;

@Component
public class StudentCourseService {

	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Transactional
	public String listAllStudent()
	{
		 String result="";
		 long i=0;
		 int sizeOfStudent = studentRepository.findAll().size();
		 for(Student a :studentRepository.findAll()) {
			 	if(i<sizeOfStudent-1) {
			 		result += a.toString()+",";
			 	}
			 	else {
			 		result += a.toString();
			 	}
			 	i++;
	        }
		return "["+result+"]";
	}
	
	@Transactional
	public String listAllCourse()
	{
		 String result="";
		 int sizeOfCourses = courseRepository.findAll().size();
		 long i=0;
		 for(Course course :courseRepository.findAll()) {
			 if(i<sizeOfCourses-1) {
			 		result += course.toString()+",";
			 	}
			 	else {
			 		result += course.toString();
			 	}
			 	i++;
	        }
		return "["+result+"]";
	}
	
	@Transactional
	public Student addStudent(Student student) {
		return studentRepository.save(student);
	}
	
	@Transactional
	public Course addCourse(Course course) {
		return courseRepository.save(course);
	}

	@Transactional
	public String GetStudent(long id_student) {
		Student student = studentRepository.findOne(id_student);
		if(student == null)
			return "Not Found";
	
		return student.toString();
	}

	@Transactional
	public String GetCourse(long id_course) {
		Course course = courseRepository.findOne(id_course);
		if(course == null)
			return "Not Found";
	
		return course.toString();
	}
	
	@Transactional
	public String registerCourse(long id_student,long id_course) {
		Student student = studentRepository.findOne(id_student);
		Set<Course> courseStudent = student.getCourse();
		
		Course course = courseRepository.findOne(id_course);
		courseStudent.add(course);
		
		studentRepository.save(student);
		
		Student studenta = studentRepository.findOne(id_student);
		
		return studenta.toString();
	}
	
	@Transactional
	public String deRegisterCourse(long id_student,long id_course) {
		
		Student student = studentRepository.findOne(id_student);
		Set<Course> courseStudent = student.getCourse();
		
		Course course = courseRepository.findOne(id_course);
		courseStudent.remove(course);
		
		studentRepository.save(student);
		
		Student studenta = studentRepository.findOne(id_student);
		
		return studenta.toString();
	}
	
	@Transactional
	public String updateStudent(long id_student,Student detailStudent) {
	
		Student student = studentRepository.findOne(id_student);
		if(student == null)
			return "Not Found";
		
		student.setName(detailStudent.getName());
		
		Student updatedStudent = studentRepository.save(student);
		
		return updatedStudent.toString();
	}
	
	@Transactional
	public String updateCourse(long id_course,Course detailCourse) {
		Course course = courseRepository.findOne(id_course);
		if(course == null)
			return "Not Found";
		
		course.setName(detailCourse.getName());
		
		Course updatedCourse = courseRepository.save(course);
		
		return course.toString();
	}
	
	@Transactional
	public String Report() {
	List<Course> course = courseRepository.findAll();
		
		String result1 ="";
		String result2 ="";
		String finalResult="";
		long studentCount=0;
		int i=0;
	
		long courseSize= course.size();
		
		for(Course courseLoop : course) {
			Set<Student> students = courseLoop.getStudent();
			int studentSize = students.size();
			if(i<courseSize-1) {
				String temp = String.format("{"+'"'+"name"+'"'+":"+'"'+"%s"+'"'+", "+'"'+"students"+'"'+": %d },", 
						courseLoop.getName(),studentSize);
				result1 += temp;
				studentCount+=studentSize;
			}
			else {
				String temp = String.format("{"+'"'+"name"+'"'+":"+'"'+"%s"+'"'+", "+'"'+"students"+'"'+": %d }", 
						courseLoop.getName(),studentSize);
				result1 += temp;
				studentCount+=studentSize;
			}
			
			i++;
		}
		
		result2 = String.format("{"+'"'+"totalCourse"+'"'+":%d, "+'"'+"totalStudents"+'"'+": %d }",
				courseSize,studentCount);
		
		finalResult = "{"+'"'+"course"+'"'+":["+result1+"],"+'"'+"sumerize"+'"'+":"+result2+"}";
		
		return finalResult;
	}
	
}
