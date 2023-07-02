package com.douk.muses.controller;

import com.douk.muses.pojo.TAttention;
import com.douk.muses.pojo.TInvitation;
import com.douk.muses.pojo.TPartitions;
import com.douk.muses.pojo.or.UserInvitation;
import com.douk.muses.service.TAttentionService;
import com.douk.muses.service.TInvitationService;
import com.douk.muses.service.TPartitionsService;
import com.douk.utils.JWT.JWTUtils;
import com.douk.utils.result.Result;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 帖子
 */
@RestController
@RequestMapping("/post")
public class PostController {

    private final int SIZE=10;
    @Autowired
    private TAttentionService attentionService;
    @Autowired
    private TInvitationService invitationService;
    @Autowired
    private TPartitionsService partitionsService;

    /**
     * 首页推荐
     * 未登录最新的15条
     * 登录后按爱好随机
     * @param
     * @return
     */
    @GetMapping("/recommend")
    public Result recommend(){
        Map<String,List> maps=new HashMap<>();
        List<UserInvitation> newest=invitationService.getUserInvitation(0,SIZE);
        List<UserInvitation> hot=invitationService.getHotUserInvitation(0,SIZE);
        maps.put("newest",newest);
        maps.put("hot",hot);
        return Result.ok(maps);
    }

    /**
     * 下拉热门更新
     * @param i
     * @return
     */
    @GetMapping("/get/hot/{i}")
    public Result getHot(@PathVariable int i){
        Map<String,List> maps=new HashMap<>();
        i=i*SIZE;
        List<UserInvitation> hot=invitationService.getHotUserInvitation(i,SIZE);
        maps.put("hot",hot);
        return Result.ok(maps);
    }

    /**
     * 下拉最新更新
     * @param i
     * @return
     */
    @GetMapping("/get/newest/{i}")
    public Result getNewest(@PathVariable int i){
        Map<String,List> maps=new HashMap<>();
        i=i*SIZE;
        List<UserInvitation> newest=invitationService.getUserInvitation(i,SIZE);
        maps.put("newest",newest);
        return Result.ok(maps);
    }
    /**
     * 发帖
     * @param request
     * @param map
     * @return
     */
    @PostMapping("/send/verify")
    public Result Issue(HttpServletRequest request,@RequestBody Map<String,String> map){
        String token = request.getHeader("Authorization");
        Integer userId = (Integer) JWTUtils.checkToken(token).get("userId");
        int i=invitationService.insertByUid(userId,map);
        return Result.ok(i);
    }

    /**
     * 帖子文件
     * @param header
     * @return
     */
    @PostMapping("/send/verify/file")
    public Result IssueFile(@RequestParam("header") MultipartFile header){
        return invitationService.post(header);
    }

    /**
     * 获得分区列表
     * @return
     */

    @GetMapping("/categories")
    public Result Categories(){
//        invitationService.getPartitionsList();
        List<TPartitions> partitionsList=partitionsService.getList();
        return Result.ok(partitionsList);
    }

    /**
     * 根据分区获得帖子
     * @param categorieId
     * @param page
     * @param news
     * @return
     */
    @GetMapping("/categorie/{categorieId}/{page}/{new}")
    public Result Categorie(@PathVariable("categorieId") Integer categorieId,
                            @PathVariable("page") Integer page,
                            @PathVariable("new") String news) {
        List<TInvitation> invitations=invitationService.getPartitionList(categorieId,page,news);

        return Result.ok(invitations);
    }


}
