/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package api.worshipass.controller;

import api.worshipass.domain.ResgateLanche;
import api.worshipass.service.ResgateLancheService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

/**
 *
 * @author David
 */
@RestController
@RequestMapping("/resgates-lanche")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ResgateLancheController {
    private final ResgateLancheService resgateLancheService;

    public ResgateLancheController(ResgateLancheService resgateLancheService) {
        this.resgateLancheService = resgateLancheService;
    }

    @GetMapping
    public ResponseEntity<List<ResgateLanche>> getAll() {
        return ResponseEntity.ok(resgateLancheService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResgateLanche> getOne(@PathVariable Integer id) {
        ResgateLanche resgate = resgateLancheService.findById(id);
        
        return ResponseEntity.ok(resgate);
    }

    @PostMapping
    public ResponseEntity<ResgateLanche> create(@RequestBody ResgateLanche resgateLanche) {
        ResgateLanche salvo = resgateLancheService.create(resgateLanche);
        
        return ResponseEntity
                .created(URI.create("/resgates-lanche/" + salvo.getId()))
                .body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResgateLanche> update(@PathVariable Integer id, @RequestBody ResgateLanche resgateLanche) {
        ResgateLanche atualizado = resgateLancheService.update(id, resgateLanche);
        
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        resgateLancheService.delete(id);
        
        return ResponseEntity.noContent().build();
    }
}
