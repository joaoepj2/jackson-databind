package com.fasterxml.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {
    public static void main(String[] args) throws JsonProcessingException {
        String simple = "123";
        String[] cars = { "Tesla", "Corvette", "BYD", "GWM", "Camaro" };
        ObjectMapper om = new ObjectMapper();
        String result = om.writeValueAsString(simple);
        String result2 = om.writeValueAsString(cars);
        System.out.println(result);
        System.out.println(cars);
        System.out.println(result2);

    }
}
