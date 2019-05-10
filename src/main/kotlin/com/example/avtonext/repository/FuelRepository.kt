package com.example.avtonext.repository

import com.example.avtonext.model.Fuel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FuelRepository : JpaRepository<Fuel, Long>