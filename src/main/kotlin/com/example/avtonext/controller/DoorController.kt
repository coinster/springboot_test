package com.example.avtonext.controller

import com.example.avtonext.model.Door
import com.example.avtonext.repository.DoorRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class DoorController(private val doorRepository: DoorRepository) {

    @GetMapping("/doors")
    fun getAllDoors(): List<Door> =
            doorRepository.findAll()


    @PostMapping("/doors")
    fun createNewDoor(@Valid @RequestBody door: Door): Door =
            doorRepository.save(door)


    @GetMapping("/doors/{id}")
    fun getDoorById(@PathVariable(value = "id") doorId: Long): ResponseEntity<Door> {
        return doorRepository.findById(doorId).map { door ->
            ResponseEntity.ok(door)
        }.orElse(ResponseEntity.notFound().build())
    }

    @PutMapping("/doors/{id}")
    fun updateDoorById(@PathVariable(value = "id") doorId: Long,
                          @Valid @RequestBody newDoor: Door): ResponseEntity<Door> {

        return doorRepository.findById(doorId).map { existingDoor ->
            val updatedDoor: Door = existingDoor
                    .copy(name = newDoor.name)
            ResponseEntity.ok().body(doorRepository.save(updatedDoor))
        }.orElse(ResponseEntity.notFound().build())

    }

    @DeleteMapping("/doors/{id}")

    fun deleteDoorById(@PathVariable(value = "id") doorId: Long): ResponseEntity<Void> {
        return doorRepository.findById(doorId).map { door  ->
            doorRepository.delete(door)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())

    }
}