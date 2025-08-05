package com.example.librarysystem.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class BookDto {
    private Long id;

    @NotEmpty(message = "Tên sách không được để trống")
    private String title;

    @NotEmpty(message = "Tên tác giả không được để trống")
    private String author;

    @NotNull(message = "Số lượng không được để trống")
    @Min(value = 0, message = "Số lượng không được là số âm")
    private Integer quantity;
}