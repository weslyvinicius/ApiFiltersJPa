package com.academy.apifiltersjpa.user_exemple.repository;

import com.academy.apifiltersjpa.user_exemple.controller.UserCriteria;
import com.academy.apifiltersjpa.user_exemple.entity.User;

import com.academy.apifiltersjpa.user_exemple.entity.User_;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;


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
			predicates.add(criteriaBuilder.like(root.get( User_.name), "%" + userCriteria.name() + "%"));
		}
		if (userCriteria.dateBirthday() != null) {
			predicates.add(criteriaBuilder.equal(root.get(User_.dateBirthday), userCriteria.dateBirthday()));
		}
		if (userCriteria.ageInitial() != null && userCriteria.ageEnd() != null) {
			predicates.add(criteriaBuilder.between(root.get(User_.age), userCriteria.ageInitial(), userCriteria.ageEnd()));
		}
		if (userCriteria.city() != null && !userCriteria.city().isEmpty()) {
			predicates.add(criteriaBuilder.like(root.get(User_.city), "%" + userCriteria.city() + "%"));
		}
		if (userCriteria.state() != null && !userCriteria.state().isEmpty()) {
			predicates.add(criteriaBuilder.like(root.get(User_.state), "%" + userCriteria.state() + "%"));
		}
		if (userCriteria.active() != null) {
			predicates.add(criteriaBuilder.equal(root.get(User_.active), userCriteria.active()));
		}

		return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

	}
}
