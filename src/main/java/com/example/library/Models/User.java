package com.example.library.Models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {
    private int id;
    @NotEmpty
    @Size(min=2,max=100, message = "Title should be between 1 to 100 characters")
    private String full_name;
    @Min(value=1900, message = "Minimum 1900 year")
    private int birth;
}
