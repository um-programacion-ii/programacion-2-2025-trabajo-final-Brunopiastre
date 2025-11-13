package mobile.eventos

data class Evento(
    val id: Long,
    val titulo: String,
    val fecha: String
)

class EventosRepository {
    suspend fun obtenerEventos(): List<Evento> {
        return listOf(
            Evento(1, "Conferencia Nerd", "2025-11-10"),
            Evento(2, "Otra Conferencia Nerd", "2025-12-12")
        )
    }
}
