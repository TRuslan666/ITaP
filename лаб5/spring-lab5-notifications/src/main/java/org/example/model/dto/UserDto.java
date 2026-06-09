package org.example.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import org.example.model.enums.UserRole;
import org.hibernate.validator.constraints.UniqueElements;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @NotBlank(message = "Имя не должно быть пустым")
    @Size(max = 100, message = "Имя не должно быть длиннее 100 символов")
    private String name;

    @NotBlank(message = "Email обязателен")
    @Email(message = "Некорректный формат email")
    @Pattern(
        regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$",
        message = "Email не соответствует требуемому шаблону"
    )
    
    @UniqueElements
    private String email;

    @Pattern(
        regexp = "^(\\+7|8)\\d{10}$", 
        message = "Некорректный формат номера телефона"
    )
    private String phone;
    
    private String deviceToken;
    private String telegramChatId;
    private LocalDateTime createdAt;
    private UserRole role;
    private Long id;
}
