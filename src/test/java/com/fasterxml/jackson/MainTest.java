package com.fasterxml.jackson;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class MainTest {
  ObjectMapper m = new ObjectMapper();

  @Test
  void firstTest() throws JsonProcessingException {
    m.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    assertEquals(1, 1); // FAILS when expected differs from actual

  }

  @Test
  void objectMapperTest() throws JsonProcessingException {
    ObjectMapper m = new ObjectMapper();
    String simple = "123";
    String[] cars = { "Tesla", "Corvette", "BYD", "GWM", "Camaro" };
    String result = m.writeValueAsString(simple);
    String result2 = m.writeValueAsString(cars);
    System.out.println(result);
    System.out.println(cars);
    System.out.println(result2);
  }

  @Test
  void parserTest() throws JsonParseException, IOException {
    JsonFactory jf = new JsonFactory();
    String DOC = "{a: x, b: y}"; // FAILS when DOC value is "[a: x, b: y}"
    JsonParser jp = jf.createParser(DOC);
    assertEquals(JsonToken.START_OBJECT, jp.nextToken());
  }

}
