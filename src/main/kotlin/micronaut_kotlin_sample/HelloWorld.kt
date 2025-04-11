package micronaut_kotlin_sample

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Body
import io.micronaut.serde.annotation.Serdeable

@Controller("/hello")
class HelloWorld {
    @Serdeable
    data class RequestBody(val name: String)

    @Get
    fun get() = "Hello World"

    @Post
    fun post(@Body body: RequestBody): String {
        return "Hello ${body.name}"
    }
}
