package com.academy.apifiltersjpa.generic_exemple.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
@Entity
@Table(name = "student")
public class Student implements Serializable {

		@Id
        @GeneratedValue
		@Column(name = "id")
		private Long id;

		@Column(unique = true)
		private String name;

		@OneToOne
		@JoinColumn(name = "address_id", referencedColumnName = "id")
		private Address address;

		@OneToMany(mappedBy = "studentsId")
		private Set<Subject> subjects;

}
