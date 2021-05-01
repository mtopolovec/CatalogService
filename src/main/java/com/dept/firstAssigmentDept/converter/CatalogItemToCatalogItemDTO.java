package com.dept.firstAssigmentDept.converter;

import com.dept.firstAssigmentDept.dto.CatalogItemDTO;
import com.dept.firstAssigmentDept.model.CatalogItem;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CatalogItemToCatalogItemDTO implements Converter<CatalogItem, CatalogItemDTO> {
    @Override
    public CatalogItemDTO convert(CatalogItem source) {
        if(source == null) {
            return null;
        }

        CatalogItemDTO catalogItemDTO = new CatalogItemDTO();
        catalogItemDTO.setId(source.getId());
        catalogItemDTO.setName(source.getName());
        catalogItemDTO.setDescription(source.getDescription());
        catalogItemDTO.setPrice(source.getPrice());
        catalogItemDTO.setImages(source.getImages());
        catalogItemDTO.setCategories(source.getCategories());

        return catalogItemDTO;
    }
}
