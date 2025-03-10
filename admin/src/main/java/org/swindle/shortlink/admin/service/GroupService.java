package org.swindle.shortlink.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.swindle.shortlink.admin.dao.entity.GroupDO;
import org.swindle.shortlink.admin.dto.req.ShortLinkGroupSortReqDTO;
import org.swindle.shortlink.admin.dto.req.ShortLinkGroupUpdateReqDTO;
import org.swindle.shortlink.admin.dto.resp.ShortLinkGroupRespDTO;

import java.util.List;

/**
 * 短链接分组接口层
 */
public interface GroupService extends IService<GroupDO> {
    /**
     * 新增短链接分组
     * @param groupName 短链接分组名
     */
    void saveGroup(String groupName);

    /**
     * 新增短链接分组
     * @param groupName 短链接分组名
     */
    void saveGroup(String username,String groupName);

    /**
     * 查询用户短链接分组集合
     * @return 短链接分组集合
     */
    List<ShortLinkGroupRespDTO> listGroup();

    /**
     * 修改短链接分组
     * @param requestParam 短链接分组
     */
    void updateGroup(ShortLinkGroupUpdateReqDTO requestParam);

    /**
     * 删除短链接分组
     * @param gid 短链接gid
     */
    void delete(String gid);

    /**
     * 短链接分组排序
     * @param requestParam
     */
    void sortGroup(List<ShortLinkGroupSortReqDTO> requestParam);
}
