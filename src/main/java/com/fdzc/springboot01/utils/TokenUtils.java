package com.fdzc.springboot01.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fdzc.springboot01.entity.Admin;
import com.fdzc.springboot01.service.impl.AdminService;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Component
public class TokenUtils {

    private static AdminService staticService;

    @Resource
    private AdminService service;

    @PostConstruct
    public void setService() {
        staticService = service;
    }

    /**
     * 生成token
     *
     * @return
     */
    public static String genToken(String userId, String sign) {
        return JWT.create().withAudience(userId) // 将 user id 保存到 token 里面,作为载荷
                .sign(Algorithm.HMAC256(sign)); // 以 password 作为 token 的密钥
    }

    /**
     * 获取当前登录用户的信息。
     *
     * @return 当前登录的管理员（Admin）对象，如果未登录或获取失败则返回 null。
     */
    public static Admin getCurrentUser() {
        try {
            // 从当前请求中获取 HttpServletRequest 对象
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

            // 从请求头中获取名为 "token" 的认证令牌
            String token = request.getHeader("token");

            // 如果令牌不为空，则解析令牌中的用户ID
            if (token != null) {
                String userId = JWT.decode(token).getAudience().get(0);

                // 根据用户ID查找对应的管理员对象
                return staticService.findOneAdmin(Integer.valueOf(userId));
            }
        } catch (Exception e) {
            // 捕获异常，打印错误日志并返回 null
            return null;
        }

        // 如果没有有效的令牌或发生其他异常，则返回 null
        return null;
    }

}
