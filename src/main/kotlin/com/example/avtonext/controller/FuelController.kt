package com.example.avtonext.controller

import com.example.avtonext.model.Fuel
import com.example.avtonext.repository.FuelRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class FuelController(private val fuelRepository: FuelRepository) {

    @GetMapping("/fuels")
    fun getAllFuels(): List<Fuel> =
            fuelRepository.findAll()


    @PostMapping("/fuels")
    fun createNewFuel(@Valid @RequestBody fuel: Fuel): Fuel =
            fuelRepository.save(fuel)


    @GetMapping("/fuels/{id}")
    fun getFuelById(@PathVariable(value = "id") fuelId: Long): ResponseEntity<Fuel> {
        return fuelRepository.findById(fuelId).map { fuel ->
            ResponseEntity.ok(fuel)
        }.orElse(ResponseEntity.notFound().build())
    }

    @PutMapping("/fuels/{id}")
    fun updateFuelById(@PathVariable(value = "id") fuelId: Long,
                          @Valid @RequestBody newFuel: Fuel): ResponseEntity<Fuel> {

        return fuelRepository.findById(fuelId).map { existingFuel ->
            val updatedFuel: Fuel = existingFuel
                    .copy(name = newFuel.name)
            ResponseEntity.ok().body(fuelRepository.save(updatedFuel))
        }.orElse(ResponseEntity.notFound().build())

    }

    @DeleteMapping("/fuels/{id}")

    fun deleteFuelById(@PathVariable(value = "id") fuelId: Long): ResponseEntity<Void> {
        return fuelRepository.findById(fuelId).map { fuel  ->
            fuelRepository.delete(fuel)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())

    }
}