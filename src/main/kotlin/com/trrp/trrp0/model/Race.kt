package com.trrp.trrp0.model

import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "t_race")
data class Race(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "race_id")
    val id: Long = 0L,

    val sex: String = "",

    val date: LocalDate = LocalDate.MIN,

    val start_time: LocalDateTime = LocalDateTime.now()


)