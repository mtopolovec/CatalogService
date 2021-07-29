package com.dept.firstAssigmentDept.repository;

import com.dept.firstAssigmentDept.model.CatalogItem;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
class CatalogItemsRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CatalogItemsRepository catalogItemsRepository;

    @Test
    void findCatalogItemsByNameContainsIgnoreCase_shouldReturnOnlyItemsContainingSearchKeyword() {

        String search = "drill";

        CatalogItem matchItem = createCatalogItem("Test Item drill","The new super TestItem with a drill", new BigDecimal(1999.99));
        CatalogItem missItem = createCatalogItem("Test Item","The new super TestItem", new BigDecimal(1999.99));
        entityManager.persist(matchItem);
        entityManager.persist(missItem);
        entityManager.flush();

        List<CatalogItem> foundCatalogItem = catalogItemsRepository.findCatalogItemsByNameContainsIgnoreCase(search);

        assertThat(foundCatalogItem, is(notNullValue()));
        assertThat(foundCatalogItem.size(), is(equalTo(1)));
        assertThat(foundCatalogItem.get(0).getName(), is(equalTo("Test Item drill")));

    }

    @Test
    void findCatalogItemsByCategories_shouldReturnOnlyCorrectItemsContainingSearchedCategories() {

        String matchCategory = "CPU";
        List<String> matchCategories = Arrays.asList(matchCategory);

        CatalogItem matchItem = createCatalogItem("Test Item drill","The new super TestItem with a drill", new BigDecimal(1999.99));
        matchItem.setCategories(matchCategories);
        CatalogItem missItem = createCatalogItem("Test Item","The new super TestItem", new BigDecimal(1999.99));
        entityManager.persist(matchItem);
        entityManager.persist(missItem);
        entityManager.flush();

        List<CatalogItem> foundCatalogItem = catalogItemsRepository.findCatalogItemsByCategories(matchCategory);

        assertThat(foundCatalogItem, is(notNullValue()));
        assertThat(foundCatalogItem.size(), is(equalTo(1)));
        assertThat(foundCatalogItem.get(0).getName(), is(equalTo("Test Item drill")));
        assertThat(foundCatalogItem.get(0).getCategories().size(), is(equalTo(1)));
        assertThat(foundCatalogItem.get(0).getCategories().get(0), is(equalTo("CPU")));

    }

    @Test
    void findCatalogItemsByNameContainsIgnoreCaseOrDescriptionContainsIgnoreCase_shouldReturnOnlyItemsWithCorrectNameAndDescription() {

        String search = "drill";

        String matchItemName = "Test Item drill";
        String matchItemDescription = "The new super TestItem with a drill";

        String matchItemName1 = "Test toy item";
        String matchItemDescription1 = "The new super Mandrill monkey toy";

        String matchItemName2 = "Drilling toy";
        String matchItemDescription2 = "The new super TestItem toy";

        CatalogItem matchItem = createCatalogItem(matchItemName, matchItemDescription, new BigDecimal(1999.99));
        CatalogItem matchItem1 = createCatalogItem(matchItemName1, matchItemDescription1, new BigDecimal(19.99));
        CatalogItem matchItem2 = createCatalogItem(matchItemName2, matchItemDescription2, new BigDecimal(19.99));
        CatalogItem missItem = createCatalogItem("Test Item","The new super TestItem", new BigDecimal(199.99));

        entityManager.persist(matchItem);
        entityManager.persist(matchItem1);
        entityManager.persist(matchItem2);
        entityManager.persist(missItem);
        entityManager.flush();

        List<CatalogItem> foundCatalogItem = catalogItemsRepository.findCatalogItemsByNameContainsIgnoreCaseOrDescriptionContainsIgnoreCase(search, search);

        assertThat(foundCatalogItem, is(notNullValue()));
        assertThat(foundCatalogItem.size(), is(equalTo(3)));
        assertThat(foundCatalogItem.get(0).getName(), is(equalTo(matchItemName)));
        assertThat(foundCatalogItem.get(0).getDescription(), is(equalTo(matchItemDescription)));
        assertThat(foundCatalogItem.get(1).getName(), is(equalTo(matchItemName1)));
        assertThat(foundCatalogItem.get(1).getDescription(), is(equalTo(matchItemDescription1)));
        assertThat(foundCatalogItem.get(2).getName(), is(equalTo(matchItemName2)));
        assertThat(foundCatalogItem.get(2).getDescription(), is(equalTo(matchItemDescription2)));

    }

    private CatalogItem createCatalogItem(String name, String description, BigDecimal price) {
        List<String> testImages = new ArrayList<>();
        testImages.add("testImage1");
        testImages.add("testImage2");

        List<String> testCategories = new ArrayList<>();
        testCategories.add("testCategory1");
        testCategories.add("testCategory2");

        CatalogItem newCatalogItem = CatalogItem.builder()
                .name(name)
                .description(description)
                .price(price)
                .images(testImages)
                .categories(testCategories)
                .build();

        return newCatalogItem;
    }
}