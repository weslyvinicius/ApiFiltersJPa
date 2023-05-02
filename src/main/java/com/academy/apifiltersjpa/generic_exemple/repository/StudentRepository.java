package com.academy.apifiltersjpa.generic_exemple.repository;

import com.academy.apifiltersjpa.generic_exemple.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>, JpaSpecificationExecutor<Student> {

	Student findByName(String name);

	List<Student> findByAddress_City(String city);



}
