package com.studentcourse.springboot.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.studentcourse.springboot.entity.*;

@Entity
@Table(name="course")
public class Course {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	private String name;
	
	public Course() {
		
	}
	public Course(String name) {
	
		this.name = name;
	}
	public Course(String name,Set<Student> student) {

		this.name = name;
		this.student = student;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@ManyToMany(mappedBy = "course")
	private Set<Student> student;
	public Set<Student> getStudent() {
        return student;
    }

    public void setStudent(Set<Student> students) {
        this.student = students;
    }
    
	@Override
	public String toString() {

		 String result = String.format(
	                "{"+'"'+"id"+'"'+":"+'"'+"%d"+'"'+", "+'"'+"name"+'"'+":"+'"'+"%s"+'"'+", "+'"'+"students"+'"'+":[",
	                this.id, this.name);
		 int studentCount = student.size();
		 int i=0;
	        if (student != null) {
	            for(Student studentLoop : student) {
	                if(i<studentCount-1) {
	            	result += String.format(
	                		"{"+'"'+"id"+'"'+":"+'"'+"%d"+'"'+", "+'"'+"name"+'"'+":"+'"'+"%s"+'"'+"},",
	                        studentLoop.getId(), studentLoop.getName());
	                }
	                else {
	                	result += String.format(
		                		"{"+'"'+"id"+'"'+":"+'"'+"%d"+'"'+", "+'"'+"name"+'"'+":"+'"'+"%s"+'"'+"}",
		                        studentLoop.getId(), studentLoop.getName());
	                }
	                i++;
	            }
	        }
	        
	        return result+"]}";
		
	}
    
    
	
	

}
