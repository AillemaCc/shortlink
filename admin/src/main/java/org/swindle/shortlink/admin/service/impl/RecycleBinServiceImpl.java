package org.swindle.shortlink.admin.service.impl;



import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import org.swindle.shortlink.admin.common.biz.user.UserContext;
import org.swindle.shortlink.admin.common.convention.exception.ServiceException;
import org.swindle.shortlink.admin.common.convention.result.Result;

import org.swindle.shortlink.admin.dao.entity.GroupDO;
import org.swindle.shortlink.admin.dao.mapper.GroupMapper;
import org.swindle.shortlink.admin.remote.ShortLinkRemoteService;
import org.swindle.shortlink.admin.remote.dto.req.ShortLinkPageReqDTO;

import org.swindle.shortlink.admin.remote.dto.req.ShortLinkRecycleBinPageReqDTO;
import org.swindle.shortlink.admin.remote.dto.resp.ShortLinkPageRespDTO;
import org.swindle.shortlink.admin.service.RecycleBinService;

import java.util.List;


/**
 * 回收站接口实现层
 */
@Service
@RequiredArgsConstructor
public class RecycleBinServiceImpl implements RecycleBinService {
    private final GroupMapper groupMapper;

    ShortLinkRemoteService shortLinkRemoteService = new ShortLinkRemoteService(){};
    /**
     * 分页查询回收站短链接
     * @param requestParam 请求参数
     * @return
     */
    @Override
    public Result<IPage<ShortLinkPageRespDTO>> pageRecycleBinShortLink(ShortLinkRecycleBinPageReqDTO requestParam) {
        LambdaQueryWrapper<GroupDO> queryWrapper = Wrappers.lambdaQuery(GroupDO.class)
                .eq(GroupDO::getUsername, UserContext.getUsername())
                .eq(GroupDO::getDelFlag, 0);
        List<GroupDO> groupDOList = groupMapper.selectList(queryWrapper);
        if (CollUtil.isEmpty(groupDOList)) {
            throw new ServiceException("用户无分组信息");
        }
        requestParam.setGidList(groupDOList.stream().map(GroupDO::getGid).toList());
        return shortLinkRemoteService.pageRecycleBinShortLink(requestParam);
    }
}
