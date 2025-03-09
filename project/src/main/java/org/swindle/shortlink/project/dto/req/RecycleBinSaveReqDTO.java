package org.swindle.shortlink.project.dto.req;

import lombok.Data;

/**
 * 回收站请求实体
 */
@Data
public class RecycleBinSaveReqDTO {

    /**
     * 分组标识
     */
    private String gid;

    /**
     * 全部短链接
     */
    private String fullShortUrl;
}
