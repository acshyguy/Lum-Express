package africa.semicolon.lumexpress.service;

import africa.semicolon.lumexpress.data.dto.request.LoginRequest;
import africa.semicolon.lumexpress.data.dto.response.LoginResponse;
import africa.semicolon.lumexpress.data.models.Customer;
import africa.semicolon.lumexpress.data.repositories.CustomerRepository;
import africa.semicolon.lumexpress.data.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class UserServiceImplTest {
    @Autowired
    private UserService userService;
    private LoginRequest loginRequest;
    @Autowired
    private CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        Customer customer = new Customer();
        customer.setFirstName("Jummie");
        customer.setLastName("Lois");
        customer.setEmail("roseadeyinka01@gmail.com");
        customer.setPassword("password");
        customerRepository.save(customer);

        loginRequest = LoginRequest.builder()
                .email("roseadeyinka01@gmail.com")
                .password("password").build();
    }

    @Test
    void loginUserTest() {
        LoginResponse response = userService.login(loginRequest);
        assertThat(response).isNotNull();
        assertThat(response.getCode()).isEqualTo(200);
    }
}