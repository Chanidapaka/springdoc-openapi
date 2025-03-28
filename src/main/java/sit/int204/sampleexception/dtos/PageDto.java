package sit.int204.sampleexception.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

//สำหรับใช้ในการส่งข้อมูลแบบแบ่งหน้า (pagination) โดยใช้ Generic Type T เพื่อให้รองรับข้อมูลประเภทต่าง ๆ ที่จะถูกส่งใน content
@Getter
@Setter
public class PageDto<T> {
    private List<T> content; //เก็บรายการข้อมูลที่ถูกแบ่งหน้า
    private Boolean last; //ระบุว่าเป็นหน้าสุดท้ายหรือไม่ (boolean)
    private Boolean first; //ระบุว่าเป็นหน้าต้นทางหรือไม่ (boolean)
    private Integer totalPages; //จำนวนหน้าทั้งหมด (จำนวนของหน้าในข้อมูล)
    private Integer size; //จำนวนข้อมูลในแต่ละหน้า (จำนวนรายการในหน้าปัจจุบัน)

    @JsonIgnore //ฟิลด์ number จะถูกละเว้นจากการแสดงผลใน JSON
    private Integer number; //หมายเลขของหน้า

    //มธอดนี้จะคืนค่า number ซึ่งเป็นหมายเลขของหน้าปัจจุบัน (ถูกใช้แทนฟิลด์ number เพื่อให้มีชื่อที่เหมาะสมและสามารถใช้งานได้อย่างชัดเจน)
    public Integer getPageNumber() {
        return number;
    }
}
//คลาสนี้ช่วยให้สามารถจัดการข้อมูลที่มีการแบ่งหน้า (pagination) และส่งข้อมูลที่เกี่ยวข้องไปยังผู้ใช้ โดยรองรับประเภทข้อมูลที่หลากหลายผ่าน Generic Type T
