package dk.laj.samples.gracefullyshutdown

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.ApplicationListener
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Scope
import org.springframework.context.event.ContextClosedEvent


@SpringBootApplication
class GracefullyShutdownApplication : ApplicationListener<ContextClosedEvent> {
	@Autowired lateinit var appState: ApplicationState

    override fun onApplicationEvent(event: ContextClosedEvent) {
        println("I'm shutting down restendpoint still working")
		appState.shuttingDown = true
        Thread.sleep(30000)
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    fun createApplicationState(): ApplicationState {
        return ApplicationState();
    }


}

fun main(args: Array<String>) {
    runApplication<GracefullyShutdownApplication>(*args)


}

