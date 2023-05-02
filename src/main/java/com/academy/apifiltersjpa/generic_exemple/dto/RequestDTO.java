package com.academy.apifiltersjpa.generic_exemple.dto;

import java.util.List;

public record RequestDTO(
		GlobalOperationEnum globalOperationEnum,
		List<SearchRequestDTO> searchRequestDTO

) {



}
