package sit.int204.sampleexception.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    @GetMapping
    public ResponseEntity<String> getCustomers(@RequestParam String name) {
        int x = 100/0;
        return ResponseEntity.ok(name);
    }
}
