/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package api.worshipass.service;

import api.worshipass.domain.Ticket;
import api.worshipass.repository.TicketRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author David
 */
@Service
public class TicketService {
    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository){
        this.ticketRepository = ticketRepository;
    }

    @Transactional(readOnly = true)
    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Ticket findById(Integer id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket nao encontrado: " + id));
    }

    @Transactional
    public Ticket create(Ticket ticket) {
        ticket.setId(null);
        
        return ticketRepository.save(ticket);
    }

    @Transactional
    public Ticket update(Integer id, Ticket dados) {
        Ticket existente = findById(id);

        existente.setStatus(dados.getStatus());
        existente.setDataEmissao(dados.getDataEmissao());
        existente.setDataUso(dados.getDataUso());
        existente.setParticipante(dados.getParticipante());
        existente.setEvento(dados.getEvento());
        existente.setResgateLanche(dados.getResgateLanche());

        return ticketRepository.save(existente);
    }

    @Transactional
    public void delete(Integer id) {
        Ticket existente = findById(id);
        
        ticketRepository.delete(existente);
    }
}
