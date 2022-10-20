package com.itheima.dao;

import com.itheima.domain.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductDao {
    List<Product> findAll();

    List<Product> findByCategory(@Param("category") String category);

    List<Product> findByProductName(@Param("productName") String productName);

    Product findByProductId(@Param("id") String id);

    int updateProduct(Product product);

    int deleteProduct(@Param("id") String id);

    int addProduct(Product product);

}
