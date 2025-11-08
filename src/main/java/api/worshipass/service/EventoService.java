/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package api.worshipass.service;

import api.worshipass.domain.Evento;
import api.worshipass.repository.EventoRepository;
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

    public EventoService(EventoRepository eventoRepository){
        this.eventoRepository = eventoRepository;
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
}
