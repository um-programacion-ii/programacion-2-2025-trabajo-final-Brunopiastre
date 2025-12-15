package ar.edu.um.ticketflow.backend.domain.port.in;

import ar.edu.um.ticketflow.backend.domain.Evento;

import java.util.List;
import java.util.Optional;

/**
 * Puerto de entrada para operaciones sobre eventos.
 * Los controladores y otros adapters deberían depender de esta interfaz.
 */
public interface EventoUseCase {

    /**
     * Lista todos los eventos disponibles.
     */
    List<Evento> listarTodos();

    /**
     * Busca un evento por su ID interno.
     */
    Optional<Evento> buscarPorId(Long id);

    /**
     * Busca un evento por el id de cátedra (identificador externo).
     */
    Optional<Evento> buscarPorIdCatedra(String idCatedra);

    /**
     * Crea o actualiza un evento.
     */
    Evento guardar(Evento evento);
}
