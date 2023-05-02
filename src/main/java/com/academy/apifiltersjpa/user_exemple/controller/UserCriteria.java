package com.academy.apifiltersjpa.user_exemple.controller;

import com.academy.apifiltersjpa.user_exemple.entity.ActiveDisableEnum;

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
