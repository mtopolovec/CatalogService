package com.dept.firstAssigmentDept.converter;

import com.dept.firstAssigmentDept.dto.CatalogItemDTO;
import com.dept.firstAssigmentDept.model.CatalogItem;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class CatalogItemDTOToCatalogItem implements Converter<CatalogItemDTO, CatalogItem> {


    @Override
    public CatalogItem convert(CatalogItemDTO source) {
        if(source == null) {
            return null;
        }

        final CatalogItem catalogItem = new CatalogItem();
        catalogItem.setId(source.getId());
        catalogItem.setName(source.getName());
        catalogItem.setDescription(source.getDescription());
        catalogItem.setPrice(source.getPrice());
        catalogItem.setImages(source.getImages());
        catalogItem.setCategories(source.getCategories());

        return catalogItem;
    }
}
