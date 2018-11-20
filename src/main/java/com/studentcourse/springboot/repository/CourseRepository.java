package com.studentcourse.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studentcourse.springboot.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long>{

}
