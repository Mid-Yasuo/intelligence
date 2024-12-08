package com.einstein.web.controller.auth;

import com.einstein.common.entity.annotation.RequireAuthentication;
import com.einstein.web.entity.vo.Result;
import com.einstein.web.entity.vo.req.LoginRequest;
import com.einstein.web.entity.vo.resp.OnlineUser;
import com.einstein.web.service.authentication.AuthenticationService;
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
