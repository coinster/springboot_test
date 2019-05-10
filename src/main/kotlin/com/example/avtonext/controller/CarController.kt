package com.example.avtonext.controller

import com.example.avtonext.model.Car
import com.example.avtonext.repository.CarRepository

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class CarController(private val carRepository: CarRepository) {

    @GetMapping("/cars")
    fun getAllCars(): List<Car> =
            carRepository.findAll()

    @PostMapping("/cars")
    fun createNewCar(@Valid @RequestBody car: Car): Car =
            carRepository.save(car)


    @GetMapping("/cars/{id}")
    fun getCarById(@PathVariable(value = "id") carId: Long): ResponseEntity<Car> {
        return carRepository.findById(carId).map { car ->
            ResponseEntity.ok(car)
        }.orElse(ResponseEntity.notFound().build())
    }

    @PutMapping("/cars/{id}")
    fun updateCarById(@PathVariable(value = "id") carId: Long,
                          @Valid @RequestBody newCar: Car): ResponseEntity<Car> {

        return carRepository.findById(carId).map { existingCar ->
            val updatedCar: Car = existingCar
                    .copy(
                            mark = newCar.mark,
                            modelId = newCar.modelId,
                            type = newCar.type,
                            price = newCar.price,
                            mileage = newCar.mileage,
                            fuelId = newCar.fuelId,
                            seatId = newCar.seatId,
                            ccm = newCar.ccm,
                            power = newCar.power,
                            doorsId = newCar.doorsId
                    )
            ResponseEntity.ok().body(carRepository.save(updatedCar))
        }.orElse(ResponseEntity.notFound().build())

    }

    @DeleteMapping("/cars/{id}")
    fun deleteCarById(@PathVariable(value = "id") carId: Long): ResponseEntity<Void> {

        return carRepository.findById(carId).map { car  ->
            carRepository.delete(car)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())

    }
}