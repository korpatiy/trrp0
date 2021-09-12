package com.trrp.trrp0

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext

@SpringBootApplication
class Trrp0Application

fun main(args: Array<String>) {
	runApplication<Trrp0Application>(*args)
}
