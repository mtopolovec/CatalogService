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

//    @PostConstruct
//    public void onAppStart() {
//        List<String> categories = new ArrayList<>();
//        categories.add("CPU");
//        categories.add("Computer parts");
//        List<String> images = new ArrayList<>();
//        images.add("https://images-na.ssl-images-amazon.com/images/I/61vGQNUEsGL._AC_SL1384_.jpg");
//        images.add("https://images-na.ssl-images-amazon.com/images/I/71KV0V8AxbL._AC_SL1500_.jpg");
//        images.add("https://images-na.ssl-images-amazon.com/images/I/81gqOrWLmfL._AC_SL1500_.jpg");
//        images.add("https://images-na.ssl-images-amazon.com/images/I/81ciJn85chL._AC_SL1500_.jpg");
//        images.add("https://images-na.ssl-images-amazon.com/images/I/61hCY7E9qeL._AC_SL1395_.jpg");
//        CatalogItem catalogItem = new CatalogItem();
//        catalogItem.setName("CPU AMD Ryzen 5 5600X");
//        catalogItem.setDescription("Unlocked Desktop Processor with Wraith Stealth Cooler");
//        catalogItem.setCategories(categories);
//        catalogItem.setImages(images);
//        catalogItem.setPrice(new BigDecimal("369.99"));
//        catalogItemsRepository.save(catalogItem);
//
//        categories.clear();
//        categories.add("Power Drills");
//        categories.add("Power tools");
//        categories.add("Tools and home improvement");
//
//        images.clear();
//        images.add("https://images-na.ssl-images-amazon.com/images/I/61eTJIc-wdL._AC_SL1000_.jpg");
//        images.add("https://images-na.ssl-images-amazon.com/images/I/71ZTG1h-ZEL._AC_SL1500_.jpg");
//        images.add("https://images-na.ssl-images-amazon.com/images/I/81NisyhA8fL._AC_SL1500_.jpg");
//        images.add("https://images-na.ssl-images-amazon.com/images/I/81nxHq-UW4L._AC_SL1500_.jpg");
//        images.add("https://images-na.ssl-images-amazon.com/images/I/61iBrbxkC7L._AC_SL1000_.jpg");
//        images.add("https://images-na.ssl-images-amazon.com/images/I/71QO3yMVYtL._AC_SL1000_.jpg");
//
//        CatalogItem boschDrill = new CatalogItem();
//        boschDrill.setName("Bosch drill");
//        boschDrill.setDescription("All brand new drill with witch you can put your best pictures on the wall with ease.");
//        boschDrill.setPrice(new BigDecimal("149.99"));
//        boschDrill.setCategories(categories);
//        boschDrill.setImages(images);
//        catalogItemsRepository.save(boschDrill);
//
//        categories.clear();
//        categories.add("Tools for drill");
//        categories.add("Tools and home improvement");
//
//        images.clear();
//        images.add("https://images-na.ssl-images-amazon.com/images/I/71AXZu96WJL._AC_SL1000_.jpg");
//
//        CatalogItem drillForWood = new CatalogItem();
//        drillForWood.setName("Drill for wood");
//        drillForWood.setDescription("Drill that has Classic Carbon Steel Material. High hardness, offer superior cutting power and durable.");
//        drillForWood.setPrice(new BigDecimal("39.55"));
//        drillForWood.setCategories(categories);
//        drillForWood.setImages(images);
//        catalogItemsRepository.save(drillForWood);
//
//        categories.clear();
//        categories.add("Puppets");
//        categories.add("Toys and games");
//
//        images.clear();
//        images.add("https://images-na.ssl-images-amazon.com/images/I/61ooewVtgzL._AC_SL1000_.jpg");
//        images.add("https://images-na.ssl-images-amazon.com/images/I/61ur1mrOlbL._AC_SL1000_.jpg");
//        images.add("https://images-na.ssl-images-amazon.com/images/I/61u1cJA05AL._AC_SL1000_.jpg");
//        images.add("https://images-na.ssl-images-amazon.com/images/I/61YpBGCjpxL._AC_SL1000_.jpg");
//
//        CatalogItem mandrillMonkey = new CatalogItem();
//        mandrillMonkey.setName("Mandrill monkey");
//        mandrillMonkey.setDescription("This hand-painted Mandrill figure features a mainly brown coat of fur, with accents of dark brown, light brown, white, and yellow ochre.");
//        mandrillMonkey.setPrice(new BigDecimal("10.10"));
//        mandrillMonkey.setCategories(categories);
//        mandrillMonkey.setImages(images);
//        catalogItemsRepository.save(mandrillMonkey);
//
//        categories.clear();
//        categories.add("Drill toys");
//        categories.add("Toys and games");
//
//        images.clear();
//        images.add("https://images-na.ssl-images-amazon.com/images/I/61ZvXAbdyFL._AC_SL1500_.jpg");
//
//        CatalogItem drillingToy = new CatalogItem();
//        drillingToy.setName("Drilling toy");
//        drillingToy.setDescription("Realistic drilling action & sounds!");
//        drillingToy.setPrice(new BigDecimal("18.20"));
//        catalogItemsRepository.save(drillingToy);
//    }
}
