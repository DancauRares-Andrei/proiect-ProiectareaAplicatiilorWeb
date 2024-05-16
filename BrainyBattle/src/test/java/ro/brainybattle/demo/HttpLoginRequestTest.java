package ro.brainybattle.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import ro.brainybattle.model.UserDTO;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HttpLoginRequestTest {
    @Autowired
    private TestRestTemplate restTemplate;
    @LocalServerPort
    private int port;
    @Test
    void testareLogareAdmin() throws Exception {
        UserDTO admin=new UserDTO("admin","ParolaComplicata",null);
        HttpEntity<UserDTO> request = new HttpEntity<>(admin);
        Map<String,String> map = new HashMap<>();
        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:" + port + "/uac/login", request, String.class);
        assertEquals(200, response.getStatusCodeValue());
    }

}
