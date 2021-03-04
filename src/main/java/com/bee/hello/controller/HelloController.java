package com.bee.hello.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bee.hello.domain.Hello;

@Controller
public class HelloController {
  
  private static final String template = "Hello, %s!";
  private final AtomicLong counter = new AtomicLong();
  
  @GetMapping("/hello-world")
  @ResponseBody
  public Hello sayHello(@RequestParam(name="name", required=false, defaultValue="Stranger") String name) {
    return new Hello(counter.incrementAndGet(), String.format(template, name));
  }

}
