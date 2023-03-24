package ru.sber.pm.esswfinalproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
public class RegistrationDataDto {

    @NotBlank
    @Size(min = 2, max = 50)
    @Pattern(regexp = "^[а-яА-ЯёЁ]+$", message = "Поле должно содержать только русские буквы")
    private String firstName;

    @Pattern(regexp = "^[а-яА-ЯёЁ]+$", message = "Поле должно содержать только русские буквы")
    @NotBlank
    @Size(min = 2, max = 50)
    private String lastName;

    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Поле должно содержать только латинские буквы и цифры")
    @NotBlank
    @Size(min = 3, max = 50)
    private String userName;

    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).*$", message = "Поле должно содержать строчные и прописные латинские буквы, цифры")
    @NotBlank
    @Size(min = 3, max = 150)
    private String password;

    private String role;
}
