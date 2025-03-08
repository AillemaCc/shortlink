package org.swindle.shortlink.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.swindle.shortlink.admin.common.convention.result.Result;
import org.swindle.shortlink.admin.common.convention.result.Results;
import org.swindle.shortlink.admin.remote.ShortLinkRemoteService;


@RestController
@RequiredArgsConstructor
public class UrlTitleController {

    ShortLinkRemoteService shortLinkService = new ShortLinkRemoteService(){};

    /**
     * 根据 URL 获取对应网站的标题
     */
    @GetMapping("/api/shortlink/admin/v1/title")
    public Result<String> getTitleByUrl(@RequestParam("url") String url) {
        return shortLinkService.getTitleByUrl(url);
    }
}
