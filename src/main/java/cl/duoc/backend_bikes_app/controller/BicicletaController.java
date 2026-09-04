package cl.duoc.backend_bikes_app.controller;

import cl.duoc.backend_bikes_app.model.Bicicleta;
import cl.duoc.backend_bikes_app.service.BicicletaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bicicletas")
public class BicicletaController {

    private final BicicletaService bicicletaService;

    public BicicletaController(BicicletaService bicicletaService) {
        this.bicicletaService = bicicletaService;
    }

    @GetMapping
    public List<Bicicleta> listar() {
        return bicicletaService.listarTodas();
    }

    @GetMapping("/{id}")
    public Bicicleta obtener(@PathVariable Long id) {
        return bicicletaService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Bicicleta crear(@Valid @RequestBody Bicicleta nueva) {
        return bicicletaService.crear(nueva);
    }

    @PutMapping("/{id}")
    public Bicicleta actualizar(@PathVariable Long id, @Valid @RequestBody Bicicleta datos) {
        return bicicletaService.actualizar(id, datos);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        bicicletaService.eliminar(id);
    }
}
