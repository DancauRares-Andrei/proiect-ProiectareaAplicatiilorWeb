package ro.brainybattle.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import ro.brainybattle.model.UserDTO;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GoodLectureTest {
        @Autowired
        private TestRestTemplate restTemplate;
        @LocalServerPort
        private int port;
        @Test
        void testareLectureExistent() throws Exception{
            String token="";
            UserDTO admin=new UserDTO("admin","ParolaComplicata",null);
            HttpEntity<UserDTO> request = new HttpEntity<>(admin);
            Map<String,String> map = new HashMap<>();
            ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:" + port + "/uac/login", request, String.class);
            token=response.getBody().substring(10,response.getBody().length()-2);
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<Object> request1 = new HttpEntity<>(null, headers);
            headers.set("Authorization", "Bearer "+token);
            ResponseEntity<String> response1=new TestRestTemplate().exchange("http://localhost:" + port + "/lecture/Preistorica", HttpMethod.GET,new HttpEntity<Object>(headers),String.class);
            assertEquals(HttpStatus.OK,response1.getStatusCode());
        }
}
