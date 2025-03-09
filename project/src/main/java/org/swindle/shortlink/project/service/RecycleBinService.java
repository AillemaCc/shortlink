package org.swindle.shortlink.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.swindle.shortlink.project.dao.entity.ShortLinkDO;
import org.swindle.shortlink.project.dto.req.RecycleBinSaveReqDTO;
import org.swindle.shortlink.project.dto.req.ShortLinkPageReqDTO;
import org.swindle.shortlink.project.dto.req.ShortLinkRecycleBinPageReqDTO;
import org.swindle.shortlink.project.dto.resp.ShortLinkPageRespDTO;

/**
 * 回收站管理接口层
 */
public interface RecycleBinService extends IService<ShortLinkDO> {
    /**
     * 保存回收站
     *
     * @param requestParam 请求参数
     */
    void saveRecycleBin(RecycleBinSaveReqDTO requestParam);

    /**
     * 分页查询短链接
     * @param requestParam 分页查询短链接请求参数
     * @return 分页查询短链接分页返回
     */
    IPage<ShortLinkPageRespDTO> pageShortLink(ShortLinkRecycleBinPageReqDTO requestParam);


}
