package com.douk.muses.controller;

import com.douk.muses.service.TInvitationService;
import com.douk.utils.JWT.JWTUtils;
import com.douk.utils.result.Result;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/issue")
public class IssueController {
    @Autowired
    private TInvitationService invitationService;

    /**
     * 发帖
     * @param request
     * @param map
     * @return
     */
    @PostMapping("/user")
    public Result Issue(//@ 封装上传数据
                        @RequestParam("header") MultipartFile header,
                        HttpServletRequest request,@RequestBody Map<String,String> map){
        String token = request.getHeader("Authorization");
        Integer userId = (Integer) JWTUtils.checkToken(token).get("userId");
        int i=invitationService.insertByUid(userId,map,header);

        return Result.ok(i);
    }

}
