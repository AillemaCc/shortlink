package org.swindle.shortlink.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.swindle.shortlink.project.common.convention.result.Result;
import org.swindle.shortlink.project.common.convention.result.Results;
import org.swindle.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import org.swindle.shortlink.project.dto.req.ShortLinkPageReqDTO;
import org.swindle.shortlink.project.dto.resp.ShortLinkCreateRespDTO;
import org.swindle.shortlink.project.dto.resp.ShortLinkPageRespDTO;
import org.swindle.shortlink.project.service.ShortLinkService;

/**
 * 短链接控制层
 */
@RestController
@RequiredArgsConstructor
public class ShortLinkController {
    private final ShortLinkService shortLinkService;

    /**
     * 创建短链接
     * @param requestParam
     * @return
     */
    @PostMapping("/api/shortlink/v1/create")
    public Result<ShortLinkCreateRespDTO> createShortLink(@RequestBody ShortLinkCreateReqDTO requestParam){
        return Results.success(shortLinkService.createShortLink(requestParam));
    }

    @GetMapping("/api/shortlink/v1/page")
    public Result<IPage<ShortLinkPageRespDTO>> pageShortLink(ShortLinkPageReqDTO requestParam){
        return Results.success(shortLinkService.pageShortLink(requestParam));
    }
}
