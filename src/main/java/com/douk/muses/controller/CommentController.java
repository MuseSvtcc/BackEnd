package com.douk.muses.controller;

import com.douk.muses.pojo.TComment;
import com.douk.muses.pojo.or.InvitationComment;
import com.douk.muses.service.TCommentService;
import com.douk.utils.JWT.JWTUtils;
import com.douk.utils.result.Result;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 评论
 */
@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private TCommentService commentService;
    /**
     *评论区
     * @param map
     * @return
     */
    @PostMapping("/list")
    public Result comment(HttpServletRequest request, @RequestBody Map<String,String> map){
        String iId=map.get("iId");
        String fnumber = map.get("fnumber");
        String fsize = map.get("fsize");
        String znumber = map.get("znumber");
        String zsize = map.get("zsize");
        String token = request.getHeader("Authorization");
        List<InvitationComment> list=new ArrayList<>();
        if(token==null||token.equals("")) {
            list = commentService.getCommentList(null,iId,fnumber, fsize,znumber,zsize);

        }else {
            Map<String, Object> map1 = JWTUtils.checkToken(token);
            Integer userId = (Integer) map1.get("userId");
            list = commentService.getCommentList(userId,iId,fnumber, fsize,znumber,zsize);
        }
        return Result.ok(list);
    }

    /**
     * 发评论
     * fatherId为父id，没有则为0
     * @param request
     * @param comment
     * @return
     */
    @PostMapping("/init")
    public Result inComment(HttpServletRequest request, @RequestBody TComment comment){

        String authorization = request.getHeader("Authorization");
        Map<String, Object> maps = JWTUtils.checkToken(authorization);
        String uId = String.valueOf(maps.get("userId"));
        comment.setUId(Integer.valueOf(uId));
        int i=commentService.initComment(comment);
        return Result.ok();
    }

}
