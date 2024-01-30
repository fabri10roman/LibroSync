package com.demo.LibroSync.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Author {
    private String id;
    private String name;
    private Integer age;
    private String country;


}
