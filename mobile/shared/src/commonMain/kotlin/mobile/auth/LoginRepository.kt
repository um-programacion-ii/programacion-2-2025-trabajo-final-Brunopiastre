package mobile.auth

class LoginRepository {
    suspend fun login(username: String, password: String): Boolean {
        // se integrará luego con el backend
        return username.isNotBlank() && password.isNotBlank()
    }
}
