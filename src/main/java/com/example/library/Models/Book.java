package com.example.library.Models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class Book {
    private int id;
    @NotEmpty
    @Size(min=2,max=100, message = "Title should be between 1 to 100 characters")
    private String title;
    @Size(min=2,max=100, message = "Author should be between 1 to 100 characters")
    private String author;
    private int user_id;
    @Min(value=1900, message = "Minimum 1900 year")
    private int published_on;
}
