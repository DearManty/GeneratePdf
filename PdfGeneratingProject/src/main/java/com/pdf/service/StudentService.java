package com.pdf.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pdf.entity.Student;
@Service
public interface StudentService {

	Student createStudent(Student student);
	
	List<Student> getAllStudent();

	
}
