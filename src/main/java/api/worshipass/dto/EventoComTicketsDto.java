/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package api.worshipass.dto;

import java.util.List;
import lombok.Data;

/**
 *
 * @author David
 */
@Data
public class EventoComTicketsDto extends EventoDto {
    private List<TicketComParticipanteDto> tickets;
}
