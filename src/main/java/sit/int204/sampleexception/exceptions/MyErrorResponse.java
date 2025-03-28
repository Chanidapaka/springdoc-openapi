package sit.int204.sampleexception.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//ใช้สำหรับการตอบกลับข้อผิดพลาดในรูปแบบ JSON เมื่อเกิดข้อผิดพลาดในระบบ
@Getter
@Setter
@RequiredArgsConstructor

//ใช้เพื่อกำหนดให้ Jackson (เครื่องมือที่ใช้แปลง Java object เป็น JSON)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MyErrorResponse {
    private final int status;  //รหัสสถานะ HTTP (เช่น 404, 500)
    private final String message; //ข้อความที่อธิบายข้อผิดพลาด
    private final String instance; //URI หรือ URL ของคำขอที่ทำให้เกิดข้อผิดพลาด
    private Instant timestamp = Instant.now(); //เวลาที่เกิดข้อผิดพลาด
    private String stackTrace; //สแต็คแทรซของข้อผิดพลาด
    private List<ValidationError> errors; //รายการของข้อผิดพลาดที่เกี่ยวข้องกับฟิลด์

    @Getter
    @Setter
    @RequiredArgsConstructor
    //คลาสนี้เก็บข้อมูลของข้อผิดพลาดที่เกี่ยวข้องกับฟิลด์ที่ตรวจสอบแล้ว
    private static class ValidationError {
        private final String field;
        private final String message;
    }

    //เมธอด
    //เมธอดนี้ช่วยในการเพิ่มข้อผิดพลาดลงในรายการ errors หากยังไม่มีรายการ errors ก็จะสร้างใหม่ และเพิ่ม ValidationError เข้าไปในลิสต์
    public void addValidationError(String field, String message) {
        if (Objects.isNull(errors)) {
            errors = new ArrayList<>();
        }
        errors.add(new ValidationError(field, message));
    }
}
