package org.swindle.shortlink.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.swindle.shortlink.project.dao.entity.ShortLinkDO;

/**
 * url标题接口层
 */
public interface UrlTitleService{
        /**
         * 根据 URL 获取标题
         *
         * @param url 目标网站地址
         * @return 网站标题
         */
        String getTitleByUrl(String url);
}

