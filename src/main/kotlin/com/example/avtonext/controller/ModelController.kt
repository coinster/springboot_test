package com.example.avtonext.controller

import com.example.avtonext.model.Model
import com.example.avtonext.repository.ModelRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class ModelController(private val modelRepository: ModelRepository) {

    @GetMapping("/models")
    fun getAllModels(): List<Model> =
            modelRepository.findAll()


    @PostMapping("/models")
    fun createNewModel(@Valid @RequestBody model: Model): Model =
            modelRepository.save(model)


    @GetMapping("/models/{id}")
    fun getModelById(@PathVariable(value = "id") modelId: Long): ResponseEntity<Model> {
        return modelRepository.findById(modelId).map { model ->
            ResponseEntity.ok(model)
        }.orElse(ResponseEntity.notFound().build())
    }

    @PutMapping("/models/{id}")
    fun updateModelById(@PathVariable(value = "id") modelId: Long,
                          @Valid @RequestBody newModel: Model): ResponseEntity<Model> {

        return modelRepository.findById(modelId).map { existingModel ->
            val updatedModel: Model = existingModel
                    .copy(name = newModel.name)
            ResponseEntity.ok().body(modelRepository.save(updatedModel))
        }.orElse(ResponseEntity.notFound().build())

    }

    @DeleteMapping("/models/{id}")

    fun deleteModelById(@PathVariable(value = "id") modelId: Long): ResponseEntity<Void> {
        return modelRepository.findById(modelId).map { model  ->
            modelRepository.delete(model)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())

    }
}