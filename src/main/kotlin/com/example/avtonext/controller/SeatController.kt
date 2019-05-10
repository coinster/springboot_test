package com.example.avtonext.controller

import com.example.avtonext.model.Seat
import com.example.avtonext.repository.SeatRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class SeatController(private val seatRepository: SeatRepository) {

    @GetMapping("/seats")
    fun getAllSeats(): List<Seat> =
            seatRepository.findAll()


    @PostMapping("/seats")
    fun createNewSeat(@Valid @RequestBody seat: Seat): Seat =
            seatRepository.save(seat)


    @GetMapping("/seats/{id}")
    fun getSeatById(@PathVariable(value = "id") seatId: Long): ResponseEntity<Seat> {
        return seatRepository.findById(seatId).map { seat ->
            ResponseEntity.ok(seat)
        }.orElse(ResponseEntity.notFound().build())
    }

    @PutMapping("/seats/{id}")
    fun updateSeatById(@PathVariable(value = "id") seatId: Long,
                          @Valid @RequestBody newSeat: Seat): ResponseEntity<Seat> {

        return seatRepository.findById(seatId).map { existingSeat ->
            val updatedSeat: Seat = existingSeat
                    .copy(name = newSeat.name)
            ResponseEntity.ok().body(seatRepository.save(updatedSeat))
        }.orElse(ResponseEntity.notFound().build())

    }

    @DeleteMapping("/seats/{id}")

    fun deleteSeatById(@PathVariable(value = "id") seatId: Long): ResponseEntity<Void> {
        return seatRepository.findById(seatId).map { seat  ->
            seatRepository.delete(seat)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())

    }
}