package com.studentcourse.springboot.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="student")
public class Student {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	private String name;

	
	
	public Student() {
	}

	public Student(String name) {
		super();
		this.name = name;
	}
	
	public Student(String name, Set<Course> course) {
		super();
		this.name = name;
		this.course = course;
	}

	

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "studentcourse", 
    	joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"),
    	inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"))
	private Set<Course> course;
	public Set<Course> getCourse() {
        return course;
    }

    public void setCourse(Set<Course> courses) {
        this.course = courses;
    }
	
	
    @Override
    public String toString() {
        String result = String.format(
        		"{"+'"'+"id"+'"'+":"+'"'+"%d"+'"'+", "+'"'+"name"+'"'+":"+'"'+"%s"+'"'+", "+'"'+"courses"+'"'+":[",
                id, name);
        int courseSize = course.size();
        int i=0;
        if (course != null) {
            for(Course course : course) {
            	if(i<courseSize-1) {
            		 result += String.format(
                     		"{"+'"'+"id"+'"'+":"+'"'+"%d"+'"'+", "+'"'+"name"+'"'+":"+'"'+"%s"+'"'+"},",
                             course.getId(), course.getName());
            	}
            	else {
            		 result += String.format(
                     		"{"+'"'+"id"+'"'+":"+'"'+"%d"+'"'+", "+'"'+"name"+'"'+":"+'"'+"%s"+'"'+"}",
                             course.getId(), course.getName());
            	}
            	i++;
               
            }
        }

        return result+"]}";
    }
	
	
}
