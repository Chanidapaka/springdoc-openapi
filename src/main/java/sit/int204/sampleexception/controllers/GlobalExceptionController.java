package sit.int204.sampleexception.controllers;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sit.int204.sampleexception.exceptions.ItemNotFoundException;
import sit.int204.sampleexception.exceptions.MyErrorResponse;

//เพื่อจัดการข้อผิดพลาดที่เกิดขึ้นในแอปพลิเคชันแบบรวมศูนย์ (global exception handling)
//โดยการใช้ @ExceptionHandler เพื่อจับและจัดการข้อผิดพลาดจาก Exception ต่างๆ ที่อาจเกิดขึ้นในแอปพลิเคชัน
@RestControllerAdvice

public class GlobalExceptionController {
    @Operation(hidden = false)
    @ExceptionHandler(IllegalArgumentException.class)

    //จัดการกับ IllegalArgumentException ซึ่งจะเกิดขึ้นเมื่อมีการให้ค่าพารามิเตอร์ที่ไม่ถูกต้อง
    public ResponseEntity<MyErrorResponse> handleIllegalArgumentException(
            IllegalArgumentException ex, HttpServletRequest request) {
        MyErrorResponse myErrorResponse = new MyErrorResponse(
                HttpStatus.BAD_REQUEST.value(), ex.getMessage(), request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(myErrorResponse);
    }
    @Operation(hidden = false)
    @ExceptionHandler(MissingServletRequestParameterException.class)

    //จัดการกับ MissingServletRequestParameterException เมื่อมีพารามิเตอร์ที่จำเป็นในคำขอ (request) หายไป
    public ResponseEntity<MyErrorResponse> handleMissingServletRequestParameterException(
            MissingServletRequestParameterException ex, HttpServletRequest request) {
        MyErrorResponse myErrorResponse = new MyErrorResponse(
                HttpStatus.BAD_REQUEST.value(), ex.getMessage(), request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(myErrorResponse);
    }
    //จัดการกับ ItemNotFoundException เมื่อไม่พบข้อมูลหรือไอเทมที่ค้นหา
    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<MyErrorResponse> handleItemNotFoundException(
            ItemNotFoundException ex, HttpServletRequest request) {
        MyErrorResponse myErrorResponse = new MyErrorResponse(
                HttpStatus.NOT_FOUND.value(), ex.getMessage(), request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(myErrorResponse);
    }

    //ดการกับข้อผิดพลาดทั่วไป (Exception) ที่ไม่สามารถจับได้โดย Exception อื่นๆ
    //และจะตอบกลับด้วยข้อความข้อผิดพลาดที่เหมาะสม
    @ExceptionHandler(Exception.class)
    public ResponseEntity<MyErrorResponse> handleException(Exception ex, HttpServletRequest request) {
        MyErrorResponse myErrorResponse = new MyErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value()
                , "Something Wrong, Please contact back-end support"
                , request.getRequestURI()
        );
        myErrorResponse.setStackTrace(ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(myErrorResponse);
    }
    //ใช้ในการสร้างข้อมูลการตอบกลับข้อผิดพลาด พร้อมสถานะ HTTP, ข้อความข้อผิดพลาด, และ URI ของคำขอที่เกิดข้อผิดพลาด
    //ResponseEntity จะส่งการตอบกลับไปยังผู้ใช้ที่ทำการร้องขอ พร้อมข้อมูลข้อผิดพลาดที่เหมาะสมตามประเภทของข้อผิดพลาดที่เกิดขึ้น
}
