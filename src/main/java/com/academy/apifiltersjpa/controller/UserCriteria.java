package com.academy.apifiltersjpa.controller;

import com.academy.apifiltersjpa.entity.ActiveDisableEnum;

import java.time.LocalDate;
public record UserCriteria(
		Long id,
		String name,
		LocalDate dateBirthday,
		Integer ageInitial,
		Integer ageEnd,
		String city,
		String state,
		ActiveDisableEnum active
) {


}
