package com.academy.apifiltersjpa.user_exemple.configs;

import com.academy.apifiltersjpa.user_exemple.entity.ActiveDisableEnum;

import org.springframework.core.convert.converter.Converter;


public class ActiveDisableEnumConverter implements Converter<String, ActiveDisableEnum> {

	@Override public ActiveDisableEnum convert( String source ) {
		return ActiveDisableEnum.of( source );
	}
}
