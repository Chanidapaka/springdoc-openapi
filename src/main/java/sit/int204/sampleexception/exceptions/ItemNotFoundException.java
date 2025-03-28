package sit.int204.sampleexception.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//แสดงว่าเมื่อมีการโยน ItemNotFoundException ใน Spring, ระบบจะตอบกลับ HTTP status เป็น 404 Not Found อัตโนมัติ. ซึ่งหมายความว่าไม่พบทรัพยากรที่ร้องขอ
@ResponseStatus(HttpStatus.NOT_FOUND)

//ItemNotFoundException extends RuntimeException:
//คลาสนี้สืบทอดมาจาก RuntimeException ซึ่งหมายความว่าเป็น Unchecked Exception
// (ข้อผิดพลาดที่ไม่จำเป็นต้องประกาศใน signature ของ method หรือจับใน try-catch block)
public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(String message) {
        super(message);
    }
}
