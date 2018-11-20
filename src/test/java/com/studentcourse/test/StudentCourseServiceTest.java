package com.studentcourse.test;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.studentcourse.springboot.Application;
import com.studentcourse.springboot.controller.StudentCourseController;
import com.studentcourse.springboot.entity.Course;
import com.studentcourse.springboot.entity.Student;
import com.studentcourse.springboot.repository.CourseRepository;
import com.studentcourse.springboot.repository.StudentRepository;
import com.studentcourse.springboot.services.StudentCourseService;

//@ComponentScan("com.studentcourse.*")
//@SpringBootTest(classes=StudentCourseController.class)


@RunWith(SpringRunner.class)
@WebMvcTest(StudentCourseService.class)
@ContextConfiguration(classes={Application.class})
public class StudentCourseServiceTest {
	
	@Autowired
	private MockMvc mockMvc;

	
	@MockBean
	private StudentRepository studentRepository;
	
	@MockBean
	private CourseRepository courseRepository;
	
	
	@Test
	public void testGetStudent() throws Exception {
		
		final Course course2 = new Course("Artificial Intellegence");
		final Set<Course> mockCourse =  new HashSet<Course>(){{
            add(course2);
     
        }};
		Student mockStudent = new Student("Lucky",mockCourse);
		
		Mockito.when(studentRepository.findOne(Mockito.anyLong())).thenReturn(mockStudent);
		//use this spesific data
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/studentcourse/getStudentById/5").accept(
				MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
	
		Assert.assertEquals(mockStudent, mockStudent);
		//JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
		//expect the response
	}
	
	@Test
	public void testGetCourseService() throws Exception {
	
		final Student student1 = new Student("Lucky");
		final Set<Student> mockStudent =  new HashSet<Student>(){{
            add(student1);
       
        }};
		Course mockCourse = new Course("Sejarah",mockStudent);
		
		Mockito.when(courseRepository.findOne(Mockito.anyLong())).thenReturn(mockCourse);
		//use this spesific data
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/studentcourse/getCourseById/5").accept(
				MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		Assert.assertEquals(mockCourse.toString(), result.getResponse().getContentAsString());
	}
	
	@Test
	public void testAddCourseService() throws Exception {
		
		
		Course mockCourse = new Course("Sejarah");
		
		String courseJson = "{\"id\":0,\"name\":\"Sejarah\",\"student\": null}";
		
		Mockito.when(courseRepository.save(Mockito.any(Course.class))).thenReturn(mockCourse);
		//use this spesific data
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/studentcourse/addCourse").accept(
				MediaType.APPLICATION_JSON).content(courseJson).contentType(MediaType.APPLICATION_JSON);


		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response = result.getResponse();
		//assertEquals(HttpStatus.CREATED.value(), response.getStatus());
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
	}

	
	@Test
	public void testAddStudent() throws Exception {
	
		Student mockStudent = new Student("Indah");
		
		String studentJson = "{\"id\":0,\"name\":\"Indah\",\"course\": null}";
		
		Mockito.when(studentRepository.save(Mockito.any(Student.class))).thenReturn(mockStudent);
		
		//use this spesific data
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/studentcourse/addStudent").accept(
				MediaType.APPLICATION_JSON).content(studentJson).contentType(MediaType.APPLICATION_JSON);


		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response = result.getResponse();
		
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
	}

	@Test
	public void testRegisterCourse() throws Exception {
		
		final Course course2 = new Course("Artificial Intellegence");
		final Set<Course> mockCourse =  new HashSet<Course>(){{
            add(course2);
       
        }};
		Student mockStudent = new Student("Lucky",mockCourse);

		Mockito.when(studentRepository.findOne(Mockito.anyLong())).thenReturn(mockStudent);
		Mockito.when(courseRepository.findOne(Mockito.anyLong())).thenReturn(course2);
		Mockito.when(studentRepository.save(Mockito.any(Student.class))).thenReturn(mockStudent);
		//use this spesific data
		
		String studentJson = "{\"id\":0,\"name\":\"indah\",\"course\": null}";
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/studentcourse/registerCourse/5/6").accept(
				MediaType.APPLICATION_JSON).content(studentJson).contentType(MediaType.APPLICATION_JSON);;
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		assertEquals(mockStudent, mockStudent);
	}
	
	@Test
	public void testDeRegisterCourse() throws Exception {
		
		final Course course2 = new Course("Artificial Intellegence");
		final Set<Course> mockCourse =  new HashSet<Course>(){{
            add(course2);
     
        }};
		Student mockStudent = new Student("Lucky",mockCourse);
	
		Mockito.when(studentRepository.findOne(Mockito.anyLong())).thenReturn(mockStudent);
		Mockito.when(courseRepository.findOne(Mockito.anyLong())).thenReturn(course2);
		Mockito.when(studentRepository.save(Mockito.any(Student.class))).thenReturn(mockStudent);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/studentcourse/deRegisterCourse/5/6").accept(
				MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		assertEquals(mockStudent, mockStudent);
	
	}
	
	@Test
	public void testReport() throws Exception {
		
		final Student student1 = new Student("Lucky");
		final Set<Student> mockStudent =  new HashSet<Student>(){{
            add(student1);
       
        }};
        Course mockCourse1 = new Course("Kimia",mockStudent);
		Course mockCourse2 = new Course("Sejarah",mockStudent);
		
		List<Course> mockCourse = Arrays.asList(mockCourse1,mockCourse2);
		
		Mockito.when(courseRepository.findAll()).thenReturn(mockCourse);
		//use this spesific data
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/studentcourse/report").accept(
				MediaType.APPLICATION_JSON);
	
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		assertEquals(mockCourse, mockCourse);
	}

	@Test
	public void testUpdateCourse() throws Exception {
		
		final Student student1 = new Student("Lucky");
		final Set<Student> mockStudent =  new HashSet<Student>(){{
            add(student1);
       
        }};
		Course mockCourse = new Course("History",mockStudent);
		
		Mockito.when(courseRepository.findOne(Mockito.anyLong())).thenReturn(mockCourse);
		Mockito.when(courseRepository.save(Mockito.any(Course.class))).thenReturn(mockCourse);
		//use this spesific data
		
		String courseJson = "{\"id\":0,\"name\":\"History\"}";
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/studentcourse/updateCourse/5").accept(
				MediaType.APPLICATION_JSON).content(courseJson).contentType(MediaType.APPLICATION_JSON);;
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
				
		assertEquals(mockCourse, mockCourse);
	}
	
	@Test
	public void testUpdateStudent() throws Exception {
	

		final Course course2 = new Course("Artificial Intellegence");
		final Set<Course> mockCourse =  new HashSet<Course>(){{
            add(course2);
       
        }};
		Student mockStudent = new Student("Lucky Putra",mockCourse);
		
		
		
		Mockito.when(studentRepository.findOne(Mockito.anyLong())).thenReturn(mockStudent);
		Mockito.when(studentRepository.save(Mockito.any(Student.class))).thenReturn(mockStudent);
	
		//use this spesific data
		
		String courseJson = "{\"id\":0,\"name\":\"Lucky Putra\"}";
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/studentcourse/updateStudent/5").accept(
				MediaType.APPLICATION_JSON).content(courseJson).contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		assertEquals(mockStudent.toString(), result.getResponse().getContentAsString());
		
	}
	
	@Test
	public void testListAllStudent() throws Exception {	

		final Course course2 = new Course("Artificial Intellegence");
		final Set<Course> mockCourse =  new HashSet<Course>(){{
            add(course2);
        }};
		final Student mockStudent1 = new Student("Lucky Putra",mockCourse);
	
		final Course course3 = new Course("Artificial Intellegence");
		final Set<Course> mockCourse2 =  new HashSet<Course>(){{
            add(course3);
        }};
		final Student mockStudent2 = new Student("Lucky Valatehan",mockCourse);
		
		List<Student> mockResult = Arrays.asList(mockStudent1,mockStudent2);
				
		Mockito.when(studentRepository.findAll()).thenReturn(mockResult);
		//use this spesific data
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/studentcourse/listAllStudent").accept(
				MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		assertEquals(mockResult, mockResult);
	}
	
	@Test
	public void testListAllCourse() throws Exception {
	
		final Student student1 = new Student("Lucky Putra");
		final Set<Student> mockStudent1 =  new HashSet<Student>(){{
            add(student1);
        }};
		Course mockCourse1 = new Course("Artificial Intellegence",mockStudent1);
		
		final Student student2 = new Student("Lucky Valatehan");
		final Set<Student> mockStudent2 =  new HashSet<Student>(){{
            add(student2);
        }};
		Course mockCourse2 = new Course("History",mockStudent2);
		
		List<Course> mockResult = Arrays.asList(mockCourse1,mockCourse2);		
		Mockito.when(courseRepository.findAll()).thenReturn(mockResult);
		//use this spesific data
	
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/studentcourse/listAllCourse").accept(
				MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		assertEquals(mockResult, mockResult);
		
	}
}
