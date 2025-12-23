package mobile.asientos

data class Asiento(
    val fila: Int,
    val columna: Int,
    var bloqueado: Boolean = false
)

class BloqueoAsientosMobile {

    private val seleccionados = mutableListOf<Asiento>()

    fun seleccionar(asiento: Asiento) {
        asiento.bloqueado = true
        seleccionados.add(asiento)
    }

    fun obtenerSeleccionados(): List<Asiento> = seleccionados
}
