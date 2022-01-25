package me.test.keycloak.keycloaky.security

import org.keycloak.adapters.KeycloakConfigResolver
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver
import org.keycloak.adapters.springsecurity.KeycloakSecurityComponents
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper
import org.springframework.security.core.session.SessionRegistryImpl
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy


@Configuration
@EnableWebSecurity
@ComponentScan(basePackageClasses = [KeycloakSecurityComponents::class])
internal class SecurityConfig : KeycloakWebSecurityConfigurerAdapter() {


    /**
     * Registers the KeycloakAuthenticationProvider with the authentication manager.
     */
    @Autowired
    fun configureGlobal(auth: AuthenticationManagerBuilder) {
        val keycloakAuthenticationProvider = keycloakAuthenticationProvider()
        keycloakAuthenticationProvider.setGrantedAuthoritiesMapper(SimpleAuthorityMapper())
        auth.authenticationProvider(keycloakAuthenticationProvider)
    }

    /**
     * Defines the session authentication strategy.
     */
    @Bean
    override fun sessionAuthenticationStrategy(): SessionAuthenticationStrategy {
        return RegisterSessionAuthenticationStrategy(SessionRegistryImpl())
    }

    override fun configure(web: WebSecurity) {
        web.ignoring().antMatchers(HttpMethod.GET, "/")
    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        super.configure(http)
        http
            .authorizeRequests()
            .antMatchers("/**").hasRole("user")
            .anyRequest().permitAll()
    }
}