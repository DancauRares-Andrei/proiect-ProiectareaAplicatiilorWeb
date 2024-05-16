package ro.brainybattle.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ro.brainybattle.api.controller.APIController;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class SmokeTest {
    @Autowired
    private APIController controller;
    @Test
    void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }
}
