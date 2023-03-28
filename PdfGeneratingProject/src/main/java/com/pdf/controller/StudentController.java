package com.pdf.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lowagie.text.DocumentException;
import com.pdf.entity.Student;
import com.pdf.service.StudentService;
import com.pdf.util.PdfGenerator;

@RestController
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@PostMapping("/addstudent")
	public ResponseEntity<Student> addStudent(@RequestBody Student student){
		Student createStudent = this.studentService.createStudent(student);
		return new ResponseEntity<Student>(createStudent,HttpStatus.CREATED);
		
	}
	
	

	@GetMapping("/getAllstudent")
	public ResponseEntity<List<Student>> getAllStudent(){
		 List<Student> allStudent = this.studentService.getAllStudent();
		return new ResponseEntity<List<Student>>(allStudent,HttpStatus.OK);
		
	}
	@GetMapping("/export-to-pdf")
	public void generatePdf(HttpServletResponse response) throws DocumentException, IOException {
		
		response.setContentType("application/pdf");
		
		DateFormat dateFormat=new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
		dateFormat.format(new Date());
		String headerkey = "Content-Disposition";
	    String currentDateTime = null;
		String headervalue = "attachment; filename=student" + currentDateTime + ".pdf";
	    response.setHeader(headerkey, headervalue);
	    List < Student > listofStudents = studentService.getAllStudent();
	    PdfGenerator generator = new PdfGenerator();
	    generator.generate(listofStudents, response);
	}
}
