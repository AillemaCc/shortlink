package org.swindle.shortlink.admin.remote;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.swindle.shortlink.admin.common.convention.result.Result;
import org.swindle.shortlink.admin.dto.req.ShortLinkUpdateReqDTO;
import org.swindle.shortlink.admin.remote.dto.req.*;
import org.swindle.shortlink.admin.remote.dto.resp.ShortLinkCreateRespDTO;
import org.swindle.shortlink.admin.remote.dto.resp.ShortLinkGroupCountQueryRespDTO;
import org.swindle.shortlink.admin.remote.dto.resp.ShortLinkPageRespDTO;

import java.util.HashMap;
import java.util.List;
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

    /**
     * 查询短链接分组内数量
     * @param requestParam
     * @return
     */
    default Result<List<ShortLinkGroupCountQueryRespDTO>> listGroupShortLinkCount(List<String> requestParam){
        Map<String,Object> reqMap = new HashMap<>();
        reqMap.put("requestParam", requestParam);
        String requestPageStr = HttpUtil.get("http://127.0.0.1:8001/api/shortlink/v1/count", reqMap);
        return JSON.parseObject(requestPageStr, new TypeReference<>() {
        });
    }

    /**
     * 修改短链接信息
     * @param requestParam 修改短链接信息请求参数
     */
    default void updateShortLink(ShortLinkUpdateReqDTO requestParam){
        HttpUtil.post("http://127.0.0.1:8001/api/shortlink/v1/update", JSON.toJSONString(requestParam));
    };
    /**
     * 根据 URL 获取标题
     *
     * @param url 目标网站地址
     * @return 网站标题
     */
    default Result<String> getTitleByUrl(@RequestParam("url") String url){
        String responseStr = HttpUtil.get("http://127.0.0.1:8001/api/shortlink/v1/title?url=" + url);
        return JSON.parseObject(responseStr, new TypeReference<>() {
        });
    }
    /**
     * 保存回收站
     *
     * @param requestParam 请求参数
     */
    default void saveRecycleBin(RecycleBinSaveReqDTO requestParam){
        HttpUtil.post("http://127.0.0.1:8001/api/shortlink/v1/recycle-bin/save", JSON.toJSONString(requestParam));
    };

    /**
     * 分页查询回收站短链接
     * @param requestParam
     * @return
     */
    default Result<IPage<ShortLinkPageRespDTO>> pageRecycleBinShortLink(ShortLinkRecycleBinPageReqDTO requestParam){
        Map<String,Object> reqMap = new HashMap<String,Object>();
        reqMap.put("gidList", requestParam.getGidList());
        reqMap.put("current", requestParam.getCurrent());
        reqMap.put("size", requestParam.getSize());
        String requestPageStr = HttpUtil.get("http://127.0.0.1:8001/api/shortlink/recycle-bin/page", reqMap);
        return JSON.parseObject(requestPageStr, new TypeReference<>() {
        });
    }

    /**
     * 从回收站当中恢复短链接
     */
    default void recoverRecycleBin(RecycleBinRecoverReqDTO requestParam){
        HttpUtil.post("http://127.0.0.1:8001/api/shortlink/v1/recycle-bin/recover", JSON.toJSONString(requestParam));
    }

}
