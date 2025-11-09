/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package api.worshipass.service;

import api.worshipass.domain.Evento;
import api.worshipass.domain.Ticket;
import api.worshipass.repository.EventoRepository;
import api.worshipass.repository.TicketRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author David
 */
@Service
public class EventoService {

    private final EventoRepository eventoRepository;
    private final TicketRepository ticketRepository;

    public EventoService(EventoRepository eventoRepository, TicketRepository ticketRepository) {
        this.eventoRepository = eventoRepository;
        this.ticketRepository = ticketRepository;
    }

    @Transactional(readOnly = true)
    public List<Evento> findAll() {
        return eventoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Evento findById(Integer id) {
        return eventoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento nao encontrado: " + id));
    }

    @Transactional
    public Evento create(Evento evento) {
        evento.setId(null);

        return eventoRepository.save(evento);
    }

    @Transactional
    public Evento update(Integer id, Evento data) {
        Evento existente = findById(id);

        existente.setNome(data.getNome());
        existente.setDataEvento(data.getDataEvento());
        existente.setCapacidadeTotal(data.getCapacidadeTotal());
        existente.setLocal(data.getLocal());

        return eventoRepository.save(existente);
    }

    @Transactional
    public void delete(Integer id) {
        Evento existente = findById(id);

        eventoRepository.delete(existente);
    }

    @Transactional
    public void generateTickets(Integer id) {
        Evento evento = findById(id);

        List<Ticket> tickets = new ArrayList();

        for (int i = 0; i < evento.getCapacidadeTotal(); i++) {
            Ticket ticket = new Ticket();

            ticket.setDataEmissao(LocalDateTime.now());
            ticket.setEvento(evento);
            ticket.setStatus("Disponivel");

            tickets.add(ticket);
        }

        ticketRepository.saveAll(tickets);
    }
}
