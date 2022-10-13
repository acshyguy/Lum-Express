package africa.semicolon.lumexpress.controller;

import africa.semicolon.lumexpress.data.dto.request.CustomerRegistrationRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
@SpringBootTest
class CustomerControllerTest {


    private CustomerRegistrationRequest request;

    @BeforeEach
    void setUp() {
        request = CustomerRegistrationRequest.builder()
                .email("test@gmail.com")
                .password("jdc")
                .country("Naija")
                .build();

    }

    @Test
    void requestSuccessful(){
        assertThat(request).isNotNull();
    }
//    @Test
//    void registerControllerTest() throws Exception {
//        ObjectMapper mapper = new ObjectMapper();
//        mockMvc.perform(MockMvcRequestBuilders
//                .post("/api/v1/customer")
//                .contentType("application/json")
//                .content(mapper.writeValueAsString(request)))
//                .andExpect(MockMvcResultMatchers.status().is(201))
//                .andDo(MockMvcResultHandlers.print());
//
//    }
}