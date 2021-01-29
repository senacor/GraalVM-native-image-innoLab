package micronaut.demo

import io.micronaut.runtime.Micronaut.*

object Application {

	@JvmStatic
	fun main(args: Array<String>) {
		build()
			.args(*args)
			.packages("micronaut.demo")
			.start()
	}
}
