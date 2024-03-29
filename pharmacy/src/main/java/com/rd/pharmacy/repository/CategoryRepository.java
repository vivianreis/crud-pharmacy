package com.rd.pharmacy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rd.pharmacy.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
	
	public List<Category> findAllByDescriptionContainingIgnoreCase(String description);

}
