package micronaut_kotlin_sample

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Body
import io.micronaut.serde.annotation.Serdeable
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.http.HttpRequest
import io.micronaut.scheduling.TaskExecutors
import io.micronaut.scheduling.annotation.ExecuteOn
import jakarta.inject.Inject

@Controller("/hello")
@ExecuteOn(TaskExecutors.BLOCKING)
class HelloWorld {
    @Inject
    @field:Client("https://jsonplaceholder.typicode.com")
    lateinit var httpClient: HttpClient
    
    @Serdeable
    data class RequestBody(val name: String)
    
    @Serdeable
    data class TodoResponse(val userId: Int, val id: Int, val title: String, val completed: Boolean)
    
    @Serdeable
    data class CustomResponse(val id: Int, val title: String)

    @Get
    fun get(): CustomResponse {
        val response = httpClient.toBlocking().retrieve(
            HttpRequest.GET<Any>("/todos/1"),
            TodoResponse::class.java
        )
        return CustomResponse(response.id, response.title)
    }

    @Post
    fun post(@Body body: RequestBody): String {
        return "Hello ${body.name}"
    }
}
