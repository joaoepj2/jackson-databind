package com.fasterxml.jackson;

import java.lang.reflect.Array;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {
    public static void main(String[] args) throws JsonProcessingException {
        String[] cars = { "Tesla", "Corvette", "BYD", "GWM", "Camaro" };
        ObjectMapper om = new ObjectMapper();
        System.out.println(om.writeValueAsString(cars));

    }
}
