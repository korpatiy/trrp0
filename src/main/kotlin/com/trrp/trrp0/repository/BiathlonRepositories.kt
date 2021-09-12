package com.trrp.trrp0.repository

import com.trrp.trrp0.model.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ChampionshipRepository : JpaRepository<Championship, Long>

@Repository
interface CityRepository : JpaRepository<City, Long>

@Repository
interface CountryRepository : JpaRepository<Country, Long>{
    fun findByName(name: String): Country
}

@Repository
interface DisciplineRepository : JpaRepository<Discipline, Long>

@Repository
interface RaceRepository : JpaRepository<Race, Long>

@Repository
interface StageRepository : JpaRepository<Stage, Long>

@Repository
interface TrackRepository : JpaRepository<Track, Long>

