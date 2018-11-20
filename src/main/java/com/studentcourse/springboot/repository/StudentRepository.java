package com.studentcourse.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studentcourse.springboot.entity.Student;
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}