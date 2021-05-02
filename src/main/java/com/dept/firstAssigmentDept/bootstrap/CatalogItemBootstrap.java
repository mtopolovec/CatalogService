package com.dept.firstAssigmentDept.bootstrap;

import com.dept.firstAssigmentDept.model.CatalogItem;
import com.dept.firstAssigmentDept.repository.CatalogItemsRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class CatalogItemBootstrap {

    private final CatalogItemsRepository catalogItemsRepository;

    public CatalogItemBootstrap(CatalogItemsRepository catalogItemsRepository) {
        this.catalogItemsRepository = catalogItemsRepository;
    }

    @PostConstruct
    public void onAppStart() {
        List<String> categories = new ArrayList<>();
        categories.add("CPU");
        categories.add("Computer parts");
        List<String> images = new ArrayList<>();
        images.add("https://images-na.ssl-images-amazon.com/images/I/61vGQNUEsGL._AC_SL1384_.jpg");
        images.add("https://images-na.ssl-images-amazon.com/images/I/71KV0V8AxbL._AC_SL1500_.jpg");
        images.add("https://images-na.ssl-images-amazon.com/images/I/81gqOrWLmfL._AC_SL1500_.jpg");
        images.add("https://images-na.ssl-images-amazon.com/images/I/81ciJn85chL._AC_SL1500_.jpg");
        images.add("https://images-na.ssl-images-amazon.com/images/I/61hCY7E9qeL._AC_SL1395_.jpg");
        CatalogItem catalogItem = new CatalogItem();
        catalogItem.setName("CPU AMD Ryzen 5 5600X");
        catalogItem.setDescription("Unlocked Desktop Processor with Wraith Stealth Cooler");
        catalogItem.setCategories(categories);
        catalogItem.setImages(images);
        catalogItem.setPrice(new BigDecimal("369.99"));
        catalogItemsRepository.save(catalogItem);

        List<String> categories1 = new ArrayList<>();
        categories1.add("Power Drills");
        categories1.add("Power tools");
        categories1.add("Tools and home improvement");
        CatalogItem boschDrill = new CatalogItem();
        boschDrill.setName("Bosch drill");
        boschDrill.setCategories(categories1);
        catalogItemsRepository.save(boschDrill);

        CatalogItem drillForWood = new CatalogItem();
        drillForWood.setName("Drill for wood");
        drillForWood.setCategories(categories1);
        catalogItemsRepository.save(drillForWood);

        CatalogItem mandrillMonkey = new CatalogItem();
        mandrillMonkey.setName("Mandrill monkey");
        catalogItemsRepository.save(mandrillMonkey);

        CatalogItem drillingToy = new CatalogItem();
        drillingToy.setName("Drilling toy");
        catalogItemsRepository.save(drillingToy);
    }
}
