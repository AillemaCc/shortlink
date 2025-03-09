package org.swindle.shortlink.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.swindle.shortlink.admin.common.convention.result.Result;
import org.swindle.shortlink.admin.remote.dto.req.ShortLinkPageReqDTO;
import org.swindle.shortlink.admin.remote.dto.req.ShortLinkRecycleBinPageReqDTO;
import org.swindle.shortlink.admin.remote.dto.resp.ShortLinkPageRespDTO;

/**
 * 回收站接口层
 */
public interface RecycleBinService {

    /**
     * 分页查询回收站短链接
     *
     * @param requestParam 请求参数
     * @return 返回参数包装
     */
    Result<IPage<ShortLinkPageRespDTO>> pageRecycleBinShortLink(ShortLinkRecycleBinPageReqDTO requestParam);
}
