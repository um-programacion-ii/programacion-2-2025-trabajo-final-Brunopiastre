package ar.edu.um.ticketflow.backend.payment.domain;

/**
 * Métodos de pago soportados por el sistema.
 * Nota: El mapeo a base usa actualmente String en entidades; este enum
 * sirve a nivel dominio/validaciones sin romper compatibilidad.
 */
public enum PaymentMethod {
    CASH,
    CARD,
    TRANSFER
}
