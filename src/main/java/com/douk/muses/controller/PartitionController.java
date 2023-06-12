package com.douk.muses.controller;

import com.douk.muses.pojo.or.UserInvitation;
import com.douk.muses.service.TInvitationService;
import com.douk.utils.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 分区 前端控制器
 * </p>
 *
 * @author douk
 * @since 2023-06-01
 */
@RestController
@RequestMapping("/zone")
public class PartitionController {
    private final int SIZE=10;
    @Autowired
    private TInvitationService invitationService;
    /**
     * 首页推荐
     * 未登录最新的15条
     * 登录后按爱好随机
     * @param
     * @return
     */
    @GetMapping("/recommend")
    public Result recommend(){
        Map<String, List> maps=new HashMap<>();
        List<UserInvitation> newest=invitationService.getUserInvitation(0,SIZE);
        List<UserInvitation> hot=invitationService.getHotUserInvitation(0,SIZE);
        maps.put("newest",newest);
        maps.put("hot",hot);
        return Result.ok(maps);
    }
}
