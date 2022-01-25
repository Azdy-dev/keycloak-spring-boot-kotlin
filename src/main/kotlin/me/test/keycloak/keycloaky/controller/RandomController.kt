package me.test.keycloak.keycloaky.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.ModelAndView
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import kotlin.random.Random


@Controller
@RestController
@RequestMapping(RandomController.ROOT)
class RandomController {

    companion object {
        const val ROOT = "/"
    }

    @GetMapping("/")
    fun index(model: Model): ModelAndView? {
        return ModelAndView().apply {
            this.viewName = "index"
        }
    }

    @GetMapping("/random")
    fun random(model: Model): ModelAndView? {
        model.addAttribute("int", Random.nextInt().toString())
        model.addAttribute("long", Random.nextLong().toString())

        val modelAndView = ModelAndView()
        modelAndView.viewName = "random"
        return modelAndView
    }

    @GetMapping(path = ["/logout"])
    fun logout(request: HttpServletRequest): ModelAndView? {
        request.logout()
        return ModelAndView().apply {
            this.viewName = "index"
        }
    }
}



