package com.example.avtonext.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.NotBlank

@Entity
data class Fuel (
		@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
		val id: Long = 0,

		@get: NotBlank(message = "Please enter fuel type")
		val name: String = ""
)
