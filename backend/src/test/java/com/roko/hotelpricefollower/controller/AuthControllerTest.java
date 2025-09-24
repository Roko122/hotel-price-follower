package com.roko.hotelpricefollower.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.roko.hotelpricefollower.domain.Role;
import com.roko.hotelpricefollower.domain.User;
import com.roko.hotelpricefollower.dto.LoginRequest;
import com.roko.hotelpricefollower.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AuthControllerTest {

    private MockMvc mockMvc;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private ObjectMapper objectMapper;

    @Autowired
    public AuthControllerTest(MockMvc mockMvc,
                              UserRepository userRepository,
                              PasswordEncoder passwordEncoder,
                              ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.objectMapper = objectMapper;
    }

    private final String username = "testUser";
    private final String password = "secretpassword";

    @BeforeEach
    public void addAnUser() {
        userRepository.findByUsername(username).orElseGet(() -> {
            User newUser = User.builder()
                    .username(username)
                    .password(passwordEncoder.encode(password))
                    .role(Role.ADMIN)
                    .build();
            return userRepository.save(newUser);
        });
    }

    @Test
    public void testThatLoginWithCorrectCredentialsReturns200OkAndAJwt() throws Exception {
        LoginRequest loginRequest = new LoginRequest(username, password);
        String loginRequestJson = objectMapper.writeValueAsString(loginRequest);

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/api/v1/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(loginRequestJson)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.token").exists()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.expiresIn").exists()
        );
    }

    @Test
    public void testThatLoginWithWrongCredentialsReturns401() throws Exception {
        LoginRequest loginRequest = new LoginRequest(username, "wrongpassword");
        String loginRequestJson = objectMapper.writeValueAsString(loginRequest);

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/api/v1/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(loginRequestJson)
        ).andExpect(
                MockMvcResultMatchers.status().isUnauthorized()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.token").doesNotExist()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.message").value("Incorrect username or password")
        );
    }
}
