package org.example.dto;

import jakarta.validation.constraints.NotBlank;

public class SkillDto {
    
    private Long id;
    
    @NotBlank(message = "Tên kỹ năng không được để trống")
    private String name;

    public SkillDto() {}

    public SkillDto(String name) {
        this.name = name;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}