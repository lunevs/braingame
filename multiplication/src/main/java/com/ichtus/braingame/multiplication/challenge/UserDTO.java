package com.ichtus.braingame.multiplication.challenge;

import jakarta.validation.constraints.NotBlank;
import lombok.Value;

@Value
public class UserDTO {

    @NotBlank
    String alias;
}
