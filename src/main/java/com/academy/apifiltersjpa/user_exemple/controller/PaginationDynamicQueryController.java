package com.academy.apifiltersjpa.user_exemple.controller;

import com.academy.apifiltersjpa.user_exemple.entity.User;
import com.academy.apifiltersjpa.user_exemple.repository.UserRepository;
import com.academy.apifiltersjpa.user_exemple.repository.UserRepositoryOther;
import com.academy.apifiltersjpa.user_exemple.repository.UserSpecification;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@AllArgsConstructor
@RestController
public class PaginationDynamicQueryController {

	private final UserRepository userRepository;
	private final UserRepositoryOther userRepositoryOther;

	@GetMapping("/paginacao-simples")
	public Page<User> paginacaoSimples(){
		return userRepository.findAll( PageRequest.of( 0,10 ) );
	}

	@GetMapping("/paginacao-com-parametros")
	public Page<User> paginacaoComParametos( Pageable pageable ){
		return userRepository.findAll( pageable );
	}

	@GetMapping("/paginacaoo-com-parametros-default")
	public Page<User> paginacaoComParametosEOrdenacao(@PageableDefault(direction = Sort.Direction.ASC, sort = "name") Pageable pageable ){
		return userRepository.findAll( pageable );
	}

	@GetMapping("/query-dinamica")
	public Page<User> paginacaoComParametosEOrdenacao( UserCriteria userCriteria, Pageable pageable){
		var userSpecification = new UserSpecification( userCriteria );
		return userRepository.findAll(userSpecification, pageable);
	}

	@GetMapping("/query-dinamica2")
	public Page<User> paginacaoComParametosEOrdenacao( UserCriteriaNotClassSpecification userCriteria, Pageable pageable){
		return userRepository.findAll( userCriteria.toSpec() , pageable);
	}

	@GetMapping("/query-dinamica3")
	public Page<User> paginacaoComParametosEOrdenacaoOther( String name, String city, Pageable pageable){
		return userRepositoryOther.findAll(
				Specification.where( UserRepositoryOther.Specs.likeName(name) )
						       .and( UserRepositoryOther.Specs.likeCity( city ) )
		    , pageable);
	}

	@GetMapping("/query-dinamica4")
	public Page<User> paginacaoComParametosEOrdenacaoOther( UserCriteria userCriteria, Pageable pageable){
		return userRepositoryOther.findAll(
				UserRepositoryOther.Specs.dynamicQuery(userCriteria)
				, pageable);
	}

}
