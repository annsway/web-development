package com.itheima.service;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Product;

import java.util.List;

public interface ProductService {
    /**
     * 查询全部的数据
     *
     * @return 全部数据的列表对象
     */
    List<Product> showAllProducts();

    /**
     * 分页查询数据
     *
     * @param page 页码
     * @param size 每页显示的数据总量
     * @return
     */
    PageInfo showAllProducts(int page, int size);

    /**
     * 分页查询数据
     *
     * @param category
     * @param page     页码
     * @param size     每页显示的数据总量
     * @return
     */
    PageInfo showByCategory(String category, int page, int size);

    /**
     * 分页查询数据
     *
     * @param productName
     * @param page        页码
     * @param size        每页显示的数据总量
     * @return
     */
    PageInfo findByProductName(String productName, int page, int size);

    /**
     * @param bookId
     * @return
     */
    Product findByProductId(String bookId);

    int updateProduct(Product product);

    int deleteProduct(String id);

    int addProduct(Product product);
//    int deleteProduct(Product product);


}

