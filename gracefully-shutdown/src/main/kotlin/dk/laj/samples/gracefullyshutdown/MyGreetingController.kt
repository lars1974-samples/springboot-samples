package dk.laj.samples.gracefullyshutdown

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class MyGreetingController @Autowired constructor(
        val appState: ApplicationState
) {
    @GetMapping("/greeting")
    fun greeting(@RequestParam(value = "name", defaultValue = "World") name: String) = "Hello, $name shutting down: ${appState.shuttingDown}"
}