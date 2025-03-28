package sit.int204.sampleexception.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//ซึ่งใช้สำหรับการตอบกลับข้อผิดพลาดในรูปแบบที่เหมาะสมกับการสร้างเอกสาร API
@Getter
@Setter
public class SimpleMyErrorResponseForApiDoc {
    @Schema(example = "404") //ใช้ @Schema เพื่อแสดงตัวอย่างค่าในเอกสาร API
    private int status; //รหัสสถานะ HTTP

    @Schema(example = "Resource NOT FOUND") //ใช้ @Schema เพื่อแสดงตัวอย่างค่าในเอกสาร API
    private String message;

    @Schema(example = "/api/products/S10_123")
    private String instance;

    private Instant timestamp = Instant.now();
}
