package com.example.demo.service;

import com.example.demo.exception.CategoryNotFoundException;
import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.repository.CategoryRepository;

import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired private CategoryRepository repo;

    @Autowired private ProductRepository productRepository;
//    public List<Category> listAll(){
//        return (List<Category>) repo.findAll();
//    }

    public List<Category> listCategory(){
        return (List<Category>) repo.findCategory();
    }

    public void save(Category category) {
        repo.save(category);
    }

    public Category get(Integer categoryID) throws CategoryNotFoundException {
        Optional<Category> result = repo.findById(categoryID);
        if(result.isPresent()){
            return result.get();
        }
        throw  new CategoryNotFoundException("Could not find any category with ID: " + categoryID);
    }

    public String softdelete(Integer categoryID){
        List<Product>products = productRepository.findByCategoryId(categoryID);
//        productRepository.deleteAll(products);
        for (Product product : products) {
            product.setDeleted(true);
            productRepository.save(product);
        }

        Optional<Category> optionalCategory = repo.findById(categoryID);
        Category category = optionalCategory.get();
        category.setDeleted(true);
        repo.save(category);

        return "Xóa Danh mục thành công!";
    }

    public String delete(Integer categoryID) {
        List<Product>products = productRepository.findByCategoryId(categoryID);
        productRepository.deleteAll(products);

        repo.deleteById(categoryID);
        return "Xóa Danh mục thành công!";
    }

    public String restore(Integer categoryID){
        List<Product>products = productRepository.findByCategoryId(categoryID);
        for (Product product : products) {
            product.setDeleted(false);
            productRepository.save(product);
        }

        Optional<Category> optionalCategory = repo.findById(categoryID);
        Category category = optionalCategory.get();
        category.setDeleted(false);
        repo.save(category);

        return "khôi phục Danh mục thành công!";
    }

    public List<Category> findCategoryByName(String searchCategory) {
        return repo.findByCategoryNameContainingIgnoreCase(searchCategory);
    }

    public List<Category> listCategoryGarbage() {
        return (List<Category>) repo.findCategoryGarbage();
    }
}
