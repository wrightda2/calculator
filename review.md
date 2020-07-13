Controller
- variable naming can be improved, does not follow java naming convention
- error handling for boundaries 
- quotient divide by zero error handling (FIXED by adding check to controller layer)
- the parameterized tests utilizes pair wise testing methodology to determine the values
- statement coverage, flow coverage, path testing methodologies were not possible due to the simplicity of the implementation

Testing the Service and Calculator class
- I believe there's a point where over testing is a concern and due to the simplistic nature of these logic layers, I would potentially test them in combination to reduce maintenance

Acceptance Testing
- Although Rest-Assured was used to perform these tests, for a product like WFM, I would opt for a more customizable approach. 
- I feel that although a framework like Rest-Assured is easy to set up, there's a lot of work in setting up complex requests on multiple servers
- 'CalculatorProxyBuilderTest' gives a glimpse of the maintainability of using a bit of custom