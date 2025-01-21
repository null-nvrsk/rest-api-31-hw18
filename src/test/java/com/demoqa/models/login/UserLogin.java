package com.demoqa.models.login;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserLogin {
    private String userId;
    private String username;
    private String password;
    private String token;
    private Date expires;
    @JsonProperty("created_date")
    private Date created_date;
    private boolean isActive;
}
