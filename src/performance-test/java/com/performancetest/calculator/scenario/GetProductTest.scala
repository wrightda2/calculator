package com.performancetest.calculator.scenario

import com.performancetest.calculator.{Calculator, Constants}
import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation

class GetProductTest extends Simulation {

  val getSum =
    scenario("Get Sum Load Test")
      .exec(
        Calculator.getProduct
      )
      .inject(
        atOnceUsers(100)
      )

  setUp(getSum)
    .protocols(Constants.httpProtocolBase)
    .assertions(
      global.successfulRequests.percent.is(100),
      global.responseTime.max.lt(1000)
    )
}