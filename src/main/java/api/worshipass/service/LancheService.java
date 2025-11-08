/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package api.worshipass.service;

import api.worshipass.domain.Lanche;
import api.worshipass.repository.LancheRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author David
 */
@Service
public class LancheService {
    private final LancheRepository lancheRepository;

    public LancheService(LancheRepository lancheRepository) {
        this.lancheRepository = lancheRepository;
    }

    @Transactional(readOnly = true)
    public List<Lanche> findAll() {
        return lancheRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Lanche findById(Integer id) {
        return lancheRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lanche nao encontrado: " + id));
    }

    @Transactional
    public Lanche create(Lanche lanche) {
        lanche.setId(null);
        
        return lancheRepository.save(lanche);
    }

    @Transactional
    public Lanche update(Integer id, Lanche dados) {
        Lanche existente = findById(id);

        existente.setNome(dados.getNome());
        existente.setDescricao(dados.getDescricao());
        existente.setQuantidadeDisponivel(dados.getQuantidadeDisponivel());

        return lancheRepository.save(existente);
    }

    @Transactional
    public void delete(Integer id) {
        Lanche existente = findById(id);
        
        lancheRepository.delete(existente);
    }
}
