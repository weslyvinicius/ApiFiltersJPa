package com.academy.apifiltersjpa.generic_exemple.controller;

import com.academy.apifiltersjpa.generic_exemple.dto.GlobalOperationEnum;
import com.academy.apifiltersjpa.generic_exemple.dto.OperationEnum;
import com.academy.apifiltersjpa.generic_exemple.dto.RequestDTO;
import com.academy.apifiltersjpa.generic_exemple.dto.SearchRequestDTO;
import com.academy.apifiltersjpa.generic_exemple.entity.Student;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.observation.Observation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class GenericFilterControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void getStudentByName() throws Exception {

		var result = mockMvc.perform(
						MockMvcRequestBuilders.get( "/generic/filter/{name}", "Jose" )
								.contentType( MediaType.APPLICATION_JSON )
								.characterEncoding( StandardCharsets.UTF_8 )
				)
				.andDo( MockMvcResultHandlers.print() )
				.andExpect( MockMvcResultMatchers.status().isOk() )
				.andReturn().getResponse().getContentAsString();

		Student student = objectMapper.readValue( result, Student.class );

		Assertions.assertNotNull( student );
		Assertions.assertEquals( "Jose", student.getName() );

	}

	@Test
	void getListOfStudentsByCity() throws Exception {

		var result = mockMvc.perform(
						MockMvcRequestBuilders.get( "/generic/filter/city/{city}", "Braga" )
								.contentType( MediaType.APPLICATION_JSON )
								.characterEncoding( StandardCharsets.UTF_8 )
				)
				.andDo( MockMvcResultHandlers.print() )
				.andExpect( MockMvcResultMatchers.status().isOk() )
				.andReturn().getResponse().getContentAsString();

		var student = objectMapper.readValue( result, new TypeReference<List<Student>>(){} );

		Assertions.assertNotNull( student );
		Assertions.assertEquals( "Maria", student.get( 0 ).getName() );

	}

	@Test
	void getListOfStudentsBySpecification() throws Exception {

		var body = new RequestDTO( GlobalOperationEnum.AND,
				                    Arrays.asList(new SearchRequestDTO( "id", "3" , OperationEnum.EQUAL, null)) );

		var result = mockMvc.perform(
						MockMvcRequestBuilders.post( "/generic/filter/specification" )
								.contentType( MediaType.APPLICATION_JSON )
								.characterEncoding( StandardCharsets.UTF_8 )
								.content(objectMapper.writeValueAsString( body ) )
				)
				.andDo( MockMvcResultHandlers.print() )
				.andExpect( MockMvcResultMatchers.status().isOk() )
				.andReturn().getResponse().getContentAsString();

		var student = objectMapper.readValue( result, new TypeReference<List<Student>>(){} );

		Assertions.assertNotNull( student );
	//	Assertions.assertEquals( "Maria", student.get( 0 ).getName() );

	}
}