package com.ichtus.braingame.multiplication.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Value;

@Value
public class UserDTO {

    @NotBlank
    String alias;
}
