package com.example.avtonext.model

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.JsonIdentityReference
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
data class Car (
		@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
		val id: Long = 0,

		@ManyToOne
		@get: NotNull(message = "Please enter modelId")
		@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator::class, property = "id")
		val mark: Mark,

		@get: NotNull(message = "Please enter modelId")
		val modelId: Long = 0,

		@get: NotNull(message = "Please enter fuelId")
		val fuelId: Long = 0,

		@get: NotNull(message = "Please enter seat type")
		val seatId: Long = 0,

		@get: NotNull(message = "Please enter doorsId")
		val doorsId: Long = 0,

		@get: NotBlank(message = "Please enter type")
		val type: String = "",

		@get: NotNull(message = "Please enter price")
		val price: Double = 0.0,

		@get: NotNull(message = "Please enter mileage")
		val mileage: Double = 0.0,

		@get: NotNull(message = "Please enter ccm")
		val ccm: Long = 0,

		@get: NotNull(message = "Please enter power")
		val power: Long = 0
)