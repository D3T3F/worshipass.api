/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package api.worshipass.mapper;

import api.worshipass.domain.ResgateLanche;
import api.worshipass.dto.ResgateLancheDto;

/**
 *
 * @author David
 */
public class ResgateLancheMapper {

    public static ResgateLancheDto toDto(ResgateLanche entity) {
        if (entity == null) {
            return null;
        }

        ResgateLancheDto dto = new ResgateLancheDto();
        dto.setId(entity.getId());
        dto.setDataResgate(entity.getDataResgate());
        dto.setLanche(LancheMapper.toDto(entity.getLanche()));
        return dto;
    }
}
