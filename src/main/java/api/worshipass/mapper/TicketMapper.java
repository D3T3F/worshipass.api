/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package api.worshipass.mapper;

import api.worshipass.domain.Evento;
import api.worshipass.domain.Participante;
import api.worshipass.domain.Ticket;
import api.worshipass.dto.TicketComEventoDto;
import api.worshipass.dto.TicketComParticipanteDto;
import api.worshipass.dto.TicketDto;

/**
 *
 * @author David
 */
public class TicketMapper {

    private static void fillBase(TicketDto dto, Ticket t) {
        dto.setId(t.getId());
        dto.setStatus(t.getStatus());
        dto.setDataEmissao(t.getDataEmissao());
        dto.setDataUso(t.getDataUso());
        dto.setResgateLanche(ResgateLancheMapper.toDto(t.getResgateLanche()));
    }

    public static TicketComEventoDto toTicketComEvento(Ticket t) {
        if (t == null) {
            return null;
        }

        TicketComEventoDto dto = new TicketComEventoDto();
        fillBase(dto, t);

        Evento e = t.getEvento();
        if (e != null) {
            dto.setEvento(EventoMapper.toDto(e));
        }

        return dto;
    }

    public static TicketComParticipanteDto toTicketComParticipante(Ticket t) {
        if (t == null) {
            return null;
        }

        TicketComParticipanteDto dto = new TicketComParticipanteDto();
        fillBase(dto, t);

        Participante p = t.getParticipante();
        if (p != null) {
            dto.setParticipante(ParticipanteMapper.toDto(p));
        }

        return dto;
    }
}
