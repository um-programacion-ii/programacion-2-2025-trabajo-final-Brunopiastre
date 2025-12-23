package ar.edu.um.ticketflow.backend.payment.domain;

/**
 * Estados posibles de un pago en el sistema.
 * Se mantiene mapeo como String en entidad para compatibilidad.
 */
public enum PaymentStatus {
    PENDING,
    APPROVED,
    REJECTED,
    CANCELED
}
