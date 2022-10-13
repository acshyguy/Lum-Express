package africa.semicolon.lumexpress.data.service;

import africa.semicolon.lumexpress.data.dto.request.CustomerRegistrationRequest;
import africa.semicolon.lumexpress.data.dto.request.EmailNotificationRequest;
import africa.semicolon.lumexpress.data.dto.request.UpdateCustomerDetails;
import africa.semicolon.lumexpress.data.dto.response.CustomerRegistrationResponse;
import africa.semicolon.lumexpress.data.models.Address;
import africa.semicolon.lumexpress.data.models.Cart;
import africa.semicolon.lumexpress.data.models.Customer;
import africa.semicolon.lumexpress.data.models.VerificationToken;
import africa.semicolon.lumexpress.data.repositories.CustomerRepository;
import africa.semicolon.lumexpress.data.service.notification.EmailNotificationService;
import africa.semicolon.lumexpress.exception.UserNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService{
    private final CustomerRepository customerRepository;
    private final ModelMapper mapper;
    private final EmailNotificationService emailNotificationService;
    private final VerificationTokenService verificationTokenService;

    @Override
    public CustomerRegistrationResponse register(CustomerRegistrationRequest registerRequest) throws FileNotFoundException {
        Customer customer = mapper.map(registerRequest, Customer.class);
        customer.setCart(new Cart());
        setCustomerAddress(registerRequest, customer);
        Customer savedCustomer = customerRepository.save(customer);
        log.info("customer to be saved in db::{}", savedCustomer);
        VerificationToken token =verificationTokenService.createToken(savedCustomer.getEmail());
        emailNotificationService.sendHtmlMail(buildEMailNotificationRequest(token,
                savedCustomer.getFirstName()));
        return registrationResponseBuilder(savedCustomer);
    }

    private EmailNotificationRequest buildEMailNotificationRequest(VerificationToken verificationToken, String customerName) throws FileNotFoundException {
        String messageN= getEmailTemplate();
        String email = getEmailTemplate();
        String mail = null;
        if (email != null){
            var verificationUrl = "http://localhost:8080/api/v1/customer/verify/"+verificationToken.getToken();
            mail = String.format(email, customerName, verificationUrl);
            log.info("mailed url -> {}", verificationUrl);
        }
        return EmailNotificationRequest.builder()
                .userEmail(verificationToken.getUserEmail())
                .mailContent(mail)
                .build();
    }

    private String getEmailTemplate() throws FileNotFoundException {
        try (BufferedReader bufferedReader = new  BufferedReader(new FileReader
                ("/home/rose/Documents/springboot-projects/lumExpress/src/main/resources/welcome.txt"))){
            return bufferedReader.lines().collect(Collectors.joining());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void setCustomerAddress(CustomerRegistrationRequest registerRequest, Customer customer) {
        Address customerAddress = new Address();
        customerAddress.setCountry(registerRequest.getCountry());
        customer.getAddresses().add(customerAddress);
    }

    private CustomerRegistrationResponse registrationResponseBuilder(
                                            Customer customer){
        return CustomerRegistrationResponse.builder()
                .message("success")
                .userId(customer.getId())
                .code(201)
                .build();
    }

        @Override
    public String updateCustomerProfile(UpdateCustomerDetails updateCustomerDetails) {
        Customer customerUpdate = getCustomer(updateCustomerDetails);
        log.info("before update -> {}", customerUpdate);
        mapper.map(updateCustomerDetails, customerUpdate);
        log.info("updated guy -> {}", customerUpdate);
        Set<Address> customerAddressList =
                customerUpdate.getAddresses();
        log.info("addresses{}");
        Optional<Address> foundAddress = customerAddressList.stream()
                .findFirst();
        if (foundAddress.isPresent()) applyAddressUpdate(foundAddress.get(), updateCustomerDetails);
        customerUpdate.getAddresses().add(foundAddress.get());
        Customer updatedCustomer = customerRepository.save(customerUpdate);
        return String.format("%s details updated successfully", updatedCustomer.getFirstName());
    }

    private void applyAddressUpdate(Address address, UpdateCustomerDetails updateCustomerDetails) {
        address.setBuildingNumber(updateCustomerDetails.getBuildingNumber());
        address.setCity(updateCustomerDetails.getCity());
        address.setStreet(updateCustomerDetails.getStreet());
        address.setState(updateCustomerDetails.getState());
    }

    private Customer getCustomer(UpdateCustomerDetails updateCustomerDetails) {
        return customerRepository.findById(updateCustomerDetails.getCustomerId())
                .orElseThrow(() -> new UserNotFoundException(String.format("customer with id %d, not found",
                        updateCustomerDetails.getCustomerId())));
    }


}
