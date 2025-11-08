/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package api.worshipass.controller;

import api.worshipass.domain.Lanche;
import api.worshipass.service.LancheService;
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
@RequestMapping("/lanches")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LancheController {
    private final LancheService lancheService;

    public LancheController(LancheService lancheService) {
        this.lancheService = lancheService;
    }

    @GetMapping
    public ResponseEntity<List<Lanche>> getAll() {
        return ResponseEntity.ok(lancheService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lanche> getOne(@PathVariable Integer id) {
        Lanche lanche = lancheService.findById(id);
        
        return ResponseEntity.ok(lanche);
    }

    @PostMapping
    public ResponseEntity<Lanche> create(@RequestBody Lanche lanche) {
        Lanche salvo = lancheService.create(lanche);
        
        return ResponseEntity
                .created(URI.create("/lanches/" + salvo.getId()))
                .body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Lanche> update(@PathVariable Integer id, @RequestBody Lanche lanche) {
        Lanche atualizado = lancheService.update(id, lanche);
        
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        lancheService.delete(id);
        
        return ResponseEntity.noContent().build();
    }
}
