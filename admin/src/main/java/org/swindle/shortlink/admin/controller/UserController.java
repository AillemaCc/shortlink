package org.swindle.shortlink.admin.controller;

import cn.hutool.core.bean.BeanUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.swindle.shortlink.admin.common.convention.result.Result;
import org.swindle.shortlink.admin.common.convention.result.Results;
import org.swindle.shortlink.admin.common.enums.UserErrorCodeEnum;
import org.swindle.shortlink.admin.dto.req.UserLoginReqDTO;
import org.swindle.shortlink.admin.dto.req.UserRegisterReqDTO;
import org.swindle.shortlink.admin.dto.req.UserUpdateReqDTO;
import org.swindle.shortlink.admin.dto.resp.UserActualRespDTO;
import org.swindle.shortlink.admin.dto.resp.UserLoginRespDTO;
import org.swindle.shortlink.admin.dto.resp.UserRespDTO;
import org.swindle.shortlink.admin.service.UserService;

/*
用户管理控制层
 */
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    @GetMapping("/api/shortlink/admin/v1/user/{username}")
    public Result<UserRespDTO> getUserByUsername(@PathVariable("username") String username) {
        UserRespDTO result = userService.getUserByUsername(username);
        if (result == null) {
            return new Result<UserRespDTO>().setCode(UserErrorCodeEnum.USER_NULL.code()).setMessage(UserErrorCodeEnum.USER_NULL.message());
        }
        else {
            return Results.success(result);
        }
    }

    /**
     * 根据用户名查询无脱敏信息
     * @param username
     * @return
     */
    @GetMapping("/api/shortlink/admin/v1/actual/user/{username}")
    public Result<UserActualRespDTO> getActualUserByUsername(@PathVariable("username") String username) {
        return Results.success(BeanUtil.toBean(userService.getUserByUsername(username), UserActualRespDTO.class));
    }

    /**
     * 注册时，判断用户名是否存在。
     * @param username
     * @return 存在返回true，不存在返回false
     */
    @GetMapping("/api/shortlink/admin/v1/user/has-username")
    public Result<Boolean> hasUsername(@RequestParam("username") String username) {
        return Results.success(userService.hasUsername(username));
    }

    /**
     * 注册用户
     * @param requestParam
     * @return
     */
    @PostMapping("/api/shortlink/admin/v1/user")
    public Result<Void> register(@RequestBody UserRegisterReqDTO requestParam) {
        userService.Register(requestParam);
        return Results.success();
    }

    /**
     * 修改用户
     * @param requestParam
     * @return
     */
    @PutMapping("/api/shortlink/admin/v1/user")
    public Result<Void> update(@RequestBody UserUpdateReqDTO requestParam) {
        userService.update(requestParam);
        return Results.success();
    }

    /**
     * 用户登录
     * @param requestParam
     * @return
     */
    @PostMapping("/api/shortlink/admin/v1/user/login")
    public Result<UserLoginRespDTO> login(@RequestBody UserLoginReqDTO requestParam) {
        UserLoginRespDTO userLoginRespDTO= userService.login(requestParam);
        return Results.success(userLoginRespDTO);
    }

    /**
     * 检查用户是否登录
     * @param username 用户名
     * @param token 用户登录产生的token
     * @return 已登录返回true
     */
    @GetMapping("/api/shortlink/admin/v1/user/check-login")
    public Result<Boolean> checkLogin(@RequestParam("username") String username,@RequestParam("token") String token) {
        return Results.success(userService.checkLogin(username,token));
    }
    @DeleteMapping("/api/shortlink/admin/v1/user/logout")
    public Result<Void> logout(@RequestParam("username") String username,@RequestParam("token") String token){
        userService.logout(username,token);
        return Results.success();
    }
}
