package micronaut_kotlin_sample

import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import io.restassured.specification.RequestSpecification
import org.junit.jupiter.api.Test

import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.CoreMatchers.`is`

@MicronautTest
class HelloWorldTest {
    @Test
    fun testGetTodo(spec: RequestSpecification) {
        spec
            .`when`()
                .get("/hello")
            .then()
                .statusCode(200)
                .body(notNullValue())
    }

    @Test
    fun testCreateGreeting(spec: RequestSpecification) {
        spec
            .given()
                .header("Content-Type", "application/json")
                .body("{\"name\": \"John\"}")
            .`when`()
                .post("/hello")
            .then()
                .statusCode(200)
                .body(`is`("Hello John"))
    }
}
