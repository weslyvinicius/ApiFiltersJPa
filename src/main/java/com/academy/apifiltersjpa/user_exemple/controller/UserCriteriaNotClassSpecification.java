package com.academy.apifiltersjpa.user_exemple.controller;

import com.academy.apifiltersjpa.user_exemple.entity.ActiveDisableEnum;
import com.academy.apifiltersjpa.user_exemple.entity.User;
import com.academy.apifiltersjpa.user_exemple.entity.User_;
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
				predicates.add(criteriaBuilder.like(root.get( User_.name), "%" + name + "%"));
			}
			if (dateBirthday != null) {
				predicates.add(criteriaBuilder.equal(root.get(User_.dateBirthday), dateBirthday));
			}
			if (ageInitial != null && ageEnd != null) {
				predicates.add(criteriaBuilder.between(root.get(User_.age), ageInitial, ageEnd));
			}
			if (city != null && !city.isEmpty()) {
				predicates.add(criteriaBuilder.like(root.get(User_.city), "%" + city + "%"));
			}
			if (state != null && !state().isEmpty()) {
				predicates.add(criteriaBuilder.like(root.get(User_.state), "%" + state + "%"));
			}
			if (active != null) {
				predicates.add(criteriaBuilder.equal(root.get(User_.active), active));
			}

			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
		};
	}

}
