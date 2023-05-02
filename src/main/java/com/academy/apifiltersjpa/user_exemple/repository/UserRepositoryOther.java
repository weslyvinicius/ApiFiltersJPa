package com.academy.apifiltersjpa.user_exemple.repository;

import com.academy.apifiltersjpa.user_exemple.controller.UserCriteria;
import com.academy.apifiltersjpa.user_exemple.entity.ActiveDisableEnum;
import com.academy.apifiltersjpa.user_exemple.entity.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface UserRepositoryOther extends PagingAndSortingRepository<User, Long>, JpaSpecificationExecutor<User> {

	interface Specs {

		static Specification<User> likeName( String name ) {
			return ( root, query, criteriaBuilder ) ->
					criteriaBuilder.like( root.get( "name" ), "%" + name + "%" );
		}

		static Specification<User> equalDateBirthday( LocalDate dateBirthday ) {
			return ( root, query, criteriaBuilder ) ->
					criteriaBuilder.equal( root.get( "dateBirthday" ), dateBirthday );
		}

		static Specification<User> betweenAges( Integer ageInitial, Integer ageEnd ) {
			return ( root, query, criteriaBuilder ) ->
					criteriaBuilder.between( root.get( "age" ), ageInitial, ageEnd );
		}

		static Specification<User> likeCity( String city ) {
			return ( root, query, criteriaBuilder ) ->
					criteriaBuilder.like( root.get( "city" ), "%" + city + "%" );
		}

		static Specification<User> likeState( String state ) {
			return ( root, query, criteriaBuilder ) ->
					criteriaBuilder.like( root.get( "state" ), "%" + state + "%" );
		}

		static Specification<User> equalActive( ActiveDisableEnum active ) {
			return ( root, query, criteriaBuilder ) ->
					criteriaBuilder.equal( root.get( "active" ), active );
		}

		static Specification<User> dynamicQuery( UserCriteria userCriteria ) {

			Specification<User> spec = Specification.where( null );

			// Adicione as condições desejadas de acordo com os campos preenchidos no userCriteria
			if ( userCriteria.name() != null && !userCriteria.name().isEmpty() ) {
				spec = spec.and( likeName( userCriteria.name() ) );
			}
			if ( userCriteria.dateBirthday() != null ) {
				spec = spec.and( equalDateBirthday( userCriteria.dateBirthday() ) );
			}
			if ( userCriteria.ageInitial() != null && userCriteria.ageEnd() != null ) {
				spec = spec.and( betweenAges( userCriteria.ageInitial(), userCriteria.ageEnd() ) );
			}
			if ( userCriteria.city() != null && !userCriteria.city().isEmpty() ) {
				spec = spec.and( likeCity( userCriteria.city() ) );
			}
			if ( userCriteria.state() != null && !userCriteria.state().isEmpty() ) {
				spec = spec.and( likeState( userCriteria.state() ) );
			}
			if ( userCriteria.active() != null ) {
				spec = spec.and( equalActive( userCriteria.active() ) );
			}

			return spec;

		}

	}
}
