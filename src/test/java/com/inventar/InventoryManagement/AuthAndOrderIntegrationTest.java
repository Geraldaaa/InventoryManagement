package com.inventar.InventoryManagement;

import com.inventar.InventoryManagement.dto.AuthResponseDTO;
import com.inventar.InventoryManagement.dto.LoginRequestDTO;
import com.inventar.InventoryManagement.dto.RegisterRequestDTO;
import com.inventar.InventoryManagement.dto.OrderDTO;
import com.inventar.InventoryManagement.model.User;
import com.inventar.InventoryManagement.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthAndOrderIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @AfterEach
    void cleanup() {
        userRepository.deleteAll();
    }

    @Test
    void registerAndLogin_shouldReturnToken() {
        RegisterRequestDTO register = new RegisterRequestDTO();
        register.setUsername("int_test_user");
        register.setPassword("pass1234");
        register.setFirstName("Int");
        register.setLastName("Tester");
        register.setEmail("int@tester.example");
        register.setAge(30);
        register.setPhoneNumber("0690000000");

        ResponseEntity<AuthResponseDTO> registerResp =
                restTemplate.postForEntity("/auth/register", register, AuthResponseDTO.class);

        assertThat(registerResp.getStatusCode()).isEqualTo(HttpStatus.OK);
        AuthResponseDTO body = registerResp.getBody();
        assertThat(body).isNotNull();
        assertThat(body.getAccessToken()).isNotBlank();
        assertThat(body.getUsername()).isEqualTo("int_test_user");

        LoginRequestDTO login = new LoginRequestDTO();
        login.setUsername("int_test_user");
        login.setPassword("pass1234");

        ResponseEntity<AuthResponseDTO> loginResp =
                restTemplate.postForEntity("/auth/login", login, AuthResponseDTO.class);

        assertThat(loginResp.getStatusCode()).isEqualTo(HttpStatus.OK);
        AuthResponseDTO loginBody = loginResp.getBody();
        assertThat(loginBody).isNotNull();
        assertThat(loginBody.getAccessToken()).isNotBlank();
        assertThat(loginBody.getUsername()).isEqualTo("int_test_user");
    }

    @Test
    void createOrder_withAuthToken_shouldSucceed() {
        // Register user
        RegisterRequestDTO register = new RegisterRequestDTO();
        register.setUsername("order_user");
        register.setPassword("orderpass");
        register.setFirstName("Order");
        register.setLastName("User");
        register.setEmail("order@user.example");
        register.setAge(28);
        register.setPhoneNumber("0691111111");

        ResponseEntity<AuthResponseDTO> registerResp =
                restTemplate.postForEntity("/auth/register", register, AuthResponseDTO.class);

        assertThat(registerResp.getStatusCode()).isEqualTo(HttpStatus.OK);
        String token = registerResp.getBody().getAccessToken();
        assertThat(token).isNotBlank();

        // find user id (test-side): repository used to locate created user
        List<User> users = userRepository.findAll();
        assertThat(users).isNotEmpty();
        User created = users.stream().filter(u -> "order_user".equals(u.getUsername())).findFirst().orElse(null);
        assertThat(created).isNotNull();
        Long userId = created.getId();
        assertThat(userId).isNotNull();

        // prepare headers with Bearer token
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        // create order (body can be minimal)
        OrderDTO orderDto = new OrderDTO();
        // optionally set fields on orderDto if required by your mapping

        HttpEntity<OrderDTO> request = new HttpEntity<>(orderDto, headers);

        ResponseEntity<OrderDTO> orderResponse =
                restTemplate.postForEntity("/orders/create/" + userId, request, OrderDTO.class);

        // Expect HTTP 200 (controller returns OrderDTO). Adjust assertions if your controller returns 201 etc.
        assertThat(orderResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        OrderDTO createdOrder = orderResponse.getBody();
        assertThat(createdOrder).isNotNull();
        // depending on your implementation, id may be set
        // assertThat(createdOrder.getId()).isNotNull();
    }
}
