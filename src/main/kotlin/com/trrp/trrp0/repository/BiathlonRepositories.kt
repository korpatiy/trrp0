package com.trrp.trrp0.repository

import com.trrp.trrp0.model.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ChampionshipRepository : JpaRepository<Championship, Long>{
    fun findByName(name: String): Championship?
}

@Repository
interface CityRepository : JpaRepository<City, Long>{
    fun findByName(name: String): City?
}

@Repository
interface CountryRepository : JpaRepository<Country, Long>{
    fun findByName(name: String): Country?
}

@Repository
interface DisciplineRepository : JpaRepository<Discipline, Long>{
    fun findByName(name: String): Discipline?
}

@Repository
interface RaceRepository : JpaRepository<Race, Long>

@Repository
interface StageRepository : JpaRepository<Stage, Long>{
    fun findByName(name: String): Stage?
}

@Repository
interface TrackRepository : JpaRepository<Track, Long>{
    fun findByLengthAndLocation(length: Int, location: String): Track?
}

