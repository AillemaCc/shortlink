package org.swindle.shortlink.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.*;
import org.swindle.shortlink.admin.common.convention.result.Result;
import org.swindle.shortlink.admin.common.convention.result.Results;
import org.swindle.shortlink.admin.dto.req.ShortLinkUpdateReqDTO;
import org.swindle.shortlink.admin.remote.ShortLinkRemoteService;
import org.swindle.shortlink.admin.remote.dto.req.ShortLinkCreateReqDTO;
import org.swindle.shortlink.admin.remote.dto.req.ShortLinkPageReqDTO;
import org.swindle.shortlink.admin.remote.dto.resp.ShortLinkCreateRespDTO;
import org.swindle.shortlink.admin.remote.dto.resp.ShortLinkPageRespDTO;

/**
 * 短链接控制层
 */
@RestController
public class ShortLinkController {
    //TODO 后续重构为Spring Cloud Feign调用
    ShortLinkRemoteService shortLinkService = new ShortLinkRemoteService(){};

    /**
     * 创建短链接
     * @param requestParam
     * @return
     */
    @PostMapping("/api/shortlink/admin/v1/create")
    public Result<ShortLinkCreateRespDTO> createShortLink(@RequestBody ShortLinkCreateReqDTO requestParam){
        return shortLinkService.createShortLink(requestParam);
    }

    /**
     * 分页查询短链接
     * @param requestParam
     * @return
     */
    @GetMapping("/api/shortlink/admin/v1/page")
    public Result<IPage<ShortLinkPageRespDTO>> pageShortLink(ShortLinkPageReqDTO requestParam){

       return shortLinkService.pageShortLink(requestParam);
    }

    /**
     * 修改短链接信息
     * @param requestParam
     * @return
     */
    @PostMapping("/api/shortlink/admin/v1/update")
    public Result<Void> updateShortLink(@RequestBody ShortLinkUpdateReqDTO requestParam){
        shortLinkService.updateShortLink(requestParam);
        return Results.success();
    }
}
