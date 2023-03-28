package com.pdf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pdf.entity.Student;
@Repository
public interface StudentPdfRepository extends JpaRepository<Student, Long>{

}
