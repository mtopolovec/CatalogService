package com.dept.firstAssigmentDept.controller;

import com.dept.firstAssigmentDept.dto.CatalogItemDTO;
import com.dept.firstAssigmentDept.model.CatalogItem;
import com.dept.firstAssigmentDept.service.CatalogItemsService;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/catalogItems")
public class CatalogItemsController {
    private final CatalogItemsService catalogItemsService;

    public CatalogItemsController(CatalogItemsService catalogItemsService) {
        this.catalogItemsService = catalogItemsService;
    }

    @GetMapping
    public List<CatalogItemDTO> allCatalogItems() {
        log.debug("Getting all catalog items.");
        return catalogItemsService.getAllCatalogItems();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CatalogItemDTO> getCatalogItem(@PathVariable("id") final Long id) {
        log.debug("Getting catalog item with id of " + id + ".");
        CatalogItemDTO catalogItem = catalogItemsService.getItemById(id);
        return new ResponseEntity<>(catalogItem, HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<CatalogItemDTO> addCatalogItem(@Valid @RequestBody final CatalogItemDTO item) {
        log.debug("Adding new item");
        CatalogItemDTO catalogItem = catalogItemsService.addCatalogItem(item);
        return new ResponseEntity<>(catalogItem, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CatalogItemDTO> updateCatalogItem(@PathVariable("id") final Long id,
                                                         @Valid @RequestBody final CatalogItemDTO item) {
        log.debug("Updating item with id of " + id + ".");
        CatalogItemDTO catalogItem = catalogItemsService.updateCatalogItem(id, item);
        return new ResponseEntity<>(catalogItem, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<JSONObject> deleteCatalogItem(@PathVariable("id") final Long id) {
        log.debug("Deleted catalog item with id of " + id + ".");
        catalogItemsService.deleteCatalogItem(id);
        JSONObject text = new JSONObject();
        text.appendField("result","Successfully deleted catalog item.");
        return new ResponseEntity<>(text, HttpStatus.OK);
    }
}
