package com.demoqa.models.login;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginResponseModel {
    private String userId;
    private String username;
    private String password;
    private String token;
    private Date expires;
    private Date created_date;
    private boolean isActive;
}
