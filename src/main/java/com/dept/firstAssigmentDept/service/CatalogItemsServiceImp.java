package com.dept.firstAssigmentDept.service;

import com.dept.firstAssigmentDept.exception.NotFoundException;
import com.dept.firstAssigmentDept.model.CatalogItem;
import com.dept.firstAssigmentDept.repository.CatalogItemsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
                .orElseThrow(() -> new NotFoundException("There is no item with this Id: " + id + "."));
    }

    @Override
    public CatalogItem addCatalogItem(CatalogItem item) {
        return catalogItemsRepository.save(item);
    }

    @Override
    public CatalogItem updateCatalogItem(Long id, CatalogItem item) {
        return catalogItemsRepository.saveAndFlush(item);
    }

    @Override
    public void deleteCatalogItem(Long id) {
        catalogItemsRepository.deleteById(id);
    }
}
