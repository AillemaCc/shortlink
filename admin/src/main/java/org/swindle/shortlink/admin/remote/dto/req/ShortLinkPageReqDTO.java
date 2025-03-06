package org.swindle.shortlink.admin.remote.dto.req;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 短链接分页请求参数
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShortLinkPageReqDTO extends Page {
    /**
     * 分组标识
     */
    private String gid;

    /**
     * 排序标识
     */
    private String orderTag;

}
