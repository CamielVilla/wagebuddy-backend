package nl.thelastages.website;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("thelastages")
public class EmailController {
    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/addemail")
    public ResponseEntity<Boolean> addEmail (@Valid @RequestBody CreateEmailDto dto) {
        return ResponseEntity.ok(emailService.addEmail(dto));
    }
}
