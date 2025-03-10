package org.swindle.shortlink.project.dto.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * 短链接基础访问监控响应参数
 */
@Data
@Builder
public class ShortLinkStatsAccessDailyRespDTO {

    /**
     * 日期
     */

    private String date;

    /**
     * 访问量
     */
    private Integer pv;

    /**
     * 独立访客数
     */
    private Integer uv;

    /**
     * 独立IP数
     */
    private Integer uip;
}