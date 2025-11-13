/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package api.worshipass.controller;

import api.worshipass.domain.Evento;
import api.worshipass.service.EventoService;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author David
 */
@RestController
@RequestMapping("/eventos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EventoController {

    private final EventoService eventoService;

    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @GetMapping
    public ResponseEntity<List<Evento>> getAll() {
        return ResponseEntity.ok(eventoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evento> getOne(@PathVariable Integer id) {
        Evento evento = eventoService.findById(id);

        return ResponseEntity.ok(evento);
    }

    @PostMapping
    public ResponseEntity<Evento> create(@RequestBody Evento evento) {
        Evento salvo = eventoService.create(evento);

        return ResponseEntity
                .created(URI.create("/eventos/" + salvo.getId()))
                .body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Evento> update(@PathVariable Integer id, @RequestBody Evento evento) {
        Evento atualizado = eventoService.update(id, evento);

        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        eventoService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/generateTickets/{id}")
    ResponseEntity<String> generateTickets(@PathVariable Integer id) {
        eventoService.generateTickets(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/hoje")
    public ResponseEntity<List<Evento>> getToday() {
        List<Evento> eventos = eventoService.findToday();
        return ResponseEntity.ok(eventos);
    }
}
