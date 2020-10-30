package com.rd.pharmacy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rd.pharmacy.model.Product;
import com.rd.pharmacy.repository.ProductRepository;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductRepository repository;
	
	public ResponseEntity<List<Product>> getAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> getById(@PathVariable long id){
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Product>> getByName (@PathVariable String name){
		return ResponseEntity.ok(repository.findAllByNameContainingIgnoreCase(name));
	}
	
	@PostMapping
	public ResponseEntity<Product> post (@RequestBody Product product){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(repository.save(product));
	}
	
	@PutMapping
	public ResponseEntity<Product> put (@RequestBody Product product){
		return ResponseEntity.ok(repository.save(product));
	}
	
	@DeleteMapping("/{id}")
	public void delete (@PathVariable long id) {
		repository.deleteById(id);
	}
	
}
