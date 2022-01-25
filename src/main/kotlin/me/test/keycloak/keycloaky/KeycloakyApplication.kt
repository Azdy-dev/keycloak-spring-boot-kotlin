package me.test.keycloak.keycloaky

import org.keycloak.adapters.KeycloakConfigResolver
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class KeycloakyApplication

fun main(args: Array<String>) {
    runApplication<KeycloakyApplication>(*args)


}
