package com.douk.muses.service;

import com.douk.muses.pojo.TUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.douk.utils.result.Result;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author douk
 * @since 2023-06-04
 */
public interface TUserService extends IService<TUser> {

    int selectPhoneExist(String phone);

    TUser getUserByLogin(String userName, String password);

    Result setUser(TUser user);

    /**
     * 判断邮箱是否存在
     * @param email
     * @return
     */
    int getEmailJudge(String email);

    TUser getEmail(String email);

    int setPassWordByEmail(String email, String passWord);

    int setAttentionCountAdd(Integer uId);
}
