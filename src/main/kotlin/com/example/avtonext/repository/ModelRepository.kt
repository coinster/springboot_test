package com.example.avtonext.repository

import com.example.avtonext.model.Model
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ModelRepository : JpaRepository<Model, Long>