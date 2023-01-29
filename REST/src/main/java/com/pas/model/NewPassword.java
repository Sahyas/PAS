package com.pas.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NewPassword {
    public NewPassword(String newPassword, String oldPassword) {
        this.newPassword = newPassword;
        this.oldPassword = oldPassword;
    }

    public NewPassword() {
    }


    private String newPassword;


    private String oldPassword;
}
