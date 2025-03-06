package org.swindle.shortlink.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.swindle.shortlink.project.dao.entity.ShortLinkDO;
import org.swindle.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import org.swindle.shortlink.project.dto.req.ShortLinkPageReqDTO;
import org.swindle.shortlink.project.dto.resp.ShortLinkCreateRespDTO;
import org.swindle.shortlink.project.dto.resp.ShortLinkGroupCountQueryRespDTO;
import org.swindle.shortlink.project.dto.resp.ShortLinkPageRespDTO;

import java.util.List;

/**
 * 短链接接口层
 */
public interface ShortLinkService extends IService<ShortLinkDO> {
    /**
     * 创建短链接
     * @param requestParam 创建短链接请求参数
     * @return 短链接创建信息
     */
    ShortLinkCreateRespDTO createShortLink(ShortLinkCreateReqDTO requestParam);

    /**
     * 分页查询短链接
     * @param requestParam 分页查询短链接请求参数
     * @return 分页查询短链接分页返回
     */
    IPage<ShortLinkPageRespDTO> pageShortLink(ShortLinkPageReqDTO requestParam);

    /**
     * 查询短链接分组内数量
     * @param requestParam
     * @return
     */
    List<ShortLinkGroupCountQueryRespDTO> listGroupShortLinkCount(List<String> requestParam);
}
