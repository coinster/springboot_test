package com.example.avtonext.model

import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
@Table(name="seat")
data class Seat (
		@get: NotBlank(message = "Please enter seat type")
		val name: String = "",

		@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
		val id: Long = 0
)