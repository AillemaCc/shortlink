package org.swindle.shortlink.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.swindle.shortlink.project.dto.req.ShortLinkGroupStatsAccessRecordReqDTO;
import org.swindle.shortlink.project.dto.req.ShortLinkGroupStatsReqDTO;
import org.swindle.shortlink.project.dto.req.ShortLinkStatsAccessRecordReqDTO;
import org.swindle.shortlink.project.dto.req.ShortLinkStatsReqDTO;
import org.swindle.shortlink.project.dto.resp.ShortLinkStatsAccessRecordRespDTO;
import org.swindle.shortlink.project.dto.resp.ShortLinkStatsRespDTO;

/**
 * 短链接监控接口层
 */
public interface ShortLinkStatsService {

    /**
     * 获取单个短链接监控数据
     *
     * @param requestParam 获取短链接监控数据入参
     * @return 短链接监控数据
     */
    ShortLinkStatsRespDTO oneShortLinkStats(ShortLinkStatsReqDTO requestParam);

    /**
     * 访问单个短链接指定时间内访问记录监控数据
     * @param requestParam 获取短链接监控访问记录数据入参
     * @return
     */
    IPage<ShortLinkStatsAccessRecordRespDTO> shortLinkStatsAccessRecord(ShortLinkStatsAccessRecordReqDTO requestParam);



    /**
     * 获取分组短链接监控数据
     *
     * @param requestParam 获取分组短链接监控数据入参
     * @return 分组短链接监控数据
     */
    ShortLinkStatsRespDTO groupShortLinkStats(ShortLinkGroupStatsReqDTO requestParam);

    /**
     * 访问分组短链接指定时间内访问记录监控数据
     *
     * @param requestParam 获取分组短链接监控访问记录数据入参
     * @return 分组访问记录监控数据
     */
    IPage<ShortLinkStatsAccessRecordRespDTO> groupShortLinkStatsAccessRecord(ShortLinkGroupStatsAccessRecordReqDTO requestParam);
}