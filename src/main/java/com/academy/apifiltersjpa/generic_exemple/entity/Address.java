package com.academy.apifiltersjpa.generic_exemple.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "address")
public class Address implements Serializable {
		@Id
		@GeneratedValue
        @Column(name = "id")
		private Long addressId;

		private String city;

}
