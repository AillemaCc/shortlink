package org.swindle.shortlink.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.swindle.shortlink.admin.dao.entity.GroupDO;
import org.swindle.shortlink.admin.dao.mapper.GroupMapper;
import org.swindle.shortlink.admin.service.GroupService;

/**
 * 分组服务接口实现类
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class GroupServiceImpl extends ServiceImpl<GroupMapper, GroupDO> implements GroupService{
}
