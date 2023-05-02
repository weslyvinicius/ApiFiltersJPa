package com.academy.apifiltersjpa.user_exemple.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import java.util.stream.Stream;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ActiveDisableEnum {
	ACTIVE("A", "Active"),
	DISABLE("D", "Disable");

	@Getter
	@JsonValue
	private String type;

	@Getter
	private String description;

	ActiveDisableEnum( String type, String description ) {
		this.type = type;
		this.description = description;
	}

	public static ActiveDisableEnum of(String type) {
		return Stream.of(ActiveDisableEnum.values())
				.filter(p -> p.getType().toLowerCase().equals(type.toLowerCase()))
				.findFirst()
				.orElseThrow(IllegalArgumentException::new);
	}
}
