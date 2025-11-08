/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package api.worshipass.service;

import api.worshipass.domain.ResgateLanche;
import api.worshipass.repository.ResgateLancheRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author David
 */
@Service
public class ResgateLancheService {
    private final ResgateLancheRepository resgateLancheRepository;

    public ResgateLancheService(ResgateLancheRepository resgateLancheRepository) {
        this.resgateLancheRepository = resgateLancheRepository;
    }

    @Transactional(readOnly = true)
    public List<ResgateLanche> findAll() {
        return resgateLancheRepository.findAll();
    }

    @Transactional(readOnly = true)
    public ResgateLanche findById(Integer id) {
        return resgateLancheRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resgate de lanche nao encontrado: " + id));
    }

    @Transactional
    public ResgateLanche create(ResgateLanche resgateLanche) {
        resgateLanche.setId(null);
        
        return resgateLancheRepository.save(resgateLanche);
    }

    @Transactional
    public ResgateLanche update(Integer id, ResgateLanche dados) {
        ResgateLanche existente = findById(id);

        existente.setDataResgate(dados.getDataResgate());
        existente.setLanche(dados.getLanche());

        return resgateLancheRepository.save(existente);
    }

    @Transactional
    public void delete(Integer id) {
        ResgateLanche existente = findById(id);
        
        resgateLancheRepository.delete(existente);
    }
}
