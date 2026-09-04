package cl.duoc.backend_bikes_app.service;

public class BicicletaNoEncontradaException extends RuntimeException {
    public BicicletaNoEncontradaException(Long id) {
        super("No existe una bicicleta con id " + id);
    }
}