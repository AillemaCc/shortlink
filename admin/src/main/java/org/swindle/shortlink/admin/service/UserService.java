package org.swindle.shortlink.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.swindle.shortlink.admin.dao.entity.UserDO;
import org.swindle.shortlink.admin.dto.req.UserLoginReqDTO;
import org.swindle.shortlink.admin.dto.req.UserRegisterReqDTO;
import org.swindle.shortlink.admin.dto.req.UserUpdateReqDTO;
import org.swindle.shortlink.admin.dto.resp.UserLoginRespDTO;
import org.swindle.shortlink.admin.dto.resp.UserRespDTO;

/**
 * 用户接口层
 */
public interface UserService extends IService<UserDO> {
    /**
     * 根据用户名查询用户信息
     * @param username
     * @return 用户返回实体
     */
    UserRespDTO getUserByUsername(String username);

    /**
     * 查看用户名是否可用（被占用）
     * @param username 用户名
     * @return 用户名存在 返回true；不存在 返回false
     */
    Boolean hasUsername(String username);

    /**
     * 注册用户
     * @param requestParam 注册用户请求参数
     */
    void Register(UserRegisterReqDTO requestParam);

    /**
     *根据用户名修改用户
     * @param requestParam 修改用户请求参数
     */
    void update(UserUpdateReqDTO requestParam);

    /**
     * 用户登录
     */
    UserLoginRespDTO login(UserLoginReqDTO requestParam);

    /**
     * 检查用户是否登录
     * @param token 携带oken
     * @return boolean登录结果
     */
    Boolean checkLogin(String username,String token);

    /**
     * 退出登录
     *
     * @param username
     * @param token
     */
    void logout(String username, String token);
}
