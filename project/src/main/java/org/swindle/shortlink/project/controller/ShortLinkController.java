package org.swindle.shortlink.project.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.baomidou.mybatisplus.core.metadata.IPage;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.swindle.shortlink.project.common.convention.result.Result;
import org.swindle.shortlink.project.common.convention.result.Results;
import org.swindle.shortlink.project.dto.req.ShortLinkBatchCreateReqDTO;
import org.swindle.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import org.swindle.shortlink.project.dto.req.ShortLinkPageReqDTO;
import org.swindle.shortlink.project.dto.req.ShortLinkUpdateReqDTO;
import org.swindle.shortlink.project.dto.resp.ShortLinkBatchCreateRespDTO;
import org.swindle.shortlink.project.dto.resp.ShortLinkCreateRespDTO;
import org.swindle.shortlink.project.dto.resp.ShortLinkGroupCountQueryRespDTO;
import org.swindle.shortlink.project.dto.resp.ShortLinkPageRespDTO;
import org.swindle.shortlink.project.handler.CustomBlockHandler;
import org.swindle.shortlink.project.service.ShortLinkService;

import java.util.List;

/**
 * 短链接控制层
 */
@RestController
@RequiredArgsConstructor
public class ShortLinkController {
    private final ShortLinkService shortLinkService;

    /**
     * 短链接跳转原始链接
     */
    @GetMapping("/{short-uri}")
    public void restoreUrl(@PathVariable("short-uri") String shortUri, ServletRequest request, ServletResponse response) {
        shortLinkService.restoreUrl(shortUri, request, response);
    }


    /**
     * 创建短链接
     * @param requestParam
     * @return
     */
    @PostMapping("/api/shortlink/v1/create")
    @SentinelResource(
            value = "create_short-link",
            blockHandler = "createShortLinkBlockHandlerMethod",
            blockHandlerClass = CustomBlockHandler.class
    )
    public Result<ShortLinkCreateRespDTO> createShortLink(@RequestBody ShortLinkCreateReqDTO requestParam){
        return Results.success(shortLinkService.createShortLink(requestParam));
    }

    /**
     * 修改短链接信息
     * @param requestParam
     * @return
     */
    @PostMapping("/api/shortlink/v1/update")
    public Result<Void> updateShortLink(@RequestBody ShortLinkUpdateReqDTO requestParam){
        shortLinkService.updateShortLink(requestParam);
        return Results.success();
    }
    /**
     * 分页查询短链接
     * @param requestParam
     * @return
     */
    @GetMapping("/api/shortlink/v1/page")
    public Result<IPage<ShortLinkPageRespDTO>> pageShortLink(ShortLinkPageReqDTO requestParam){
        return Results.success(shortLinkService.pageShortLink(requestParam));
    }
    /**
     * 查询短链接分组内数量
     * @param requestParam
     * @return
     */
    @GetMapping("/api/shortlink/v1/count")
    public Result<List<ShortLinkGroupCountQueryRespDTO>> listGroupShortLinkCount(@RequestParam List<String> requestParam){
        return Results.success(shortLinkService.listGroupShortLinkCount(requestParam));
    }

    /**
     * 批量创建短链接
     */
    @PostMapping("/api/shortlink/v1/create/batch")
    public Result<ShortLinkBatchCreateRespDTO> batchCreateShortLink(@RequestBody ShortLinkBatchCreateReqDTO requestParam) {
        return Results.success(shortLinkService.batchCreateShortLink(requestParam));
    }


}
