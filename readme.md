# Calculator - Test Engineer Code Test

This application is intended to be used as a coding test for test engineer candidates.

## Expectations
* The candidate should add test coverage to the application as they see fit
* During the interview, the candidate will walk us through the tests which they wrote, and is expected to explain what they did and why they took the approach that they did.
* Bonus points for any bugs that are found :)

## Application Description
This application exposes a simple calculator via a RESTful interface. The following endpoints are exposed:
#### Addition
`http://localhost:8080/calculator/sum?augend={value}&addend={value}`
#### Subtraction
`http://localhost:8080/calculator/difference?minuend={value}&subtrahend={value}`
#### Multiplication
`http://localhost:8080/calculator/product?multiplicand={value}&multiplier={value}`
#### Division
`http://localhost:8080/calculator/quotient?dividend={value}&divisor={value}`

### Prerequisites
* Java 8

### Building the project
`./mvnw clean install`

### Running the application
`./mvnw spring-boot:run`
... or it may be run using your favourite IDE

### Running the tests
`./mvnw test`
... or they may be run using your favourite IDE
