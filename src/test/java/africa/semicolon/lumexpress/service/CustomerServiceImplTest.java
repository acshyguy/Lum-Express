package africa.semicolon.lumexpress.service;

import africa.semicolon.lumexpress.data.dto.request.CustomerRegistrationRequest;
import africa.semicolon.lumexpress.data.dto.request.UpdateCustomerDetails;
import africa.semicolon.lumexpress.data.dto.response.CustomerRegistrationResponse;
import africa.semicolon.lumexpress.data.service.CustomerService;
import africa.semicolon.lumexpress.util.LumExpressUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CustomerServiceImplTest {

    @Autowired
    private CustomerService customerService;

    private CustomerRegistrationRequest request;
    @BeforeEach
    void setUp() {
        request = CustomerRegistrationRequest
                .builder()
                .email("roseadeyinka01@gmail.com")
                .password("test Password")
                .country("Nigeria")
                .build();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void register() throws FileNotFoundException {
        CustomerRegistrationResponse customerRegistrationResponse=
                customerService.register(request);
        assertThat(customerRegistrationResponse).isNotNull();
        assertThat(customerRegistrationResponse.getMessage())
                .isNotNull();
        assertThat(customerRegistrationResponse.getUserId())
                .isGreaterThan(0);
        assertThat(customerRegistrationResponse.getCode())
                .isEqualTo(201);
    }

    @Test
    void loginCustomerTest() {
    }

    @Test
    void updateProfile() throws FileNotFoundException {
        CustomerRegistrationResponse customerRegistrationResponse=
                customerService.register(request);
        UpdateCustomerDetails details = UpdateCustomerDetails
                .builder()
                .customerId(customerRegistrationResponse.getUserId())
                .imageUrl(LumExpressUtils.getMockCloudinaryImageUrl())
                .lastName("testLastName")
                .city("Lagos")
                .street("Herbert Macaulay")
                .buildingNumber(312)
                .phoneNumber("09012345678")
                .build();
        String updateResponse = customerService.updateCustomerProfile(details);
        assertThat(updateResponse).isNotNull();
        assertThat(updateResponse.contains("success")).isTrue();
    }
}