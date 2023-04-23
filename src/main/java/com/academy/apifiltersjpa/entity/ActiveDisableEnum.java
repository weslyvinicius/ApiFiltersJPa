package com.academy.apifiltersjpa.entity;

import lombok.Getter;

import java.util.stream.Stream;

public enum ActiveDisableEnum {
	ACTIVE("A", "Active"),
	DISABLE("D", "Disable");

	@Getter
	private String type;

	@Getter
	private String description;

	ActiveDisableEnum( String type, String description ) {
		this.type = type;
		this.description = description;
	}

	public static ActiveDisableEnum of(String type) {
		return Stream.of(ActiveDisableEnum.values())
				.filter(p -> p.getType().equals(type))
				.findFirst()
				.orElseThrow(IllegalArgumentException::new);
	}
}
