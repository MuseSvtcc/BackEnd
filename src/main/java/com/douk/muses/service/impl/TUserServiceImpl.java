package com.douk.muses.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.douk.muses.pojo.TUser;
import com.douk.muses.mapper.TUserMapper;
import com.douk.muses.service.TUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.douk.utils.result.Result;
import com.douk.utils.result.ResultCodeEnum;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author douk
 * @since 2023-06-04
 */
@Service
public class TUserServiceImpl extends ServiceImpl<TUserMapper, TUser> implements TUserService {
    //盐
    private static final String slat = "douk!@###";

    @Autowired
    private TUserMapper userMapper;

    @Override
    public int selectPhoneExist(String phone) {
        LambdaQueryWrapper<TUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(TUser::getPhone, phone);
        TUser tUser = baseMapper.selectOne(wrapper);
        return tUser == null ? 0 : 1;
    }


    @Override
    public TUser getUserByLogin(String userName, String password) {
        String pws = DigestUtils.md5Hex(password + slat);

        LambdaQueryWrapper<TUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(TUser::getUserName, userName);
        wrapper.eq(TUser::getPassWord, pws);
        wrapper.select(TUser::getUId,TUser::getUserName,TUser::getNickName,TUser::getPortrait,TUser::getEmail,TUser::getPhone,
                TUser::getDescribeText,TUser::getSex,TUser::getVip);
        wrapper.last("limit 1");

        return baseMapper.selectOne(wrapper);
    }

    @Override
    public Result setUser(TUser user) {
        String userName = user.getUserName();
        LambdaQueryWrapper<TUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(TUser::getUserName,userName);
        TUser user1 = baseMapper.selectOne(wrapper);
        if(user1!=null){
            return Result.custom(202,"账号已存在",null);
        }else {
            String pws = DigestUtils.md5Hex(user.getPassWord() + slat);
            user.setPassWord(pws);
            baseMapper.insert(user);
            return Result.ok();
        }


    }

    /**
     * 判断邮箱是否存在 1存在 0不存在
     * @param email
     * @return
     */
    @Override
    public int getEmailJudge(String email) {
        LambdaQueryWrapper<TUser> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(TUser::getEmail,email);
        wrapper.last("limit 1");

        TUser user = baseMapper.selectOne(wrapper);



        return user==null?0:1;
    }

    @Override
    public TUser getEmail(String email) {
        LambdaQueryWrapper<TUser> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(TUser::getEmail,email);
        wrapper.last("limit 1");
        wrapper.select(TUser::getUId,TUser::getUserName,TUser::getNickName,TUser::getPortrait,TUser::getEmail,TUser::getPhone,
                TUser::getDescribeText,TUser::getSex,TUser::getVip);


        return baseMapper.selectOne(wrapper);
    }

    @Override
    public int setPassWordByEmail(String email, String passWord) {
        LambdaQueryWrapper<TUser> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(TUser::getEmail,email);
        wrapper.last("limit 1");
        TUser user = userMapper.selectOne(wrapper);
        String pws = DigestUtils.md5Hex(passWord + slat);
        user.setPassWord(pws);

        return baseMapper.updateById(user);
    }

    @Override
    public int setAttentionCountAdd(Integer uId) {
        return baseMapper.setAttentionCountAdd(uId);
    }


}
