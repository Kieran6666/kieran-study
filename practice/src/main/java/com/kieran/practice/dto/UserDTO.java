package com.kieran.practice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    @NotBlank(message = "姓名不能为空")
    private String name;

    @Min(value = 1, message = "年龄不能小于1岁")
    private String age;

    private Integer sex;
}
