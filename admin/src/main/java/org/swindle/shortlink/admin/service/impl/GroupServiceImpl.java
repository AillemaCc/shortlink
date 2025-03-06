package org.swindle.shortlink.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.swindle.shortlink.admin.common.biz.user.UserContext;
import org.swindle.shortlink.admin.common.convention.result.Result;
import org.swindle.shortlink.admin.dao.entity.GroupDO;
import org.swindle.shortlink.admin.dao.mapper.GroupMapper;
import org.swindle.shortlink.admin.dto.req.ShortLinkGroupSortReqDTO;
import org.swindle.shortlink.admin.dto.req.ShortLinkGroupUpdateReqDTO;
import org.swindle.shortlink.admin.dto.resp.ShortLinkGroupRespDTO;
import org.swindle.shortlink.admin.remote.ShortLinkRemoteService;
import org.swindle.shortlink.admin.remote.dto.resp.ShortLinkGroupCountQueryRespDTO;
import org.swindle.shortlink.admin.service.GroupService;
import org.swindle.shortlink.admin.toolkit.RandomGenerator;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * 分组服务接口实现类
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class GroupServiceImpl extends ServiceImpl<GroupMapper, GroupDO> implements GroupService{

    ShortLinkRemoteService shortLinkService = new ShortLinkRemoteService(){};
    /**
     * @param groupName 短链接分组名
     */
    @Override
    public void saveGroup(String groupName) {
        String gid;
        do {
            gid = RandomGenerator.generateRandom();
        } while (!hasGid(gid));
        GroupDO groupDO = GroupDO.builder()
                .gid(gid)
                .sortOrder(0)
                .name(groupName)
                .username(UserContext.getUsername())
                .build();
        baseMapper.insert(groupDO);

    }

    /**
     * @return
     */
    @Override
    public List<ShortLinkGroupRespDTO> listGroup() {
       //TODO 获取用户名、
        LambdaQueryWrapper<GroupDO> queryWrapper = Wrappers.lambdaQuery(GroupDO.class)
                .eq(GroupDO::getDelFlag, 0)
                .eq(GroupDO::getUsername, UserContext.getUsername())
                .orderByDesc(GroupDO::getSortOrder, GroupDO::getUpdateTime);
        List<GroupDO> groupDOList = baseMapper.selectList(queryWrapper);
        Result<List<ShortLinkGroupCountQueryRespDTO>> listResult = shortLinkService
                .listGroupShortLinkCount(groupDOList.stream().map(GroupDO::getGid).toList());
        List<ShortLinkGroupRespDTO> shortLinkGroupRespDTOList =BeanUtil.copyToList(groupDOList, ShortLinkGroupRespDTO.class);
        shortLinkGroupRespDTOList.forEach(each->{
            Optional<ShortLinkGroupCountQueryRespDTO> first = listResult.getData().stream()
                    .filter(item -> Objects.equals(item.getGid(), each.getGid()))
                    .findFirst();
            first.ifPresent(item->each.setShortLinkCount(first.get().getShortLinkCount()));
        });

        return shortLinkGroupRespDTOList;
    }

    /**
     *
     * @param requestParam 短链接分组
     */
    @Override
    public void updateGroup(ShortLinkGroupUpdateReqDTO requestParam) {
        LambdaUpdateWrapper<GroupDO> updateWrapper = Wrappers.lambdaUpdate(GroupDO.class)
                .eq(GroupDO::getUsername, UserContext.getUsername())
                .eq(GroupDO::getGid, requestParam.getGid())
                .eq(GroupDO::getDelFlag, 0);
        GroupDO groupDO = new GroupDO();
        groupDO.setName(requestParam.getName());
        baseMapper.update(groupDO,updateWrapper);
    }

    /**
     * @param gid 短链接gid
     */
    @Override
    public void delete(String gid) {
        LambdaUpdateWrapper<GroupDO> updateWrapper = Wrappers.lambdaUpdate(GroupDO.class)
                .eq(GroupDO::getUsername, UserContext.getUsername())
                .eq(GroupDO::getGid, gid)
                .eq(GroupDO::getDelFlag, 0);
        GroupDO groupDO = new GroupDO();
        groupDO.setDelFlag(1);
        baseMapper.update(groupDO,updateWrapper);
    }

    /**
     * 短链接分组排序
     * @param requestParam
     */
    @Override
    public void sortGroup(List<ShortLinkGroupSortReqDTO> requestParam) {
        requestParam.forEach(each->{
            GroupDO groupDO = GroupDO.builder()
                    .sortOrder(each.getSortOrder())
                    .build();
            LambdaUpdateWrapper<GroupDO> updateWrapper = Wrappers.lambdaUpdate(GroupDO.class)
                    .eq(GroupDO::getUsername, UserContext.getUsername())
                    .eq(GroupDO::getGid, each.getGid())
                    .eq(GroupDO::getDelFlag, 0);
            baseMapper.update(groupDO,updateWrapper);
        });

    }

    /**
     *查询该gid是否被占用，因为gid全局唯一
     * @param gid
     * @return gid被占用，返回false，未被占用，返回ture
     */
    private boolean hasGid(String gid){
        LambdaQueryWrapper<GroupDO> queryWrapper = Wrappers.lambdaQuery(GroupDO.class)
                .eq(GroupDO::getGid, gid)
                //TODO 设置用户名
                .eq(GroupDO::getUsername, UserContext.getUsername())
                .eq(GroupDO::getName, null);
        GroupDO hasGroupFlag = baseMapper.selectOne(queryWrapper);
        return hasGroupFlag == null;
    }
}
