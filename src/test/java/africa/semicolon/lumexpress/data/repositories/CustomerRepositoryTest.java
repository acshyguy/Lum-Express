package africa.semicolon.lumexpress.data.repositories;

import africa.semicolon.lumexpress.data.dto.request.LoginRequest;
import africa.semicolon.lumexpress.data.dto.response.LoginResponse;
import africa.semicolon.lumexpress.data.models.Customer;
import africa.semicolon.lumexpress.data.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class CustomerRepositoryTest {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private UserServiceImpl userService;
    private LoginRequest loginRequest;
    @Autowired
    private Customer customer;

    @BeforeEach
    void setUp() {
        loginRequest = LoginRequest.builder()
                .email("roseadeyinka01@gmail.com")
                .password("password")
                .build();

        customer.setEmail("roseadeyinka01@gmail.com");
        customer.setFirstName("Jummie");
        customer.setLastName("Lois");
        customer.setPassword("password");
        customerRepository.save(customer);
    }

    @Test
    void findByEmail() {
        assertThat(customerRepository.findByEmail(customer.getEmail()))
                .isNotNull();
    }

    @Test
    void loginUserTest(){
        LoginResponse response = userService.login(loginRequest);
        assertThat(response).isNotNull();
        assertThat(response.getCode()).isEqualTo(200);
    }
}