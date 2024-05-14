package harisbrulicita2024.model

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(schema = "users", name = "users")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var userId: Long? = null,

    var name: String = "",

    var surname: String = "",

    var email: String = "",

    var password: String = "",

    var birthdate: LocalDate = LocalDate.now(),

    )