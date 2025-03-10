package org.swindle.shortlink.admin.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户登录接口返回响应
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginRespDTO {
    private String token;
}
