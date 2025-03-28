package sit.int204.sampleexception.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import sit.int204.sampleexception.entities.Product;
import sit.int204.sampleexception.exceptions.ItemNotFoundException;
import sit.int204.sampleexception.repositories.ProductRepo;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepo productRepo;

    //ฟังก์ชันนี้ใช้เพื่อดึงข้อมูลสินค้าทั้งหมดจากฐานข้อมูลในรูปแบบ Page (ใช้สำหรับการแบ่งหน้า) โดยรับพารามิเตอร์ page (หมายเลขหน้า) และ size (ขนาดของแต่ละหน้า) จากนั้นส่งคืนข้อมูลในรูปแบบ Page<Product> ซึ่งสามารถใช้ได้ร่วมกับ pagination.
    //ใช้ productRepo.findAll(PageRequest.of(page, size)) สำหรับการค้นหาข้อมูลในรูปแบบแบ่งหน้า
    public Page<Product> findAll(int page, int size) {
        return productRepo.findAll(PageRequest.of(page, size));
    }

    //ฟังก์ชันนี้ใช้เพื่อดึงข้อมูลสินค้าทั้งหมดจากฐานข้อมูลแบบไม่แบ่งหน้า และส่งคืนข้อมูลในรูปแบบ List<Product>.
    public List<Product> findAll() {
        return productRepo.findAll();
    }
    public Product findById(String productCode) {
        return productRepo.findById(productCode).orElseThrow(
                () -> new ItemNotFoundException("Product id "
                + productCode + " does not exist !!!")
        );
    }

}
