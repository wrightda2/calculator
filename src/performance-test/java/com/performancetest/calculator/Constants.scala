package com.performancetest.calculator

import com.performancetest.calculator.context.TestProperties
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder

object Constants {

  val httpProtocolBase: HttpProtocolBuilder = http
    .baseUrl(TestProperties.getAsString("base.url"))

}
