/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package api.worshipass.mapper;

import api.worshipass.domain.Participante;
import api.worshipass.dto.ParticipanteComTicketsDto;
import api.worshipass.dto.ParticipanteDto;

/**
 *
 * @author David
 */
public class ParticipanteMapper {

    public static ParticipanteDto toDto(Participante entity) {
        if (entity == null) {
            return null;
        }

        ParticipanteDto dto = new ParticipanteDto();
        dto.setId(entity.getId());
        dto.setNomeCompleto(entity.getNomeCompleto());
        dto.setEmail(entity.getEmail());
        dto.setTelefone(entity.getTelefone());
        return dto;
    }

    public static ParticipanteComTicketsDto toComTickets(Participante entity) {
        if (entity == null) {
            return null;
        }

        ParticipanteComTicketsDto dto = new ParticipanteComTicketsDto();

        ParticipanteDto base = toDto(entity);
        dto.setId(base.getId());
        dto.setNomeCompleto(base.getNomeCompleto());
        dto.setEmail(base.getEmail());
        dto.setTelefone(base.getTelefone());

        if (entity.getTickets() != null) {
            dto.setTickets(
                    entity.getTickets().stream()
                            .map(TicketMapper::toTicketComEvento)
                            .toList()
            );
        }

        return dto;
    }
}
