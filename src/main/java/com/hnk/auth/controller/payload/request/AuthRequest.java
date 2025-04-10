package com.hnk.auth.controller.payload.request;

import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;
}
