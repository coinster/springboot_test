package com.example.avtonext.repository

import com.example.avtonext.model.Mark
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MarkRepository : JpaRepository<Mark, Long>