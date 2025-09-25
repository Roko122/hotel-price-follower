package com.roko.hotelpricefollower;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.roko.hotelpricefollower.domain.Role;
import com.roko.hotelpricefollower.domain.User;
import com.roko.hotelpricefollower.dto.CreateHotelRequest;
import com.roko.hotelpricefollower.repository.UserRepository;
import com.roko.hotelpricefollower.security.CustomUserDetails;
import com.roko.hotelpricefollower.service.AuthenticationService;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.security.core.userdetails.UserDetails;

@TestComponent
public class TestUtil {

    private final AuthenticationService authenticationService;
    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;
    private final String testJwtToken;

    public TestUtil(AuthenticationService authenticationService, UserRepository userRepository, ObjectMapper objectMapper) {
        this.authenticationService = authenticationService;
        this.userRepository = userRepository;
        this.objectMapper = objectMapper;
        this.testJwtToken = generateJwtToken();
    }

    public String getJwtToken() {
        return testJwtToken;
    }

    public String generateCreateHotelRequestJson(String name, String imageUrl) throws JsonProcessingException {
        CreateHotelRequest hotelCreateDto = new CreateHotelRequest(name, imageUrl);

        return objectMapper.writeValueAsString(hotelCreateDto);
    }

    private String generateJwtToken() {
        User user = User.builder()
                .username("test")
                .password("test")
                .role(Role.USER)
                .build();
        User savedUser = userRepository.save(user);
        UserDetails userDetails = new CustomUserDetails(savedUser);

        return authenticationService.generateToken(userDetails);
    }
}
