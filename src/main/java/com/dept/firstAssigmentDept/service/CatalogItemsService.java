package com.dept.firstAssigmentDept.service;

import com.dept.firstAssigmentDept.dto.CatalogItemDTO;

import java.util.List;

public interface CatalogItemsService {
    List<CatalogItemDTO> getAllCatalogItems();
    CatalogItemDTO getItemById(Long id);
    CatalogItemDTO addCatalogItem(CatalogItemDTO item);
    CatalogItemDTO updateCatalogItem(Long id, CatalogItemDTO item);
    void deleteCatalogItem(Long id);
    List<CatalogItemDTO> searchCatalogItemsByName(String search);

}
