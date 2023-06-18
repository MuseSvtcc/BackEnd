package com.douk.muses.controller;

import com.douk.muses.pojo.TAttention;
import com.douk.muses.pojo.or.UserInvitation;
import com.douk.muses.service.TAttentionService;
import com.douk.muses.service.TInvitationService;
import com.douk.utils.JWT.JWTUtils;
import com.douk.utils.result.Result;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 首页 前端控制器
 * </p>
 *
 * @author douk
 * @since 2023-06-02
 */
@RestController
@RequestMapping("/home")
public class HomePageController {

    private final int SIZE=10;
    @Autowired
    private TAttentionService attentionService;
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
    @GetMapping("/gethot/{i}")
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
    @GetMapping("/getnewest/{i}")
    public Result getNewest(@PathVariable int i){
        Map<String,List> maps=new HashMap<>();
        i=i*SIZE;
        List<UserInvitation> newest=invitationService.getUserInvitation(i,SIZE);
        maps.put("newest",newest);
        return Result.ok(maps);
    }

    /**
     * 首页关注
     *需要参数uid
     * @param request
     * @param map
     * @return
     */
    @PutMapping("/user/attention")
    public Result attention(HttpServletRequest request,@RequestBody Map<String,String> map) {
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

    /**
     * 帖子内容
     * @param request
     * @param invitation
     * @return
     */
    @GetMapping("/content/{invitation}")
    public Result content(HttpServletRequest request,@PathVariable Integer invitation){
        String token = request.getHeader("Authorization");
        Map <String,Object> map=new HashMap<>();
        if(token==null||token.equals("")){
            map=invitationService.invitationContest(invitation);

            return Result.ok(map);
        }else {
            Map<String, Object> map1 = JWTUtils.checkToken(token);
            Integer userId = (Integer) map1.get("userId");
            System.out.println("账号为："+userId);
            map=invitationService.invitationContestById(userId,invitation);
            return Result.ok(map);
        }
    }

    /**
     * 关注
     * @param request
     * @param id
     * @return
     */
    @GetMapping("/user/{id}")
    public Result attention(HttpServletRequest request,@PathVariable Integer id){
        String token = request.getHeader("Authorization");
        Map<String, Object> map = JWTUtils.checkToken(token);
        Integer userId = (Integer) map.get("userId");
        int i=attentionService.insertAttention(userId,id);
        return Result.ok(i);
    }


}
