package com.example.demo.controller;


import com.example.demo.model.*;
import com.example.demo.service.*;
import com.example.demo.util.fileUploadUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin")
@SessionAttributes("loggedInUser")
public class ImportBillController {
    @Autowired
    private ImportBillService imBillService;

    @Autowired private SupplierService supplierService;

    @Autowired private ProductService productService;

    @Autowired private CategoryService categoryService;

    @Autowired private ImportProductService imProductService;

    @GetMapping("/import")
    public String getImportList(@RequestParam(value = "supplierID", required = false) Integer supplierID,
                                @RequestParam(value = "searchName", required = false) String searchName, Model model) {
        List<ImportBill> importBills;
        if(searchName != null && !searchName.isEmpty()) {
            importBills = imBillService.findImportBillByProductName(searchName);
        }
        else if(supplierID !=null && supplierID !=0) {
            importBills = imBillService.getImportBillBySupplierId(supplierID);
        }
        else {
            importBills = imBillService.getAll();
        }

        List<Supplier> suppliers = supplierService.getAllSuppliers();

        List<Product> products = productService.getAllProduct();

        List<Category> categories = categoryService.listCategory();

        model.addAttribute("imports", importBills);
        model.addAttribute("products" , products);
        model.addAttribute("suppliers", suppliers);
        model.addAttribute("categories", categories);

        return "import";
    }

    @PostMapping("/import/create")
    public String createImportBill(@ModelAttribute ImportBill importBill,
                                   @ModelAttribute Product product,
                                   @ModelAttribute ImportProduct importProduct,
                                   @RequestParam("imageNew") MultipartFile multipartFile,
                                   HttpServletRequest request) {


        try {
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            product.setImg(fileName);
            Product productCreate = productService.createProduct(product);

            ImportProduct imProduct = imProductService.save(importProduct);

            String uploadDir = "./src/main/resources/static/images/"+ productCreate.getProductID();
            fileUploadUtil.saveFile(uploadDir,fileName, multipartFile);
//           ImportBill importBillCreate = new ImportBill(productCreate, imProduct, importBill.getQuantity(), importBill.getExpiry(), importBill.getImportPrice(), importBill.getUnit(), importBill.getTotalAmount());
            ImportBill.ImportBillId id = new ImportBill.ImportBillId();
            id.setImportID(imProduct.getImportID());
            id.setProductID(productCreate.getProductID());
            importBill.setImportProduct(importProduct);
            importBill.setProduct(product);
            importBill.setId(id);
            importBill.setTotalAmount(importBill.getImportPrice() * importBill.getQuantity());
            ImportBill billcreate =  imBillService.createImportBill(importBill);

            return "redirect:/admin/import";

        }catch (Exception e){

            e.getMessage();

            return "redirect:/admin/import";
        }
    }

    @GetMapping("/import/{importID}")
    @ResponseBody
    public ResponseEntity<ImportBill> getProductById(@PathVariable int importID){
        ImportBill ibproduct = imBillService.get(importID);
        if(ibproduct != null ) {
            return ResponseEntity.ok(ibproduct);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }



}
