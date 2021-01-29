package micronaut.demo

import io.micronaut.runtime.Micronaut.*
fun main(args: Array<String>) {
	build()
	    .args(*args)
		.packages("micronaut.demo")
		.start()
}
