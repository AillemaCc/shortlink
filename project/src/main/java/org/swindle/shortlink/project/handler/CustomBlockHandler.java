package org.swindle.shortlink.project.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.swindle.shortlink.project.common.convention.result.Result;
import org.swindle.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import org.swindle.shortlink.project.dto.resp.ShortLinkCreateRespDTO;

/**
 * 自定义限流策略
 */

public class CustomBlockHandler {

    public static Result<ShortLinkCreateRespDTO> createShortLinkBlockHandlerMethod(ShortLinkCreateReqDTO requestParam, BlockException exception) {
        return new Result<ShortLinkCreateRespDTO>().setCode("B100000").setMessage("当前访问网站人数过多，请稍后再试...");
    }
}