package com.performancetest.calculator.scenario

import com.performancetest.calculator.{Calculator, Constants}
import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation

class GetDifferenceTest extends Simulation {

  val getSum =
    scenario("Get Sum Load Test")
      .exec(
        Calculator.getDifference
      )
      .inject(
        rampUsers(100) during(5)
      )

  setUp(getSum)
    .protocols(Constants.httpProtocolBase)
    .assertions(
      global.successfulRequests.percent.is(100),
      global.responseTime.max.lt(1000)
    )
}