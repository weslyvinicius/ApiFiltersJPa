package com.academy.apifiltersjpa.generic_exemple.dto;

public record SearchRequestDTO(
		String column,
		String value,
		OperationEnum operation,
		String joinTable

){}
