package com.example.demo.service;


import com.example.demo.model.ImportBill;
import com.example.demo.repository.ImportBillRepository;
import com.example.demo.repository.ImportProductRepository;
import com.example.demo.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class ImportBillService {

    @Autowired
    private ImportBillRepository imBillRepository;

    @Autowired private ProductRepository productRepository;

    @Autowired private ImportProductRepository imProductRepository;

    public List<ImportBill> getAll(){
        return  imBillRepository.findAll();
    }

    public List<ImportBill> getImportBillBySupplierId(int supplierId) {
        return imBillRepository.findBySupplierID(supplierId);
    }

    public List<ImportBill> findImportBillByProductName(String searchName) {
        return imBillRepository.findImportBillByProductName(searchName);
    }


    @Transactional
    public ImportBill createImportBill(ImportBill importBill)
    {
        if(importBill != null) {
            return imBillRepository.save(importBill);
        }
        else {
            return null;
        }
    }

    public ImportBill get(int importID) {
        ImportBill ib = imBillRepository.findImportBillsByImportID(importID);
        return ib;
    }
    public ImportBill getImportBillByProductId(Integer productID) {
        ImportBill ib = imBillRepository.findByProductID(productID);
        return ib;
    }
}