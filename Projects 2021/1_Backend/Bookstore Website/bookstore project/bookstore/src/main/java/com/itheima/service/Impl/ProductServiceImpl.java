package com.itheima.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dao.ProductDao;
import com.itheima.domain.Product;
import com.itheima.factory.MapperFactory;
import com.itheima.service.ProductService;
import com.itheima.util.TransactionUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    @Override
    public List<Product> showAllProducts() {
        SqlSession sqlSession = null;

        sqlSession = MapperFactory.getSqlSession();
        ProductDao productDao = MapperFactory.getMapper(sqlSession, ProductDao.class);
        return productDao.findAll();
    }

    @Override
    public PageInfo showAllProducts(int page, int size) {
        SqlSession sqlSession = null;
        try {
            //1.获取SqlSession
            sqlSession = MapperFactory.getSqlSession();
            //2.获取Dao
            ProductDao productDao = MapperFactory.getMapper(sqlSession, ProductDao.class);
            //3.调用Dao层操作
            PageHelper.startPage(page, size);
            List<Product> all = productDao.findAll();
            PageInfo pageInfo = new PageInfo(all);
            return pageInfo;
        } catch (Exception e) {
            throw new RuntimeException(e);
            //记录日志
        } finally {
            try {
                TransactionUtil.close(sqlSession);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public PageInfo showByCategory(String category, int page, int size) {
        SqlSession sqlSession = null;
        try {
            //1.获取SqlSession
            sqlSession = MapperFactory.getSqlSession();
            //2.获取Dao
            ProductDao productDao = MapperFactory.getMapper(sqlSession, ProductDao.class);
            //3.调用Dao层操作
            PageHelper.startPage(page, size);
            List<Product> all = productDao.findByCategory(category);
            PageInfo pageInfo = new PageInfo(all);
            return pageInfo;
        } catch (Exception e) {
            throw new RuntimeException(e);
            //记录日志
        } finally {
            try {
                TransactionUtil.close(sqlSession);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public PageInfo findByProductName(String productName, int page, int size) {
        SqlSession sqlSession = null;
        try {
            //1.获取SqlSession
            sqlSession = MapperFactory.getSqlSession();
            //2.获取Dao
            ProductDao productDao = MapperFactory.getMapper(sqlSession, ProductDao.class);
            //3.调用Dao层操作
            PageHelper.startPage(page, size);
            List<Product> all = productDao.findByProductName(productName);

            System.out.println("test " + all.get(0));

            PageInfo pageInfo = new PageInfo(all);
            return pageInfo;
        } catch (Exception e) {
            throw new RuntimeException(e);
            //记录日志
        } finally {
            try {
                TransactionUtil.close(sqlSession);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Product findByProductId(String id) {
        SqlSession sqlSession = null;
        try {
            //1.获取SqlSession
            sqlSession = MapperFactory.getSqlSession();
            //2.获取Dao
            ProductDao productDao = MapperFactory.getMapper(sqlSession, ProductDao.class);
            //3.调用Dao层操作
            Product product = productDao.findByProductId(id);

            System.out.println("test findByProductId" + product);

            return product;
        } catch (Exception e) {
            throw new RuntimeException(e);
            //记录日志
        } finally {
            try {
                TransactionUtil.close(sqlSession);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int updateProduct(Product product) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MapperFactory.getSqlSession();
            ProductDao productDao = MapperFactory.getMapper(sqlSession, ProductDao.class);
            int flag = productDao.updateProduct(product);
            TransactionUtil.commit(sqlSession);
            return flag;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                TransactionUtil.close(sqlSession);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public int deleteProduct(String id) {
//    public int deleteProduct(Product product) {

        SqlSession sqlSession = null;
        try {
            sqlSession = MapperFactory.getSqlSession();
            ProductDao productDao = MapperFactory.getMapper(sqlSession, ProductDao.class);
            int flag = productDao.deleteProduct(id);
//            int flag = productDao.deleteProduct(product);

            TransactionUtil.commit(sqlSession);
            return flag;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                TransactionUtil.close(sqlSession);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
    @Override
    public int addProduct(Product product) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MapperFactory.getSqlSession();
            ProductDao productDao = MapperFactory.getMapper(sqlSession, ProductDao.class);
            int flag = productDao.addProduct(product);
            TransactionUtil.commit(sqlSession);
            return flag;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                TransactionUtil.close(sqlSession);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
