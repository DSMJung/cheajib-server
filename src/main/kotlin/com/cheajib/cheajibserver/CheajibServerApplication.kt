package com.cheajib.cheajibserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@ConfigurationPropertiesScan
@EnableFeignClients
@SpringBootApplication
class CheajibServerApplication

fun main(args: Array<String>) {
    runApplication<CheajibServerApplication>(*args)
}
