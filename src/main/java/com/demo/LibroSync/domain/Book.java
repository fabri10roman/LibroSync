package com.demo.LibroSync.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Book {
    private String isbn;
    private String title;
    private String genre;
    private String publisher;
    private Integer year;
    private Integer authorId;
}
