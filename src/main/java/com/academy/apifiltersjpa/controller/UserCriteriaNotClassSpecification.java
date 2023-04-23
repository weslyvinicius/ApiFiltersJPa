package com.academy.apifiltersjpa.controller;

import com.academy.apifiltersjpa.entity.ActiveDisableEnum;
import com.academy.apifiltersjpa.entity.User;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public record UserCriteriaNotClassSpecification(
		Long id,
		String name,
		LocalDate dateBirthday,
		Integer ageInitial,
		Integer ageEnd,
		String city,
		String state,
		ActiveDisableEnum active
) {


	public Specification<User> toSpec(){
		return ( root, query, criteriaBuilder ) ->{

			List<Predicate> predicates = new ArrayList<>();
			// Adicione as condições desejadas de acordo com os campos preenchidos no userCriteria
			if (name != null && !name.isEmpty()) {
				predicates.add(criteriaBuilder.like(root.get("name"), "%" + name + "%"));
			}
			if (dateBirthday != null) {
				predicates.add(criteriaBuilder.equal(root.get("dateBirthday"), dateBirthday));
			}
			if (ageInitial != null && ageEnd != null) {
				predicates.add(criteriaBuilder.between(root.get("age"), ageInitial, ageEnd));
			}
			if (city != null && !city.isEmpty()) {
				predicates.add(criteriaBuilder.like(root.get("city"), "%" + city + "%"));
			}
			if (state != null && !state().isEmpty()) {
				predicates.add(criteriaBuilder.like(root.get("state"), "%" + state + "%"));
			}
			if (active != null) {
				predicates.add(criteriaBuilder.equal(root.get("active"), active));
			}

			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
		};
	}

}
