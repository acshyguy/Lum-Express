package africa.semicolon.lumexpress.controller;

import africa.semicolon.lumexpress.data.dto.request.CustomerRegistrationRequest;
import africa.semicolon.lumexpress.data.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.io.FileNotFoundException;

@RequestMapping("/api/v1/customer")
@AllArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    @PostMapping()
    public ResponseEntity<?> register(@Valid @RequestBody CustomerRegistrationRequest customerRegistrationRequest) throws FileNotFoundException {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(customerService.register(customerRegistrationRequest));
    }



}
