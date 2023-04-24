package com.academy.apifiltersjpa.repository;

import com.academy.apifiltersjpa.entity.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryOther extends PagingAndSortingRepository<User, Long>, JpaSpecificationExecutor<User> {

	interface Specs {

		static Specification<User> likeName(String name){
			return ( root, query, criteriaBuilder ) ->
				criteriaBuilder.like(root.get("name"), "%" + name + "%");
		}

		static Specification<User> equalDateBirthday(String dateBirthday){
			return ( root, query, criteriaBuilder ) ->
					criteriaBuilder.equal(root.get("dateBirthday"), dateBirthday);
		}

		static Specification<User> betweenAges(Integer ageInitial,  Integer ageEnd){
			return ( root, query, criteriaBuilder ) ->
					criteriaBuilder.between(root.get("age"), ageInitial, ageEnd);
		}

		static Specification<User> likeCity(String city){
			return ( root, query, criteriaBuilder ) ->
					criteriaBuilder.like(root.get("city"), "%" + city + "%");
		}

		static Specification<User> likeState(String state){
			return ( root, query, criteriaBuilder ) ->
					criteriaBuilder.like(root.get("state"), "%" + state + "%");
		}

		static Specification<User> equalActive(String active){
			return ( root, query, criteriaBuilder ) ->
					criteriaBuilder.equal(root.get("active"), active);
		}


		static Specification<User> createdOn( Specification<User> spec) {
			return (root, query, builder) ->
				spec.toPredicate(root, query, builder);
		}

	}


}
