package com.example.library.Models;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {
    private int id;
    private String full_name;
    private int birth;
}
