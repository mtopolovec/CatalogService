package com.dept.firstAssigmentDept.service;

import com.dept.firstAssigmentDept.model.CatalogItem;

import java.util.List;

public interface CatalogItemsService {
    List<CatalogItem> getAllCatalogItems();
    CatalogItem getItemById(Long id);
    CatalogItem addCatalogItem(CatalogItem item);
    CatalogItem updateCatalogItem(Long id, CatalogItem item);
    void deleteCatalogItem(Long id);

}
