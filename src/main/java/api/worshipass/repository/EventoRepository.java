/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package api.worshipass.repository;

import api.worshipass.domain.Evento;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author David
 */
@Repository
public interface EventoRepository extends JpaRepository<Evento, Integer> {
    List<Evento> findByDataEventoBetween(LocalDateTime startDate, LocalDateTime endDate);
}
