/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package api.worshipass.mapper;

import api.worshipass.domain.Lanche;
import api.worshipass.dto.LancheDto;

/**
 *
 * @author David
 */
public class LancheMapper {

    public static LancheDto toDto(Lanche entity) {
        if (entity == null) {
            return null;
        }

        LancheDto dto = new LancheDto();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setDescricao(entity.getDescricao());
        dto.setQuantidadeDisponivel(entity.getQuantidadeDisponivel());
        return dto;
    }
}
