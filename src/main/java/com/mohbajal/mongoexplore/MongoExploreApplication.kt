package com.mohbajal.mongoexplore

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan

@EnableAutoConfiguration
@ComponentScan
class MongoExploreApplication {

}

fun main(args: Array<String>) {
    SpringApplication.run(MongoExploreApplication::class.java, *args)
}