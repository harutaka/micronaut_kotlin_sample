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
class HelloWorld(@Client("https://jsonplaceholder.typicode.com") private val client: HttpClient) {
    @Serdeable
    data class RequestBody(val name: String)
    
    @Serdeable
    data class TodoResponse(val userId: Int, val id: Int, val title: String, val completed: Boolean)
    
    @Serdeable
    data class CustomResponse(val id: Int, val title: String)

    @Get
    fun getTodo(): CustomResponse {
        val request = HttpRequest.GET<Any>("/todos/1")
        val response = client.toBlocking().retrieve(request, TodoResponse::class.java)
        return CustomResponse(response.id, response.title)
    }

    @Post
    fun createGreeting(@Body body: RequestBody): String {
        val numbers = listOf(1, 2, 3, 4, 5)

        var max = numbers[0]
        for (num in numbers) {
            if (num > max) {
                max = num
            }
        }
        
        return "Hello ${body.name} ${max.toString()}"
    }
}
