/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package api.worshipass.dto;

import lombok.Data;

/**
 *
 * @author David
 */
@Data
public class LancheDto {
    private Integer id;
    private String nome;
    private String descricao;
    private Integer quantidadeDisponivel;
}
