package com.bee.hello;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bee.hello.domain.Hello;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloProdApplicationTests {

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate testRestTemplate;

  @Test
  public void shouldReturn200WhenSendingRequestToController() throws Exception {
    
    ResponseEntity<Hello> entity = this.testRestTemplate.getForEntity("http://localhost:" + this.port + "/hello-world", Hello.class);
    
    assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(entity.getBody().getName()).contains("Stranger");
  }
  
  @Test
  public void shouldReturnSpecifiedNameWhenSendingRequestToController() throws Exception {
    
    ResponseEntity<Hello> entity = this.testRestTemplate.getForEntity("http://localhost:" + this.port + "/hello-world?name=Lol", Hello.class);
    
    assertThat(entity.getBody().getName())
      .contains("Lol")
      .doesNotContain("Stranger");
  }

}
