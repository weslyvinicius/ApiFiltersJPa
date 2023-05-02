package com.academy.apifiltersjpa.generic_exemple.controller;

import com.academy.apifiltersjpa.generic_exemple.dto.RequestDTO;
import com.academy.apifiltersjpa.generic_exemple.entity.Student;
import com.academy.apifiltersjpa.generic_exemple.repository.StudentRepository;
import com.academy.apifiltersjpa.generic_exemple.service.FilterSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/generic/filter")
@RequiredArgsConstructor
public class GenericFilterController {

	private final StudentRepository studentRepository;

	private final FilterSpecification<Student> studentFilterSpecification;


	@GetMapping("/{name}")
	@ResponseStatus( HttpStatus.OK)
	public Student getStudentByName(@PathVariable String name){
		return studentRepository.findByName(name);
	}

	@GetMapping("/city/{city}")
	@ResponseStatus( HttpStatus.OK)
	public List<Student> getListOfStudentsByCity(@PathVariable String city){
		return studentRepository.findByAddress_City(city);
	}

	@PostMapping("/specification")
	@ResponseStatus( HttpStatus.OK)
	public List<Student> getListOfStudentsByCity(@RequestBody RequestDTO requestDTO ){
		Specification<Student> searchSpecification = studentFilterSpecification.getSearchSpecification(
				requestDTO.searchRequestDTO(), requestDTO.globalOperationEnum() );
		return studentRepository.findAll(searchSpecification);
	}


}
