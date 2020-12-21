# Gemography backend-coding-challenge

[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)](https://opensource.org/licenses/MIT)

## General Info
1. The task is implemented using Java as programming language and [Spring boot](http://spring.io/projects/spring-boot) as back-end framework.
2. I used [IntelliJ IDEA](https://www.jetbrains.com/idea/) as IDE.

## Dependencies 
- Java 11
- [Lombok](https://projectlombok.org/)
## Installation and setup
1. Unzip the compressed file to any where on your computer if you download or just clone the repository.
2. Navigate to the cloned/downloaded folder and open it using your favourite IDE but but I prefer [IntelliJ IDEA](https://www.jetbrains.com/idea/)

## Running

#### Maven
You can run the project using gradle but make sure it is installed in your machine or configured in your IDE through the following command
`$ mvn spring-boot:run` 

#### IDE - Jetbrains Intellij
You can run the application directly from intellij but make sure you are using Java 11

Start the application from JAR file  
    ```
    $ java -jar build/libs/trending-0.0.1-SNAPSHOT.jar
    ```

#### Docker
- You can run the project through docker either by building the image from Dockerfile then running the container through the following commands
    ```
    docker build -t terndingrepos .
    ```
    ```
    docker run -p 8080:8080 terndingrepos
    ```
## Usage & available end-points
There are five end-points available in this project

| Method        | Endpoint                          | Body            |
| ------------- |:---------------------------------:| --------------- |   
| GET           | localhost:8080/api/v1/languages   | no body          |


- By using your favorite HTTP client **I recommend using POSTMAN** or by using cURL or Swagger, you can use and test the endpoint.

## Swagger API documentation
The available end-point is documented by swagger through this link 

- `http://localhost:8080/swagger-ui.html`
- `http://gemographytrendingrepos-env.eba-knpd42qx.us-east-1.elasticbeanstalk.com/swagger-ui.html#/`

## Deployment
* The project is deployed on [Amazon web services](https://aws.amazon.com/) [using Elastic Beanstalk](https://aws.amazon.com/elasticbeanstalk/)
* AWS endpoint 
    ```
    http://gemographytrendingrepos-env.eba-knpd42qx.us-east-1.elasticbeanstalk.com/api/v1/languages/
    ```

## Author
[Mohamed Kashif](mailto:mohammedd.kashiff@gmail.com)