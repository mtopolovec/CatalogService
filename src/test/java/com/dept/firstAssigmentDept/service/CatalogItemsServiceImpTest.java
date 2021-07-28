package com.dept.firstAssigmentDept.service;

import com.dept.firstAssigmentDept.converter.CatalogItemDTOToCatalogItem;
import com.dept.firstAssigmentDept.converter.CatalogItemToCatalogItemDTO;
import com.dept.firstAssigmentDept.dto.CatalogItemDTO;
import com.dept.firstAssigmentDept.exception.NotFoundException;
import com.dept.firstAssigmentDept.model.CatalogItem;
import com.dept.firstAssigmentDept.repository.CatalogItemsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CatalogItemsServiceImpTest {

    @Mock
    private CatalogItemsRepository catalogItemsRepository;
    @InjectMocks
    private CatalogItemsServiceImp catalogItemsService;
    @Mock
    private CatalogItemToCatalogItemDTO catalogItemToCatalogItemDTO;
    @Mock
    private CatalogItemDTOToCatalogItem catalogItemDTOToCatalogItem;

    @Test
    void getAllCatalogItems_shouldReturnCatalogItems() {

        CatalogItem catalogItem1 = createCatalogItem(1L,"TestItem1","The new super TestItem", new BigDecimal(99.99));
        CatalogItem catalogItem2 = createCatalogItem(2L,"TestItem2","The super TestItem2", new BigDecimal(19.99));
        CatalogItem catalogItem3 = createCatalogItem(3L,"TestItem3","The super TestItem3", new BigDecimal(1999.99));

        List<CatalogItem> catalogItems = new ArrayList<>(Arrays.asList(catalogItem1, catalogItem2, catalogItem3));

        when(catalogItemsRepository.findAll()).thenReturn(catalogItems);

        List<CatalogItemDTO> actualCatalogItems = catalogItemsService.getAllCatalogItems();

        assertThat(actualCatalogItems, is(notNullValue()));
        assertThat(actualCatalogItems.size(), equalTo(3));

    }

    @Test
    void getItemById_shouldReturnCorrectItem() {

        CatalogItem testCatalogItem = createCatalogItem(1L,"TestItem","The new super TestItem", new BigDecimal(99.99));
        CatalogItemDTO catalogItemDTO = new CatalogItemDTO(1L,"TestItem","The new super TestItem", new BigDecimal(99.99),testCatalogItem.getImages(),testCatalogItem.getCategories());

        when(catalogItemToCatalogItemDTO.convert(testCatalogItem)).thenReturn(catalogItemDTO);
        when(catalogItemsRepository.findById(testCatalogItem.getId())).thenReturn(Optional.of(testCatalogItem));

        CatalogItemDTO actualCatalogItem = catalogItemsService.getItemById(testCatalogItem.getId());

        assertThat(actualCatalogItem, is(notNullValue()));
        assertThat(actualCatalogItem.getName(), is(equalTo("TestItem")));
    }

    @Test
    void getItemById_shouldThrowNotFoundException() {

        final Long number = 1L;
        when(catalogItemsRepository.findById(number)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> catalogItemsService.getItemById(number),
                "There is no item with this Id: " + number + ".");
        assertThatThrownBy(() -> catalogItemsService.getItemById(number))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("There is no item with this Id: " + number + ".");
    }

    @Test
    void addCatalogItem_shouldAddCatalogItem() {

        CatalogItem testCatalogItem = createCatalogItem(1L,"TestItem","The new super TestItem", new BigDecimal(99.99));
        CatalogItemDTO catalogItemDTO = new CatalogItemDTO(1L,"TestItem","The new super TestItem", new BigDecimal(99.99),testCatalogItem.getImages(),testCatalogItem.getCategories());

        when(catalogItemToCatalogItemDTO.convert(testCatalogItem)).thenReturn(catalogItemDTO);
        CatalogItemDTO response = catalogItemToCatalogItemDTO.convert(testCatalogItem);

        when(catalogItemDTOToCatalogItem.convert(catalogItemDTO)).thenReturn(testCatalogItem);
        when(catalogItemsRepository.save(testCatalogItem)).thenReturn(testCatalogItem);
        when(catalogItemsService.addCatalogItem(catalogItemDTO)).thenReturn(response);

        CatalogItemDTO actualCatalogItem = catalogItemsService.addCatalogItem(catalogItemToCatalogItemDTO.convert(testCatalogItem));

        assertThat(actualCatalogItem, is(notNullValue()));
        assertThat(actualCatalogItem.getName(), is(equalTo("TestItem")));

    }

    @Test
    void updateCatalogItem_shouldReturnUpdatedItem() {

        final Long number = 1L;

        CatalogItem newCatalogItem = createCatalogItem(1L,"TestItem","The new super TestItem", new BigDecimal(99.99));
        CatalogItemDTO newCatalogItemDTO = new CatalogItemDTO(1L,"TestItem","The new super TestItem", new BigDecimal(99.99),newCatalogItem.getImages(),newCatalogItem.getCategories());

        when(catalogItemToCatalogItemDTO.convert(newCatalogItem)).thenReturn(newCatalogItemDTO);
        when(catalogItemDTOToCatalogItem.convert(newCatalogItemDTO)).thenReturn(newCatalogItem);
        when(catalogItemsRepository.existsById(number)).thenReturn(true);
        when(catalogItemsRepository.saveAndFlush(newCatalogItem)).thenReturn(newCatalogItem);

        CatalogItemDTO updatedCatalogItem = catalogItemsService.updateCatalogItem(number, newCatalogItemDTO);

        assertThat(updatedCatalogItem, is(notNullValue()));
    }

    @Test
    void updateCatalogItem_shouldThrowNotFoundException() {

        final Long number = 1L;

        CatalogItem newCatalogItem = createCatalogItem(1L,"TestItem","The new super TestItem", new BigDecimal(99.99));
        CatalogItemDTO newCatalogItemDTO = new CatalogItemDTO(1L,"TestItem","The new super TestItem", new BigDecimal(99.99),newCatalogItem.getImages(),newCatalogItem.getCategories());

        when(catalogItemsRepository.existsById(number)).thenReturn(false);

        assertThatThrownBy(() -> catalogItemsService.updateCatalogItem(number, newCatalogItemDTO))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("There is no Catalog item with id of " + number + ".");
    }

    @Test
    void deleteCatalogItem_shouldDeleteCorrectItem() {

        final Long number = 1L;

        when(catalogItemsRepository.existsById(number)).thenReturn(true);
        catalogItemsService.deleteCatalogItem(number);

        verify(catalogItemsRepository).deleteById(number);
    }

    @Test
    void deleteCatalogItem_shouldThrowNotFoundException() {

        final Long number = 1L;

        when(catalogItemsRepository.existsById(number)).thenReturn(false);

        assertThatThrownBy(() -> catalogItemsService.deleteCatalogItem(number))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("There is no Catalog item with id of " + number + ".");
    }

    @Test
    void searchCatalogItemsByName_shouldReturnProperSearchItem() {

        String search = "drill";

        CatalogItem catalogItem = createCatalogItem(1L,"Test Item drill","The new super TestItem", new BigDecimal(99.99));
        CatalogItem catalogItem1 = createCatalogItem(2L,"TestItem","The new super TestItem", new BigDecimal(99.99));
        CatalogItemDTO catalogItemDTO = new CatalogItemDTO(1L,"Test Item drill","The new super TestItem", new BigDecimal(99.99),catalogItem.getImages(),catalogItem.getCategories());
        CatalogItemDTO catalogItem1DTO = new CatalogItemDTO(2L,"TestItem","The new super TestItem", new BigDecimal(99.99),catalogItem1.getImages(),catalogItem1.getCategories());

        when(catalogItemToCatalogItemDTO.convert(catalogItem)).thenReturn(catalogItemDTO);
        when(catalogItemToCatalogItemDTO.convert(catalogItem1)).thenReturn(catalogItem1DTO);
        when(catalogItemsRepository.findCatalogItemsByNameContainsIgnoreCase(search)).thenReturn(new ArrayList<>(Arrays.asList(catalogItem, catalogItem1)));

        List<CatalogItemDTO> searchItems = catalogItemsService.searchCatalogItemsByName(search);

        assertThat(searchItems, is(notNullValue()));
        assertThat(searchItems.size(), is(equalTo(1)));
        assertThat(searchItems.get(0).getName(), is(equalTo(catalogItemDTO.getName())));

    }

    @Test
    void filterCatalogItemsByCategories_shouldReturnItemsWithCategoriesSearched() {

        String filterCategory = "testCategory1";
        List<String> filterCategories = new ArrayList<>(Arrays.asList(filterCategory));

        CatalogItem catalogItem = createCatalogItem(1L,"Test Item drill","The new super TestItem", new BigDecimal(99.99));
        CatalogItemDTO catalogItemDTO = new CatalogItemDTO(1L,"Test Item drill","The new super TestItem", new BigDecimal(99.99),catalogItem.getImages(),catalogItem.getCategories());

        when(catalogItemToCatalogItemDTO.convert(catalogItem)).thenReturn(catalogItemDTO);
        when(catalogItemsRepository.findCatalogItemsByCategories(filterCategory)).thenReturn(new ArrayList<>(Arrays.asList(catalogItem)));

        List<CatalogItemDTO> filterItemsByCategory = catalogItemsService.filterCatalogItemsByCategories(filterCategories);

        assertThat(filterItemsByCategory, is(notNullValue()));
        assertThat(filterItemsByCategory.size(), is(equalTo(1)));
        assertThat(filterItemsByCategory.get(0).getName(), is(equalTo(catalogItemDTO.getName())));

    }

    @Test
    void searchCatalogItemsByNameAndDescription_shouldReturnItemsThatHaveSearchedStringInNameAndDescription() {

        String search = "drill";

        CatalogItem catalogItem = createCatalogItem(1L,"Test Item drill","The new super TestItem with a drill", new BigDecimal(99.99));
        CatalogItemDTO catalogItemDTO = new CatalogItemDTO(1L,"Test Item drill","The new super TestItem with a drill", new BigDecimal(99.99),catalogItem.getImages(),catalogItem.getCategories());

        when(catalogItemToCatalogItemDTO.convert(catalogItem)).thenReturn(catalogItemDTO);
        when(catalogItemsRepository.findCatalogItemsByNameContainsIgnoreCaseOrDescriptionContainsIgnoreCase(search, search)).thenReturn(new ArrayList<>(Arrays.asList(catalogItem)));

        List<CatalogItemDTO> searchItems = catalogItemsService.searchCatalogItemsByNameAndDescription(search);

        assertThat(searchItems, is(notNullValue()));
        assertThat(searchItems.size(), is(equalTo(1)));
        assertThat(searchItems.get(0).getName(), is(equalTo(catalogItemDTO.getName())));

    }

    private CatalogItem createCatalogItem(Long id, String name, String description, BigDecimal price) {
        List<String> testImages = new ArrayList<>();
        testImages.add("testImage1");
        testImages.add("testImage2");

        List<String> testCategories = new ArrayList<>();
        testCategories.add("testCategory1");
        testCategories.add("testCategory2");

        CatalogItem newCatalogItem = CatalogItem.builder()
                .id(id)
                .name(name)
                .description(description)
                .price(price)
                .images(testImages)
                .categories(testCategories)
                .build();

        return newCatalogItem;
    }

}