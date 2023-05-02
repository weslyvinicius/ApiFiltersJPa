package com.academy.apifiltersjpa.user_exemple.entity;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.apache.logging.log4j.util.Strings;

@Converter(autoApply = true)
public class ActiveDisableConvert implements AttributeConverter<ActiveDisableEnum, String> {

	@Override public String convertToDatabaseColumn( ActiveDisableEnum activeDisableEnum ) {
		if (activeDisableEnum == null) {
			return null;
		}
		return activeDisableEnum.getType();
	}

	@Override public ActiveDisableEnum convertToEntityAttribute( String value ) {
		if(Strings.isBlank(value)) {
			return null;
		}

		return ActiveDisableEnum.of( value );
	}
}
