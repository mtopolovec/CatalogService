package com.dept.firstAssigmentDept.service;

import com.dept.firstAssigmentDept.model.CatalogItem;
import com.dept.firstAssigmentDept.repository.CatalogItemsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CatalogItemsServiceImp implements CatalogItemsService {

    private final CatalogItemsRepository catalogItemsRepository;

    public CatalogItemsServiceImp(CatalogItemsRepository catalogItemsRepository) {
        this.catalogItemsRepository = catalogItemsRepository;
    }

    @Override
    public List<CatalogItem> getAllCatalogItems() {
        return catalogItemsRepository.findAll();
    }

    @Override
    public CatalogItem getItemById(Long id) {
        return catalogItemsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("There is no item with this Id: " + id + "."));
    }

    @Override
    public CatalogItem addCatalogItem(CatalogItem item) {
        return null;
    }

    @Override
    public CatalogItem updateCatalogItem(Long id, CatalogItem item) {
        return null;
    }

    @Override
    public void deleteCatalogItem(Long id) {

    }
}
