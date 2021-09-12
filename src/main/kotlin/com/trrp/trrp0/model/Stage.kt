package com.trrp.trrp0.model

import javax.persistence.*

@Entity
@Table(name = "t_stage")
data class Stage(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stage_id")
    val id: Long = 0L,

    @Column(name = "stage_name")
    val name: String = "",

    @ManyToOne
    @JoinColumn(name = "champ_id", referencedColumnName = "champ_id")
    val championship: Championship? = null,

    @OneToOne
    @JoinColumn(name = "city_id", referencedColumnName = "city_id")
    val city: City? = null
)