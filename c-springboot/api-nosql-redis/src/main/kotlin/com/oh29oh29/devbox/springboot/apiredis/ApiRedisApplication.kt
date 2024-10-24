package com.oh29oh29.devbox.springboot.apiredis

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(
    scanBasePackages = ["com.oh29oh29.devbox"]
)
class ApiRedisApplication

fun main(args: Array<String>) {
    runApplication<ApiRedisApplication>(*args)
}
