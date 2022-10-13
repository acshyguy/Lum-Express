package africa.semicolon.lumexpress.data.dto.request;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Validated
public class CustomerRegistrationRequest {
    @NotNull(message = "country cannot be null")
    @NotEmpty(message = "country cannot be empty")
    private String country;
    @NotNull(message = "email cannot be null")
    @Email(message = "invalid email")
    @NotEmpty(message = "email cannot be empty")
    private String email;
    @NotEmpty(message = "provide your name")
    @NotNull(message = "provide your name")
    private String firstName;
    @NotEmpty
    @NotNull
    private String password;
}
