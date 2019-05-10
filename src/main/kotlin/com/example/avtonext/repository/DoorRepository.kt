package com.example.avtonext.repository

import com.example.avtonext.model.Door
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DoorRepository : JpaRepository<Door, Long>