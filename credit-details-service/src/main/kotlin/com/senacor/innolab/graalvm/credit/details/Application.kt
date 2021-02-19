package com.senacor.innolab.graalvm.credit.details

import io.micronaut.runtime.Micronaut.*

object Application {

	@JvmStatic
	fun main(args: Array<String>) {
		build()
			.args(*args)
			.packages("com.senacor.innolab.graalvm.credit.details")
			.start()
	}
}