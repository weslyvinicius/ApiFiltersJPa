package com.academy.apifiltersjpa.configs;

import com.academy.apifiltersjpa.entity.ActiveDisableEnum;

import org.springframework.core.convert.converter.Converter;


public class StringToEnumConverter implements Converter<String, ActiveDisableEnum> {

	@Override public ActiveDisableEnum convert( String source ) {
		return ActiveDisableEnum.of( source );
	}
}
