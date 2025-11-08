/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package api.worshipass.repository;

import api.worshipass.domain.Participante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author David
 */
@Repository
public interface ParticipanteRepository extends JpaRepository<Participante, Integer> {
    
}
