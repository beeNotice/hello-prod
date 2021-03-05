package com.bee.hello.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bee.hello.domain.Hello;

@RestController
public class HelloController {

  private static final String DEFAULT_NAME = "Stranger";

  private final AtomicLong counter = new AtomicLong();

  @GetMapping("/")
  public Hello sayHello() {
    return sayHello(DEFAULT_NAME);
  }

  @GetMapping("/hello-world")
  public Hello sayHello(@RequestParam(name = "name", required = false, defaultValue = DEFAULT_NAME) String name) {
    return new Hello(counter.incrementAndGet(), String.format("Hello, %s!", name));
  }

}
