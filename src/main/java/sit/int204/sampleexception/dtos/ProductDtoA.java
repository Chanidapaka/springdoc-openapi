package sit.int204.sampleexception.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

//ProductDtoA ซึ่งเป็น Data Transfer Object (DTO) สำหรับการส่งข้อมูลที่เกี่ยวข้องกับผลิตภัณฑ์
@Getter
@Setter
public class ProductDtoA {
    @NotNull //ต้องไม่เป็นค่าว่าง
    @Size(min = 5, max = 10) //ความยาวระหว่าง 5 ถึง 10 ตัวอักษร
    private String productCode; //รหัสสินค้า
    @NotNull
    @Size(min = 10, max = 100)
    private String productName;

    // เพื่อไม่ให้ฟิลด์นี้ถูกแปลงเป็น JSON และส่งกลับเมื่อทำการแปลงข้อมูลเป็น JSON
    @JsonIgnore
    private BigDecimal msrp;//ราคาขายแนะนำ

    //คืนค่าราคาของผลิตภัณฑ์ที่แปลงจาก msrp (ในประเภท BigDecimal) ไปเป็น Double เพื่อให้เหมาะสมกับการใช้งานที่มีค่าในรูปแบบตัวเลขทศนิยม
    @Min(5) //ราคาควรมีค่าระหว่าง 5 ถึง 1200
    @Max(1200)
    public Double getPrice() {
        return msrp.doubleValue();
    }
}
