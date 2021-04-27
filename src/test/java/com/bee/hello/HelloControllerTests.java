package com.bee.hello;

import com.bee.hello.domain.Hello;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloControllerTests {

  @LocalServerPort
  private int port;

  @Autowired
  private SecurityProperties securityProperties;

  @Autowired
  private TestRestTemplate testRestTemplate;

  @Test
  public void shouldReturn200WhenSendingRequestAsAnonymousUserToHelloController() throws Exception {
    ResponseEntity<Hello> entity = this.testRestTemplate.getForEntity("http://localhost:" + this.port + "/", Hello.class);
    
    assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(entity.getBody().getName()).contains("Stranger");
  }

  @Test
  public void shouldReturn401dWhenSendingRequestAsAnonymousUserToExitController() throws Exception {
    ResponseEntity<Hello> entity = this.testRestTemplate.getForEntity("http://localhost:" + this.port + "/exit", Hello.class);
    assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
  }

  @Test
  public void shouldReturn401WhenSendingRequestAsAnonymousUserToActuator() throws Exception {
    ResponseEntity<String> entity = this.testRestTemplate.getForEntity("http://localhost:" + this.port + "/actuator", String.class);
    assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
  }

  @Test
  public void shouldReturn200WhenSendingRequestAsAnonymousUserToActuatorHealth() throws Exception {
    ResponseEntity<String> entity = this.testRestTemplate.getForEntity("http://localhost:" + this.port + "/actuator/health", String.class);
    assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
  }

}
