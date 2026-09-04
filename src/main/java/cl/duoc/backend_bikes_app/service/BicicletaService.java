package cl.duoc.backend_bikes_app.service;
import cl.duoc.backend_bikes_app.model.Bicicleta;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class BicicletaService {

    private final Map<Long, Bicicleta> inventario = new ConcurrentHashMap<>();
    private final AtomicLong secuenciaId = new AtomicLong(0);

    @PostConstruct
    public void cargarDatosDeEjemplo() {
        crear(new Bicicleta(null, "Montaña Pro", "Trailmax", 46, 12));
        crear(new Bicicleta(null, "Urbana Classic", "Ciclón", 50, 20));
        crear(new Bicicleta(null, "Ruta Aero", "Trailmax", 54, 5));
    }

    public List<Bicicleta> listarTodas() {
        return inventario.values().stream()
                .sorted((a, b) -> Long.compare(a.getId(), b.getId()))
                .collect(Collectors.toList());
    }

    public Bicicleta buscarPorId(Long id) {
        Bicicleta bicicleta = inventario.get(id);
        if (bicicleta == null) {
            throw new BicicletaNoEncontradaException(id);
        }
        return bicicleta;
    }

    public Bicicleta crear(Bicicleta nueva) {
        long id = secuenciaId.incrementAndGet();
        nueva.setId(id);
        inventario.put(id, nueva);
        return nueva;
    }

    public Bicicleta actualizar(Long id, Bicicleta datos) {
        Bicicleta existente = buscarPorId(id);
        existente.setModelo(datos.getModelo());
        existente.setMarca(datos.getMarca());
        existente.setTalla(datos.getTalla());
        existente.setStock(datos.getStock());
        return existente;
    }

    public void eliminar(Long id) {
        if (!inventario.containsKey(id)) {
            throw new BicicletaNoEncontradaException(id);
        }
        inventario.remove(id);
    }
}