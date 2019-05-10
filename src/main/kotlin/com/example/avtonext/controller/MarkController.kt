package com.example.avtonext.controller

import com.example.avtonext.model.Mark
import com.example.avtonext.repository.MarkRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class MarkController(private val markRepository: MarkRepository) {

    @GetMapping("/marks")
    fun getAllMarks(): List<Mark> =
            markRepository.findAll()


    @PostMapping("/marks")
    fun createNewMark(@Valid @RequestBody mark: Mark): Mark =
            markRepository.save(mark)


    @GetMapping("/marks/{id}")
    fun getMarkById(@PathVariable(value = "id") markId: Long): ResponseEntity<Mark> {
        return markRepository.findById(markId).map { mark ->
            ResponseEntity.ok(mark)
        }.orElse(ResponseEntity.notFound().build())
    }

    @PutMapping("/marks/{id}")
    fun updateMarkById(@PathVariable(value = "id") markId: Long,
                          @Valid @RequestBody newMark: Mark): ResponseEntity<Mark> {

        return markRepository.findById(markId).map { existingMark ->
            val updatedMark: Mark = existingMark
                    .copy(name = newMark.name)
            ResponseEntity.ok().body(markRepository.save(updatedMark))
        }.orElse(ResponseEntity.notFound().build())

    }

    @DeleteMapping("/marks/{id}")

    fun deleteMarkById(@PathVariable(value = "id") markId: Long): ResponseEntity<Void> {
        return markRepository.findById(markId).map { mark  ->
            markRepository.delete(mark)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())

    }
}