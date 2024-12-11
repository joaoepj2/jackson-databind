package com.fasterxml.jackson;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class MainTest {
  ObjectMapper m = new ObjectMapper();
  int stuff = 10;
  String stuff2 = "bla bla bla";

  @Test
  void test() throws JsonProcessingException {
    m.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    System.out.println("###");
    System.out.println(m.toString());
    System.out.println(m.writerWithDefaultPrettyPrinter().writeValueAsString(MainTest.class));
    assertEquals(1, 1);

  }

}
