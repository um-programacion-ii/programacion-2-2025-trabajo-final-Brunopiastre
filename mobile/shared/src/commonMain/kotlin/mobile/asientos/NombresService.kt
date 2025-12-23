package mobile.asientos

class NombresService {

    private val nombres = mutableMapOf<String, String>()

    fun asignarNombre(asiento: Asiento, nombre: String) {
        nombres["${asiento.fila}-${asiento.columna}"] = nombre
    }

    fun obtenerNombre(asiento: Asiento): String? =
        nombres["${asiento.fila}-${asiento.columna}"]
}
