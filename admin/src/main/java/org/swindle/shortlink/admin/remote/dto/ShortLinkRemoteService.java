package org.swindle.shortlink.admin.remote.dto;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.swindle.shortlink.admin.common.convention.result.Result;
import org.swindle.shortlink.admin.remote.dto.req.ShortLinkCreateReqDTO;
import org.swindle.shortlink.admin.remote.dto.req.ShortLinkPageReqDTO;
import org.swindle.shortlink.admin.remote.dto.resp.ShortLinkCreateRespDTO;
import org.swindle.shortlink.admin.remote.dto.resp.ShortLinkPageRespDTO;

import java.util.HashMap;
import java.util.Map;

/**
 * 短链接中台远程调用服务
 */
public interface ShortLinkRemoteService {
    /**
     * 创建短链接
     * @param requestParam 创建短链接请求参数
     * @return 短链接创建响应
     */
    default Result<ShortLinkCreateRespDTO> createShortLink(ShortLinkCreateReqDTO requestParam){
        String resultBodyStr = HttpUtil.post("http://127.0.0.1:8001/api/shortlink/v1/create", JSON.toJSONString(requestParam));
        return JSON.parseObject(resultBodyStr, new TypeReference<>() {
        });
    }

    /**
     * 分页查询短链接
     * @param requestParam
     * @return
     */
    default Result<IPage<ShortLinkPageRespDTO>> pageShortLink(ShortLinkPageReqDTO requestParam){
        Map<String,Object> reqMap = new HashMap<String,Object>();
        reqMap.put("gid", requestParam.getGid());
        reqMap.put("current", requestParam.getCurrent());
        reqMap.put("size", requestParam.getSize());
        String requestPageStr = HttpUtil.get("http://127.0.0.1:8001/api/shortlink/v1/page", reqMap);
        return JSON.parseObject(requestPageStr, new TypeReference<>() {
        });
    }


}
