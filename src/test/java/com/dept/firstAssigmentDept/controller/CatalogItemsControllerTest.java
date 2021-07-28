package com.dept.firstAssigmentDept.controller;

import com.dept.firstAssigmentDept.converter.CatalogItemDTOToCatalogItem;
import com.dept.firstAssigmentDept.converter.CatalogItemToCatalogItemDTO;
import com.dept.firstAssigmentDept.dto.CatalogItemDTO;
import com.dept.firstAssigmentDept.model.CatalogItem;
import com.dept.firstAssigmentDept.service.CatalogItemsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CatalogItemsController.class)
class CatalogItemsControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    CatalogItemDTOToCatalogItem catalogItemDTOToCatalogItem;

    @Autowired
    CatalogItemToCatalogItemDTO catalogItemToCatalogItemDTO;

    @MockBean
    CatalogItemsService catalogItemsService;

    List<String> images = new ArrayList<>();
    List<String> categories = new ArrayList<>();

    @Before(value = "CatalogItemsControllerTestSetup")
    void setUp() {

        images.add("Image1");
        images.add("Image2");

        categories.add("TestCategories1");
        categories.add("TestCategories2");

    }

    CatalogItem catalogItem1 = new CatalogItem(1L,"TestItem1", "TestItem1 description", new BigDecimal(24.99), images, categories);
    CatalogItem catalogItem2 = new CatalogItem(2L,"TestItem2", "TestItem2 description", new BigDecimal(99.99), images, categories);
    CatalogItem catalogItem3 = new CatalogItem(3L,"TestItem3", "TestItem3 description", new BigDecimal(99999.99), images, categories);

    @Test
    void allCatalogItems_success() throws Exception {
        List<CatalogItemDTO> catalogItems = new ArrayList<>(Arrays.asList(catalogItemToCatalogItemDTO.convert(catalogItem1),
                catalogItemToCatalogItemDTO.convert(catalogItem2),
                catalogItemToCatalogItemDTO.convert(catalogItem3)));

        when(catalogItemsService.getAllCatalogItems()).thenReturn(catalogItems);

        mockMvc.perform(MockMvcRequestBuilders
        .get("/api/catalogItems")
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[2].name", is("TestItem3")));
    }

    @Test
    void getCatalogItem_success() throws Exception {
        when(catalogItemsService.getItemById(catalogItem1.getId())).thenReturn((catalogItemToCatalogItemDTO.convert(catalogItem1)));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/catalogItems/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",notNullValue()))
                .andExpect(jsonPath("$.name", is("TestItem1")));
    }

    @Test
    void addCatalogItem_success() throws Exception {

        CatalogItemDTO newCatalogItem = createCatalogItem();

        when(catalogItemsService.addCatalogItem(newCatalogItem)).thenReturn(newCatalogItem);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .post("/api/catalogItems")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(newCatalogItem));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.name", is("TestItem")));
    }

    @Test
    void updateCatalogItem_success() throws Exception {

        CatalogItemDTO updatedCatalogItem = createCatalogItem();

        when(catalogItemsService.getItemById(catalogItem1.getId()))
                .thenReturn(catalogItemToCatalogItemDTO.convert(catalogItem1));
        when(catalogItemsService.updateCatalogItem(catalogItem1.getId(),
                updatedCatalogItem))
                .thenReturn(updatedCatalogItem);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .put("/api/catalogItems/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(updatedCatalogItem));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.name", is("TestItem")));

    }

    @Test
    void updateCatalogItem_nullId() throws Exception {

        CatalogItemDTO updatedCatalogItem = createCatalogItem();

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .put("/api/catalogItems")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(updatedCatalogItem));

        mockMvc.perform(mockRequest)
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    void deleteCatalogItem_success() throws Exception {

        when(catalogItemsService.getItemById(catalogItem1.getId()))
                .thenReturn(catalogItemToCatalogItemDTO.convert(catalogItem1));

        mockMvc.perform(MockMvcRequestBuilders
        .delete("/api/catalogItems/1")
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
        .andExpect(result ->
                assertEquals("{\"result\":\"Successfully deleted catalog item.\"}",
                        result.getResponse().getContentAsString()));
    }

    @Test
    void searchCatalogItemsByName_success() throws Exception {

        CatalogItemDTO searchItem = createCatalogItem();
        searchItem.setName("Bosch drill");

        catalogItemsService.addCatalogItem(searchItem);

        when(catalogItemsService.searchCatalogItemsByName("drill"))
                .thenReturn(new ArrayList<>(Arrays.asList(searchItem)));

        mockMvc.perform(MockMvcRequestBuilders
        .get("/api/catalogItems/searchByName/drill")
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.[0].name", is("Bosch drill")));
    }

    @Test
    void filterItemsByCategory_ShouldFilterItemCorrectly() throws Exception {

        List<String> testCategories = new ArrayList<>();
        testCategories.add("Power Drills");
        testCategories.add("Power tools");
        testCategories.add("Tools and home improvement");

        CatalogItemDTO searchCategory = createCatalogItem();
        searchCategory.setName("Bosch drill");
        searchCategory.setCategories(testCategories);

        when(catalogItemsService.filterCatalogItemsByCategories(new ArrayList<>(Arrays.asList("Power tools"))))
                .thenReturn(new ArrayList<>(Arrays.asList(searchCategory)));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .post("/api/catalogItems/filterByCategory")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("[\"Power tools\"]");

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.[0].name", is("Bosch drill")));
    }

    @Test
    void fullTextSearchByNameAndDescription() throws Exception {

        CatalogItemDTO searchByNameAndDescription = createCatalogItem();
        searchByNameAndDescription.setName("Bosch drill");
        searchByNameAndDescription.setDescription("All brand new drill with witch you can put your best pictures on the wall with ease.");

        when(catalogItemsService.searchCatalogItemsByNameAndDescription("drill"))
                .thenReturn(new ArrayList<>(Arrays.asList(searchByNameAndDescription)));

        mockMvc.perform(MockMvcRequestBuilders
            .get("/api/catalogItems/searchByNameAndDescription/drill")
            .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is("Bosch drill")));
    }

    private CatalogItemDTO createCatalogItem() {
        List<String> testImages = new ArrayList<>();
        testImages.add("testImage1");
        testImages.add("testImage2");

        List<String> testCategories = new ArrayList<>();
        testCategories.add("testCategory1");
        testCategories.add("testCategory2");

        CatalogItemDTO newCatalogItem = CatalogItemDTO.builder()
                .name("TestItem")
                .description("The new super TestItem")
                .price(new BigDecimal(1500.99))
                .images(testImages)
                .categories(testCategories)
                .build();

        return newCatalogItem;
    }
}