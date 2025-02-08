package com.chunjiez.web.controller.auth;

import com.chunjiez.common.entity.annotation.RequireAuthentication;
import com.chunjiez.web.entity.vo.Result;
import com.chunjiez.web.entity.vo.req.LoginRequest;
import com.chunjiez.web.entity.vo.resp.OnlineUser;
import com.chunjiez.web.service.authentication.AuthenticationService;
import org.springframework.web.bind.annotation.*;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2024/1/5
 */
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public Result<String> login(@RequestBody LoginRequest loginRequest) {
        return Result.success(authenticationService.login(loginRequest));
    }

    @GetMapping("/userinfo")
    @RequireAuthentication
    public Result<OnlineUser> userinfo() {
        return Result.success(authenticationService.getCurrentUser());
    }
}
