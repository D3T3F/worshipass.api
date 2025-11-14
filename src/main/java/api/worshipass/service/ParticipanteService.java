/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package api.worshipass.service;

import api.worshipass.domain.Participante;
import api.worshipass.dto.ParticipanteComTicketsDto;
import api.worshipass.mapper.ParticipanteMapper;
import api.worshipass.repository.ParticipanteRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author David
 */
@Service
public class ParticipanteService {

    private final ParticipanteRepository participanteRepository;

    public ParticipanteService(ParticipanteRepository participanteRepository) {
        this.participanteRepository = participanteRepository;
    }

    @Transactional(readOnly = true)
    public List<ParticipanteComTicketsDto> findAll() {
        return participanteRepository.findAll()
                .stream()
                .map(ParticipanteMapper::toComTickets)
                .toList();
    }

    @Transactional(readOnly = true)
    public Participante findById(Integer id) {
        return participanteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Participante nao encontrado: " + id));
    }

    @Transactional
    public Participante create(Participante participante) {
        participante.setId(null);

        return participanteRepository.save(participante);
    }

    @Transactional
    public Participante update(Integer id, Participante dados) {
        Participante existente = findById(id);

        existente.setNomeCompleto(dados.getNomeCompleto());
        existente.setEmail(dados.getEmail());
        existente.setTelefone(dados.getTelefone());

        return participanteRepository.save(existente);
    }

    @Transactional
    public void delete(Integer id) {
        Participante existente = findById(id);

        participanteRepository.delete(existente);
    }
}
