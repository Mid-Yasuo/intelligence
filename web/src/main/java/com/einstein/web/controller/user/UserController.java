package com.einstein.web.controller.user;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.einstein.database.entity.po.User;
import com.einstein.service.user.UserService;
import com.einstein.web.entity.vo.PageResult;
import com.einstein.web.entity.vo.req.user.GetUsersReq;
import com.einstein.web.entity.vo.resp.user.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2024/9/11
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private final UserService userService;

    @PostMapping("/list")
    public PageResult<List<UserVO>> list(@RequestBody GetUsersReq getUsersReq) {
        IPage<User> page = userService.listUsers(getUsersReq.getUsername(),getUsersReq.getPageParam().getPageNo(), getUsersReq.getPageParam().getPageSize());
        return new PageResult<>(page.getTotal(), page.getCurrent(), page.getSize(),
                page.getRecords().stream().map(UserVO::build).collect(Collectors.toList()));
    }
}
