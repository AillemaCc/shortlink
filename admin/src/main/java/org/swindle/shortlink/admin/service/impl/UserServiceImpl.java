package org.swindle.shortlink.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.swindle.shortlink.admin.common.convention.exception.ClientException;
import org.swindle.shortlink.admin.common.enums.UserErrorCodeEnum;
import org.swindle.shortlink.admin.dao.entity.UserDO;
import org.swindle.shortlink.admin.dao.mapper.UserMapper;
import org.swindle.shortlink.admin.dto.req.UserRegisterReqDTO;
import org.swindle.shortlink.admin.dto.resp.UserRespDTO;
import org.swindle.shortlink.admin.service.UserService;

import static org.swindle.shortlink.admin.common.enums.UserErrorCodeEnum.USER_NAME_EXIST;
import static org.swindle.shortlink.admin.common.enums.UserErrorCodeEnum.USER_SAVE_ERROR;

/**
 * 用户接口实现层
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {
    private final RBloomFilter<String> userRegisterCachePenetrationBloomFilter;
    private final RedissonClient redissonClient;
    @Override
    public UserRespDTO getUserByUsername(String username) {
        LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery(UserDO.class)
                .eq(UserDO::getUsername, username);
        UserDO userDO = baseMapper.selectOne(queryWrapper);
        UserRespDTO result = new UserRespDTO();
        if(userDO != null){
            BeanUtils.copyProperties(userDO, result);       // 此方法需要判空才可以，否则会报错
            return result;
        } else {
            return null;
        }
    }


    @Override
    public Boolean hasUsername(String username) {
//        LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery(UserDO.class)
//                .eq(UserDO::getUsername, username);
//        UserDO userDO = baseMapper.selectOne(queryWrapper);
//        return userDO != null;
        //如果布隆过滤器存在username，说明不可以用
        return !userRegisterCachePenetrationBloomFilter.contains(username);
    }

    @Override
    public void Register(UserRegisterReqDTO requestParam) {
        if (!hasUsername(requestParam.getUsername())) {
            throw new ClientException(USER_NAME_EXIST);
        }
        RLock lock = redissonClient.getLock("LOCK_USER_REGISTER_KEY"+requestParam.getUsername());
        try{
            int insert = baseMapper.insert(BeanUtil.toBean(requestParam, UserDO.class));
            if(lock.tryLock()){
                if (insert < 1) {
                    throw new ClientException(USER_SAVE_ERROR);
                }
                userRegisterCachePenetrationBloomFilter.add(requestParam.getUsername());
            }
//            throw new ClientException(USER_NAME_EXIST); 谁写的代码。。
        }
        finally {
            lock.unlock();
        }
    }
}
