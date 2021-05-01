package com.dept.firstAssigmentDept.repository;

import com.dept.firstAssigmentDept.model.CatalogItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogItemsRepository extends JpaRepository<CatalogItem, Long> {
}
