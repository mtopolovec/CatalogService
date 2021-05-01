package com.dept.firstAssigmentDept.service;

import com.dept.firstAssigmentDept.converter.CatalogItemDTOToCatalogItem;
import com.dept.firstAssigmentDept.converter.CatalogItemToCatalogItemDTO;
import com.dept.firstAssigmentDept.dto.CatalogItemDTO;
import com.dept.firstAssigmentDept.exception.NotFoundException;
import com.dept.firstAssigmentDept.model.CatalogItem;
import com.dept.firstAssigmentDept.repository.CatalogItemsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CatalogItemsServiceImp implements CatalogItemsService {

    private final CatalogItemsRepository catalogItemsRepository;
    private final CatalogItemToCatalogItemDTO catalogItemToCatalogItemDTO;
    private final CatalogItemDTOToCatalogItem catalogItemDTOToCatalogItem;

    public CatalogItemsServiceImp(CatalogItemsRepository catalogItemsRepository, CatalogItemToCatalogItemDTO catalogItemToCatalogItemDTO, CatalogItemDTOToCatalogItem catalogItemDTOToCatalogItem) {
        this.catalogItemsRepository = catalogItemsRepository;
        this.catalogItemToCatalogItemDTO = catalogItemToCatalogItemDTO;
        this.catalogItemDTOToCatalogItem = catalogItemDTOToCatalogItem;
    }

    @Override
    public List<CatalogItemDTO> getAllCatalogItems() {
        return catalogItemsRepository.findAll()
                .stream()
                .map(catalogItemToCatalogItemDTO::convert)
                .collect(Collectors.toList());
    }

    @Override
    public CatalogItemDTO getItemById(Long id) {
        return catalogItemsRepository.findById(id)
                .map(catalogItemToCatalogItemDTO::convert)
                .orElseThrow(() -> new NotFoundException("There is no item with this Id: " + id + "."));
    }

    @Override
    public CatalogItemDTO addCatalogItem(CatalogItemDTO item) {
        CatalogItem savedCatalogItem = catalogItemsRepository.save(catalogItemDTOToCatalogItem.convert(item));
        return catalogItemToCatalogItemDTO.convert(savedCatalogItem);
    }

    @Override
    public CatalogItemDTO updateCatalogItem(Long id, CatalogItemDTO item) {
        CatalogItem updatedCatalogItem;
        if(catalogItemsRepository.existsById(id)) {
            updatedCatalogItem = catalogItemDTOToCatalogItem.convert(item);
            updatedCatalogItem.setId(id);
        } else {
            throw new NotFoundException("There is no Catalog item with id of " + id + ".");
        }
        catalogItemsRepository.saveAndFlush(updatedCatalogItem);
        return catalogItemToCatalogItemDTO.convert(updatedCatalogItem);
    }

    @Override
    public void deleteCatalogItem(Long id) {
        catalogItemsRepository.deleteById(id);
    }
}
