package com.fasterxml.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {
    public static void main(String[] args) throws JsonProcessingException {
        String simple = "123";
        // String[] cars = { "Tesla", "Corvette", "BYD", "GWM", "Camaro" };
        ObjectMapper om = new ObjectMapper();
        System.out.println(om.writeValueAsString(simple));

    }
}
