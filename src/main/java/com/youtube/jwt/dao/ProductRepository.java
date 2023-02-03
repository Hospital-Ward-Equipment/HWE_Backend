package com.youtube.jwt.dao;

import com.youtube.jwt.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    @Query(value = "SELECT * FROM product where id=?1", nativeQuery = true)
    Product getProductById(int productId);

    @Query(value = "update product set `all`=?1, `broken`=?2, `name`=?3, `usable`=?4 where `id`=?5", nativeQuery = true)
    Product updateProduct(int all, int broken, String name, int usable, int id);
}
