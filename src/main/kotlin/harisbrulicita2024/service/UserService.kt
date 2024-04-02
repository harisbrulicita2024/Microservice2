package harisbrulicita2024.service

import harisbrulicita2024.*
import harisbrulicita2024.model.User
import harisbrulicita2024.repository.UserRepository
import io.grpc.stub.StreamObserver
import io.quarkus.grpc.GrpcService
import jakarta.inject.Singleton
import jakarta.transaction.Transactional
import java.time.LocalDate

@GrpcService
@Singleton
class UserService(private val userRepository: UserRepository) : UserServiceGrpc.UserServiceImplBase() {

    @Transactional
    override fun createUser(request: CreateUserRequest, responseObserver: StreamObserver<UserResponse>) {
        val user = User(
            name = request.name,
            surname = request.surname,
            email = request.email,
            password = request.password,
            birthdate = LocalDate.parse(request.birthdate)
        )
        userRepository.persist(user)

        val response = UserResponse.newBuilder()
            .setId((user.userId ?: 0).toInt()) // Ako userId je null, koristi 0 kao placeholder
            .setName(user.name)
            .setSurname(user.surname)
            .setEmail(user.email)
            .setPassword(user.password)
            .setBirthdate(user.birthdate.toString())
            .build()

        responseObserver.onNext(response)
        responseObserver.onCompleted()
    }

    override fun getUser(request: GetUserRequest, responseObserver: StreamObserver<UserResponse>) {
        val userId = request.id.toLong() // Konvertujte Int u Long
        val user = userRepository.findById(userId)
        if (user == null) {
            responseObserver.onError(RuntimeException("User not found"))
            return
        }

        val response = UserResponse.newBuilder()
            .setId((user.userId ?: 0).toInt()) // Ponovo, koristi 0 kao placeholder ako je userId null
            .setName(user.name)
            .setSurname(user.surname)
            .setEmail(user.email)
            .setPassword(user.password)
            .setBirthdate(user.birthdate.toString())
            .build()

        responseObserver.onNext(response)
        responseObserver.onCompleted()
    }

    @Transactional
    override fun updateUser(request: UpdateUserRequest, responseObserver: StreamObserver<UserResponse>) {
        val userId = request.id.toLong() // Konvertujte Int u Long
        val user = userRepository.findById(userId) ?: throw RuntimeException("User not found")

        user.apply {
            name = request.name
            surname = request.surname
            email = request.email
            password = request.password
            birthdate = LocalDate.parse(request.birthdate)
        }
        userRepository.persist(user)

        val response = UserResponse.newBuilder()
            .setId((user.userId ?: 0).toInt()) // Ponovo, koristi 0 kao placeholder ako je userId null
            .setName(user.name)
            .setSurname(user.surname)
            .setEmail(user.email)
            .setPassword(user.password)
            .setBirthdate(user.birthdate.toString())
            .build()

        responseObserver.onNext(response)
        responseObserver.onCompleted()
    }

    @Transactional
    override fun deleteUser(request: DeleteUserRequest, responseObserver: StreamObserver<DeleteUserResponse>) {
        val userId = request.id.toLong() // Konvertujte Int u Long
        userRepository.findById(userId)?.let {
            userRepository.delete(it)
            responseObserver.onNext(DeleteUserResponse.newBuilder().setSuccess(true).build())
        } ?: responseObserver.onNext(DeleteUserResponse.newBuilder().setSuccess(false).build())

        responseObserver.onCompleted()
    }

    override fun listUsers(request: ListUserRequest, responseObserver: StreamObserver<ListUserResponse>) {
    }
}
