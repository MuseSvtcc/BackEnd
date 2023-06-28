package com.douk.muses.controller;

import com.douk.muses.pojo.TAttention;
import com.douk.muses.service.TAttentionService;
import com.douk.utils.JWT.JWTUtils;
import com.douk.utils.result.Result;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


/**
 * 用户
 *
 * @author douk
 * @since 2023-06-02
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private TAttentionService attentionService;
    /**
     * 首页关注
     *需要参数uid
     * @param request
     * @param map
     * @return
     */
    @PutMapping("/attention")
    public Result attention(HttpServletRequest request, @RequestBody Map<String,String> map) {
        String authorization = request.getHeader("Authorization");
        Map<String, Object> maps = JWTUtils.checkToken(authorization);
        String uId = String.valueOf(maps.get("userId"));
        String attentionId = map.get("uid");

        TAttention tAttention = new TAttention();
        tAttention.setUId(Integer.valueOf(uId));
        tAttention.setAttentionId(Integer.valueOf(attentionId));

        int i = attentionService.setOne(tAttention);
        if (i == 0) {
            return Result.fail();
        } else {
            return Result.ok();
        }
    }
}
