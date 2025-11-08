/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package api.worshipass.controller;

import api.worshipass.domain.Ticket;
import api.worshipass.service.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

/**
 *
 * @author David
 */
@RestController
@RequestMapping("/tickets")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    public ResponseEntity<List<Ticket>> getAll() {
        return ResponseEntity.ok(ticketService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getOne(@PathVariable Integer id) {
        Ticket ticket = ticketService.findById(id);
        
        return ResponseEntity.ok(ticket);
    }

    @PostMapping
    public ResponseEntity<Ticket> create(@RequestBody Ticket ticket) {
        Ticket salvo = ticketService.create(ticket);
        
        return ResponseEntity
                .created(URI.create("/tickets/" + salvo.getId()))
                .body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ticket> update(@PathVariable Integer id, @RequestBody Ticket ticket) {
        Ticket atualizado = ticketService.update(id, ticket);
        
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        ticketService.delete(id);
        
        return ResponseEntity.noContent().build();
    }
}
