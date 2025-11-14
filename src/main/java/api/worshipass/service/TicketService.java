/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package api.worshipass.service;

import api.worshipass.domain.Lanche;
import api.worshipass.domain.ResgateLanche;
import api.worshipass.domain.Ticket;
import api.worshipass.repository.LancheRepository;
import api.worshipass.repository.ResgateLancheRepository;
import api.worshipass.repository.TicketRepository;
import java.time.LocalDateTime;
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
    private final LancheRepository lancheRepository;
    private final ResgateLancheRepository resgateLancheRepository;
    
    public TicketService(TicketRepository ticketRepository, LancheRepository lancheRepository, ResgateLancheRepository resgateLancheRepository) {
        this.ticketRepository = ticketRepository;
        this.lancheRepository = lancheRepository;
        this.resgateLancheRepository = resgateLancheRepository;
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
    
    @Transactional
    public void reedemLanche(Integer ticketId, Integer lancheId) {
        Ticket ticket = findById(ticketId);
        
        Lanche lanche = lancheRepository.findById(lancheId)
                .orElseThrow(() -> new RuntimeException("Lanche nao encontrado: " + lancheId));
        
        lanche.setQuantidadeDisponivel(lanche.getQuantidadeDisponivel() - 1);
        
        lancheRepository.save(lanche);
        
        ResgateLanche resgateLanche = new ResgateLanche();
        
        resgateLanche.setDataResgate(LocalDateTime.now());
        resgateLanche.setLanche(lanche);
        
        ResgateLanche resgatado = resgateLancheRepository.save(resgateLanche);
        
        ticket.setResgateLanche(resgatado);
        
        ticketRepository.save(ticket);
    }
}
