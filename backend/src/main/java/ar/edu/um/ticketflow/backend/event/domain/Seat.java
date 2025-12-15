package ar.edu.um.ticketflow.backend.event.domain;

/**
 * Value object que representa una ubicación dentro del evento.
 */
public class Seat {
    private int row;
    private int column;
    private SeatState state;

    public Seat() {
    }

    public Seat(int row, int column, SeatState state) {
        this.row = row;
        this.column = column;
        this.state = state;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public SeatState getState() {
        return state;
    }

    public void setState(SeatState state) {
        this.state = state;
    }

    public enum SeatState {
        FREE, BLOCKED, SOLD, SELECTED
    }
}
