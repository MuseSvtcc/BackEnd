package com.douk.muses.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.douk.muses.pojo.TInvitation;
import com.douk.muses.mapper.TInvitationMapper;
import com.douk.muses.pojo.or.UserInvitation;
import com.douk.muses.service.TInvitationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.douk.utils.result.Result;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

/**
 * <p>
 * 帖子 服务实现类
 * </p>
 *
 * @author douk
 * @since 2023-06-02
 */
@Service
public class TInvitationServiceImpl extends ServiceImpl<TInvitationMapper, TInvitation> implements TInvitationService {
    Date s=new Date();

    @Override
    public List<TInvitation> getIIT(int i) {
        LambdaQueryWrapper<TInvitation> wrapper=new LambdaQueryWrapper<>();
        wrapper.orderByDesc(TInvitation::getInvitationId);
        wrapper.last("limit "+i);
        return baseMapper.selectList(wrapper);
    }

    @Override
    public List<UserInvitation> getUserInvitation(int x,int y) {
        List<UserInvitation> userInvitationList = baseMapper.getUserInvitationList(x,y);
        return userInvitationList;
    }

    @Override
    public List<UserInvitation> getHotUserInvitation(int x, int y) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, -7);
        s = calendar.getTime();
        s.setHours(0);
        s.setMinutes(0);
        s.setSeconds(0);
        List<UserInvitation> userInvitationList = baseMapper.getHotUserInvitation(s,x,y);
        return userInvitationList;
    }

    @Override
    public Map<String, Object> getContent(Integer userId, Integer invitation) {
        Map<String, Object> content = baseMapper.getContent(userId, invitation);
        if(content==null){
            content = baseMapper.insertUserInvitation(userId,invitation);
        }
        return content;
    }

    @Override
    public Map<String, Object> insertUserInvitation(String i, Integer invitation) {
        return baseMapper.insertUserInvitation(Integer.valueOf(i),invitation);
    }

    @Override
    public int insertByUid(Integer userId, Map<String, String> map) {
        TInvitation invitation=new TInvitation();
        invitation.setUId(userId);
        invitation.setTitle(map.get("title"));//标题
        invitation.setContent(map.get("content"));//内容
        invitation.setContent(map.get("price"));//价格
        invitation.setContent(map.get("partitions"));//分区
        invitation.setContent(map.get("private"));//私有
        invitation.setContent(map.get("tag"));//标签
        invitation.setIOrM(map.get("iorm"));//图片或视频链接

        return baseMapper.insert(invitation);
    }

    @Override
    public Result post(MultipartFile header) {


        return null;
    }

    @Override
    public List<Integer> getPartitionList(Integer categorieId, Integer page, String news) {
        List<Integer> integers=new ArrayList<>();
        LambdaQueryWrapper<TInvitation> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(TInvitation::getPartitionsId,categorieId);
        wrapper.last("Limit "+page);
        if(news.equals("new")){
            wrapper.orderByDesc(TInvitation::getCreatedAt);

        }else if(news.equals("hot")){
            wrapper.orderByDesc(TInvitation::getGood);
        }
        List<TInvitation> invitations = baseMapper.selectList(wrapper);
        for (TInvitation invitation : invitations) {
            integers.add(invitation.getInvitationId());
        }
        return integers;
    }

    @Override
    public TInvitation getInvitation(Integer id) {
        TInvitation invitation = baseMapper.selectById(id);
        return invitation;
    }
}
