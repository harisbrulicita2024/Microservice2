import harisbrulicita2024.CreateUserRequest
import harisbrulicita2024.UserResponse
import harisbrulicita2024.UserServiceGrpc
import harisbrulicita2024.model.User
import harisbrulicita2024.repository.UserRepository
import io.quarkus.grpc.GrpcClient
import io.quarkus.test.InjectMock
import io.quarkus.test.junit.QuarkusTest
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.mockito.Mockito

@QuarkusTest
class UserServiceTest {

    @InjectMock
    lateinit var userRepository: UserRepository

    @GrpcClient("UserService")
    lateinit var userServiceBlockingStub: UserServiceGrpc.UserServiceBlockingStub

    @Test
    fun `create user should create user and return user response`() {
        val request = CreateUserRequest.newBuilder()
            .setName("Test")
            .setSurname("User")
            .setEmail("test@example.com")
            .setPassword("passwd")
            .setBirthdate("1990-01-01")
            .build()


        val response: UserResponse = userServiceBlockingStub.createUser(request)

        assertNotNull(response)
        assertNotNull(response.id)
    }
}
