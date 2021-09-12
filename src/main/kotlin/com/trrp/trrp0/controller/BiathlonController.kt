package com.trrp.trrp0.controller

import com.trrp.trrp0.MigrationService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class BiathlonController(
    val migrationService: MigrationService
) {

    @GetMapping()
    fun go() {
        migrationService.migrate()
    }
}