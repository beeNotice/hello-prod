package com.bee.hello.controller;

import com.bee.hello.domain.Hello;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class HelloController {

  private static final Logger LOG = LoggerFactory.getLogger(HelloController.class);
  private static final String DEFAULT_NAME = "Stranger";

  private final AtomicLong counter = new AtomicLong();

  @GetMapping("/")
  public Hello hello(@RequestParam(name = "name", required = false, defaultValue = DEFAULT_NAME) String name) {
    return new Hello(counter.incrementAndGet(), String.format("Hello, %s!", name));
  }

}
