package org.swindle.shortlink.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.swindle.shortlink.admin.common.convention.result.Result;
import org.swindle.shortlink.admin.common.convention.result.Results;
import org.swindle.shortlink.admin.remote.ShortLinkRemoteService;
import org.swindle.shortlink.admin.remote.dto.req.ShortLinkGroupStatsReqDTO;
import org.swindle.shortlink.admin.remote.dto.req.ShortLinkStatsAccessRecordReqDTO;
import org.swindle.shortlink.admin.remote.dto.req.ShortLinkStatsReqDTO;
import org.swindle.shortlink.admin.remote.dto.resp.ShortLinkStatsAccessRecordRespDTO;
import org.swindle.shortlink.admin.remote.dto.resp.ShortLinkStatsRespDTO;

/**
 * 短链接监控控制层
 */
@RestController
@RequiredArgsConstructor
public class ShortLinkStatsController {

    /**
     * 后续重构为 SpringCloud Feign 调用
     */
    ShortLinkRemoteService shortLinkRemoteService = new ShortLinkRemoteService() {
    };

    /**
     * 访问单个短链接指定时间内监控数据
     */
    @GetMapping("/api/shortlink/admin/v1/stats")
    public Result<ShortLinkStatsRespDTO> shortLinkStats(ShortLinkStatsReqDTO requestParam) {
        return shortLinkRemoteService.oneShortLinkStats(requestParam);
    }

    /**
     * 访问单个短链接指定时间内访问记录监控数据
     */
    @GetMapping("/api/shortlink/admin/v1/stats/access-record")
    public Result<IPage<ShortLinkStatsAccessRecordRespDTO>> shortLinkStatsAccessRecord(@RequestBody ShortLinkStatsAccessRecordReqDTO requestParam) {
        return shortLinkRemoteService.shortLinkStatsAccessRecord(requestParam);
    }

    /**
     * 访问分组短链接指定时间内监控数据
     */
    @GetMapping("/api/shortlink/admin/v1/stats/group")
    public Result<ShortLinkStatsRespDTO> groupShortLinkStats(ShortLinkGroupStatsReqDTO requestParam) {
        return shortLinkRemoteService.groupShortLinkStats(requestParam);
    }



}