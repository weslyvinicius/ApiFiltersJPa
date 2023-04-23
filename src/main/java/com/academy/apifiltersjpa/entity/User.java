package com.academy.apifiltersjpa.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "clients")
@Data
public class User {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;

	@Column(name = "date_birthday")
	private LocalDate dateBirthday;

	private int age;

	private String city;

	private String state;

	private ActiveDisableEnum active;

}
