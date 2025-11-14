/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package api.worshipass.dto;

import java.time.LocalDateTime;
import lombok.Data;

/**
 *
 * @author David
 */
@Data
public class TicketDto {
    private Integer id;
    private String status;
    private LocalDateTime dataEmissao;
    private LocalDateTime dataUso;

    private ResgateLancheDto resgateLanche;
}
