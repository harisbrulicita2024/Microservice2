package harisbrulicita2024.model

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(schema = "users", name = "users")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var userId: Long? = null,

    var name: String = "", // Defaultna vrednost prazan string

    var surname: String = "", // Defaultna vrednost prazan string

    var email: String = "", // Defaultna vrednost prazan string

    var password: String = "", // Defaultna vrednost prazan string

    var birthdate: LocalDate = LocalDate.now(),

    )