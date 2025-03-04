package org.swindle.shortlink.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.swindle.shortlink.admin.dao.entity.UserDO;
import org.swindle.shortlink.admin.dto.req.UserRegisterReqDTO;
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
     * @param username
     * @return 用户名存在 返回true；不存在 返回false
     */
    Boolean hasUsername(String username);

    /**
     * 注册用户请求参数
     * @param requestParam
     */
    void Register(UserRegisterReqDTO requestParam);
}
