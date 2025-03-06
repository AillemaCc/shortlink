package org.swindle.shortlink.project.dto.req;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.swindle.shortlink.project.dao.entity.ShortLinkDO;

/**
 * 短链接分页请求参数
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShortLinkPageReqDTO extends Page<ShortLinkDO> {
    /**
     * 分组标识
     */
    private String gid;

    /**
     * 排序标识
     */
    private String orderTag;

}
