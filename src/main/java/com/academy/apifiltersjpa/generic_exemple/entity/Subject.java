package com.academy.apifiltersjpa.generic_exemple.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "subject")
public class Subject implements Serializable  {

		@Id
		@GeneratedValue
		@Column(name = "id")
		private Long id;

		private String name;

		@ManyToOne
		@JoinColumn(name = "student_id", referencedColumnName = "id")
		private Student studentsId;

}
