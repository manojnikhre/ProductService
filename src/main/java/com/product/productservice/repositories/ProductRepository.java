package com.product.productservice.repositories;

import com.product.productservice.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    @Override
    Optional<Product> findById(Long aLong);
    Product save(Product product);

    @Override
    Page<Product> findAll(Pageable pageable);
}
