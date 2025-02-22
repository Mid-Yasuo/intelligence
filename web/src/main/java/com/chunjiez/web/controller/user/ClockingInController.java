package com.chunjiez.web.controller.user;

import com.chunjiez.web.entity.vo.Result;
import com.chunjiez.web.entity.vo.req.clock.GetClockReq;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2025/2/8
 */
@RestController
@RequestMapping("/clocking-in")
public class ClockingInController {

    @PostMapping
    public Result<String> clockIn(){
       return Result.success();
    }

    @PostMapping("/list")
    public Result<String> list(@RequestBody GetClockReq clockReq){
       return Result.success();
    }

}
