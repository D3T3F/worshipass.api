/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package api.worshipass.mapper;

import api.worshipass.domain.Evento;
import api.worshipass.dto.EventoComTicketsDto;
import api.worshipass.dto.EventoDto;

/**
 *
 * @author David
 */
public class EventoMapper {

    public static EventoDto toDto(Evento entity) {
        if (entity == null) {
            return null;
        }

        EventoDto dto = new EventoDto();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setDataEvento(entity.getDataEvento());
        dto.setCapacidadeTotal(entity.getCapacidadeTotal());
        dto.setLocal(entity.getLocal());
        return dto;
    }

    public static EventoComTicketsDto toComTickets(Evento entity) {
        if (entity == null) {
            return null;
        }

        EventoComTicketsDto dto = new EventoComTicketsDto();
        EventoDto base = toDto(entity);
        dto.setId(base.getId());
        dto.setNome(base.getNome());
        dto.setDataEvento(base.getDataEvento());
        dto.setCapacidadeTotal(base.getCapacidadeTotal());
        dto.setLocal(base.getLocal());

        if (entity.getTickets() != null) {
            dto.setTickets(
                    entity.getTickets().stream()
                            .map(TicketMapper::toTicketComParticipante)
                            .toList()
            );
        }

        return dto;
    }
}
