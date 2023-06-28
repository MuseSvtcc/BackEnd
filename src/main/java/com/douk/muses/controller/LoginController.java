package com.douk.muses.controller;

import com.alibaba.fastjson.JSON;
import com.douk.muses.pojo.TUser;
import com.douk.muses.service.TUserService;
import com.douk.muses.service.impl.EmailServiceImpl;
import com.douk.utils.JWT.JWTUtils;
import com.douk.utils.result.Result;
import com.douk.utils.result.ResultCodeEnum;
import jakarta.annotation.Resource;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 登录注册
 *
 * @author douk
 * @since 2023-06-01
 */
@RestController
@RequestMapping("/user")
public class LoginController {
    private Map<String,Object> m=new HashMap<>();
    @Autowired
    private TUserService userService;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Autowired
    private EmailServiceImpl emailService;

    /**
     * 账号密码登录
     * @param map
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody Map<String,String> map){
        String userName = map.get("userName");
        String passWord = map.get("passWord");
        TUser user=userService.getUserByLogin(userName,passWord);

        if(user!=null){//登录成功
            String token = JWTUtils.createToken(Long.valueOf(user.getUId()));
            //7天
            redisTemplate.opsForValue().set("TOKEN_"+token, JSON.toJSONString(user),7, TimeUnit.DAYS);
            user.setPassWord(null);
            user.setDeleted(null);
            user.setUpdatedAt(null);
            user.setUId(null);
            user.setHobby(null);
            m.put("user",user);
            m.put("token",token);
            return Result.ok(m);
        }
        return Result.build("登陆失败", ResultCodeEnum.LOGIN_ERROR);
    }

    /**
     * 通过邮箱找回密码
     * @param map
     * @return
     */
    @PutMapping("/email/retrieve")
    public Result retrieve(@RequestBody Map<String,String> map){
        String passWord = map.get("passWord");
        String email = map.get("email");
        String code1 = map.get("code");
        String code2 = redisTemplate.opsForValue().get("retrieve"+email);
        if(code1.equals(code2)){
            int i=userService.setPassWordByEmail(email,passWord);
            return Result.ok(i);
        }else {
            return Result.fail("验证码错误");
        }
    }



    /**
     * 邮箱找回验证码
     * @param email
     * @return
     */
    @GetMapping("/email/retrieve/{email}")
    public Result emailGet(@PathVariable String email){
        int i = userService.getEmailJudge(email);
        if(i==0){
            return Result.fail("邮箱未注册");
        }
        String code=emailService.EmailGet(email);
        redisTemplate.opsForValue().set("retrieve"+email,code,30,TimeUnit.MINUTES);
        return Result.ok();
    }

    /**
     * 邮箱登录
     * @param map
     * @return
     */
    @PostMapping("/email/login")
    public Result emailLogin(@RequestBody Map<String,String> map){
        String email = map.get("email");
        String code1 = map.get("code");
        String code2 = redisTemplate.opsForValue().get("login"+email);
        if(code1.equals(code2)){
            TUser user = userService.getEmail(email);
            System.out.println(user);
            String token = JWTUtils.createToken(Long.valueOf(user.getUId()));
            //7天
            redisTemplate.opsForValue().set("TOKEN_"+token, JSON.toJSONString(user),7, TimeUnit.DAYS);
            user.setPassWord(null);
            user.setDeleted(null);
            user.setUpdatedAt(null);
            user.setUId(null);
            user.setHobby(null);
            m.put("user",user);
            m.put("token",token);
            return Result.ok(m);
        }else {
            return Result.fail("验证码错误");
        }
    }


    /**
     * 邮箱登录验证码
     * @param email
     * @return
     */
    @GetMapping("/email/login/{email}")
    public Result emailLoginCode(@PathVariable String email){
        TUser user=userService.getEmail(email);
        if(user!=null){
            String code=emailService.EmailLogin(email);
            redisTemplate.opsForValue().set("login"+email,code,30,TimeUnit.MINUTES);
            return Result.ok();
        }else {
            return Result.fail("邮箱未注册");
        }
    }

    /**
     * 注册
     * @param map
     * @return
     */
    @PostMapping("/signin")
    public Result sign(@RequestBody Map<String,String> map){
        System.out.println(map);
        String email = map.get("email");
        String code1 = map.get("code");
        String code2 = redisTemplate.opsForValue().get("sign"+email);
        if(!code1.equals(code2)){
            return Result.fail("验证码错误");
        }
        TUser user =new TUser();
        user.setUserName(map.get("userName"));
        user.setPassWord(map.get("passWord"));
        user.setNickName("新用户");
        user.setEmail(email);

        return userService.setUser(user);
    }

    /**
     * 获取邮箱注册验证码
     * @param email
     * @return
     */
    @GetMapping("/signin/{email}")
    public Result getCode(@PathVariable String email){
        int i=userService.getEmailJudge(email);
        if(i==1){
            return Result.fail("邮箱已存在");
        }
        String emailCode = emailService.EmailCode(email);
        redisTemplate.opsForValue().set("sign"+email,emailCode,30,TimeUnit.MINUTES);
        return Result.ok();
    }




}
