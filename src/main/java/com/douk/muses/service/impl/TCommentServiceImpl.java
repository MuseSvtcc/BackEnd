package com.douk.muses.service.impl;

import com.douk.muses.pojo.TComment;
import com.douk.muses.mapper.TCommentMapper;
import com.douk.muses.pojo.or.InvitationComment;
import com.douk.muses.service.TCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 *
 * @author douk
 * @since 2023-06-01
 */
@Service
public class TCommentServiceImpl extends ServiceImpl<TCommentMapper, TComment> implements TCommentService {


    @Override
    public List<InvitationComment> getCommentList(Integer userId, String iId, String number, String size, String znumber, String zsize) {
        List<InvitationComment> list=new ArrayList<>();
        if(userId==null){
            userId=0;
        }
        list=baseMapper.getCommentList(userId,iId,number,size);
        for(InvitationComment i:list){

            i.setIsGood(baseMapper.getIsGood(userId,i.getCommentsId())==null?0:1);
            i.setComments(baseMapper.getCommentSonList(i.getCommentsId(),znumber,zsize));
            i.setCounts(i.getComments().size());
        }

        return list;
    }

    @Override
    public int inComment(TComment comment) {

        return baseMapper.insert(comment);
    }
}
