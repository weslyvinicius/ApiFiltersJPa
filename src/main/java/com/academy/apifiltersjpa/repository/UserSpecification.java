package com.academy.apifiltersjpa.repository;

import com.academy.apifiltersjpa.controller.UserCriteria;
import com.academy.apifiltersjpa.entity.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

public class UserSpecification implements Specification<User> {

	private final UserCriteria userCriteria;

	public UserSpecification( UserCriteria userCriteria ) {
		this.userCriteria = userCriteria;
	}

	@Override public Predicate toPredicate( Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder ) {
		List<Predicate> predicates = new ArrayList<>();

		// Adicione as condições desejadas de acordo com os campos preenchidos no userCriteria
		if (userCriteria.name() != null && !userCriteria.name().isEmpty()) {
			predicates.add(criteriaBuilder.like(root.get("name"), "%" + userCriteria.name() + "%"));
		}
		if (userCriteria.dateBirthday() != null) {
			predicates.add(criteriaBuilder.equal(root.get("dateBirthday"), userCriteria.dateBirthday()));
		}
		if (userCriteria.ageInitial() != null && userCriteria.ageEnd() != null) {
			predicates.add(criteriaBuilder.between(root.get("age"), userCriteria.ageInitial(), userCriteria.ageEnd()));
		}
		if (userCriteria.city() != null && !userCriteria.city().isEmpty()) {
			predicates.add(criteriaBuilder.like(root.get("city"), "%" + userCriteria.city() + "%"));
		}
		if (userCriteria.state() != null && !userCriteria.state().isEmpty()) {
			predicates.add(criteriaBuilder.like(root.get("state"), "%" + userCriteria.state() + "%"));
		}
		if (userCriteria.active() != null) {
			predicates.add(criteriaBuilder.equal(root.get("active"), userCriteria.active()));
		}

		return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

	}
}
