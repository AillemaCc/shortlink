package org.swindle.shortlink.project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.swindle.shortlink.project.common.convention.result.Result;
import org.swindle.shortlink.project.common.convention.result.Results;
import org.swindle.shortlink.project.dto.req.RecycleBinSaveReqDTO;
import org.swindle.shortlink.project.service.RecycleBinService;

@RestController
@RequiredArgsConstructor
/**
 * 回收站控制层
 */
public class RecycleBinController {
    private final RecycleBinService recycleBinService;
    /**
     * 保存回收站
     */
    @PostMapping("/api/shortlink/v1/recycle-bin/save")
    public Result<Void> saveRecycleBin(@RequestBody RecycleBinSaveReqDTO requestParam) {
        recycleBinService.saveRecycleBin(requestParam);
        return Results.success();
    }

}
