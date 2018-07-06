package com.ayush.demo.api;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class User {
    int id;
    int age;
    String name;
}
