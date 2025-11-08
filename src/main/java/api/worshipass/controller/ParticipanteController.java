/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package api.worshipass.controller;

import api.worshipass.domain.Participante;
import api.worshipass.service.ParticipanteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

/**
 *
 * @author David
 */
@RestController
@RequestMapping("/participantes")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ParticipanteController {
    private final ParticipanteService participanteService;

    public ParticipanteController(ParticipanteService participanteService) {
        this.participanteService = participanteService;
    }

    @GetMapping
    public ResponseEntity<List<Participante>> getAll() {
        return ResponseEntity.ok(participanteService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Participante> getOne(@PathVariable Integer id) {
        Participante participante = participanteService.findById(id);
        
        return ResponseEntity.ok(participante);
    }

    @PostMapping
    public ResponseEntity<Participante> create(@RequestBody Participante participante) {
        Participante salvo = participanteService.create(participante);
        
        return ResponseEntity
                .created(URI.create("/participantes/" + salvo.getId()))
                .body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Participante> update(@PathVariable Integer id, @RequestBody Participante participante) {
        Participante atualizado = participanteService.update(id, participante);
        
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        participanteService.delete(id);
        
        return ResponseEntity.noContent().build();
    }
}
