package com.performancetest.calculator

import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._

object Calculator {
  val getSum: ChainBuilder =
    exec(
      http("Get Sum")
        .get("/calculator/sum?augend=1&addend=1")
        .check(status is 200)
    )

  val getDifference: ChainBuilder =
    exec(
      http("Get Difference")
        .get("/calculator/difference?minuend=1&subtrahend=1")
        .check(status is 200)
    )

  val getProduct: ChainBuilder =
    exec(
      http("Get Product")
        .get("/calculator/product?multiplicand=1&multiplier=1")
        .check(status is 200)
    )

  val getQuotient: ChainBuilder =
    exec(
      http("Get Quotient")
        .get("/calculator/quotient?dividend=1&divisor=1")
        .check(status is 200)
    )
}