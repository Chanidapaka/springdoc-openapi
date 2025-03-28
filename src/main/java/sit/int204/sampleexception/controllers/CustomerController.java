package sit.int204.sampleexception.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    @GetMapping //ระบุว่าเมื่อมีการส่งคำขอแบบ GET มาที่เส้นทาง /api/customers จะเรียกใช้ฟังก์ชัน getCustomers

    //รับค่าพารามิเตอร์ name จาก URL เช่น /api/customers?name=John
    public ResponseEntity<String> getCustomers(@RequestParam String name) {
        int x = 100/0; //โค้ดนี้จะทำให้เกิดข้อผิดพลาด (ArithmeticException) เนื่องจากการหารด้วยศูนย์
        return ResponseEntity.ok(name); //ส่งค่ากลับเป็นคำตอบ HTTP 200 (OK) พร้อมชื่อที่ส่งเข้ามาในพารามิเตอร์ name
    }
}
