package com.academy.apifiltersjpa.generic_exemple.service;

import com.academy.apifiltersjpa.generic_exemple.dto.GlobalOperationEnum;
import com.academy.apifiltersjpa.generic_exemple.dto.OperationEnum;
import com.academy.apifiltersjpa.generic_exemple.dto.SearchRequestDTO;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilterSpecification<T> {

	public Specification<T> getSearchSpecification( List<SearchRequestDTO> searchRequestDTOList, GlobalOperationEnum globalOperationEnum ){
		return ( root, query, criteriaBuilder ) -> {

			List<Predicate> predicates = searchRequestDTOList.stream()
					.map( searchRequestDTO ->
                    	  switch (searchRequestDTO.operation()) {
							  case EQUAL -> criteriaBuilder.equal( root.get( searchRequestDTO.column() ), searchRequestDTO.value() );
							  case LIKE -> criteriaBuilder.like( root.get( searchRequestDTO.column() ), "%"+ searchRequestDTO.value() +"%" );
							  case IN -> root.get( searchRequestDTO.column()).in( searchRequestDTO.value().split( "," ) );
							  case GREATER_THAN ->  criteriaBuilder.greaterThan( root.get( searchRequestDTO.column() ), searchRequestDTO.value() );
							  case LESS_THAN -> criteriaBuilder.lessThan( root.get( searchRequestDTO.column() ), searchRequestDTO.value() );
							  case BETWEEN -> criteriaBuilder.between( root.get( searchRequestDTO.column() ), searchRequestDTO.value().split( "," )[0], searchRequestDTO.value().split( "," )[1] );
							  case JOIN -> criteriaBuilder.equal( root.join( searchRequestDTO.joinTable() ).get(searchRequestDTO.column()), searchRequestDTO.value());
						  }
					)
					.collect( Collectors.toList() );

			if(globalOperationEnum.equals(GlobalOperationEnum.AND )){
				return criteriaBuilder.and( predicates.toArray( new Predicate[0]));
			}else {
				return criteriaBuilder.or( predicates.toArray( new Predicate[0]));
			}


		};
	}

}
