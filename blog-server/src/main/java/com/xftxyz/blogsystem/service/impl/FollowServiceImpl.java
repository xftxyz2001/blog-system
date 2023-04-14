package com.xftxyz.blogsystem.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xftxyz.blogsystem.jb.Follow;
import com.xftxyz.blogsystem.mapper.FollowMapper;
import com.xftxyz.blogsystem.service.FollowService;

/**
 * @author 25810
 * @description 针对表【t_follow】的数据库操作Service实现
 * @createDate 2023-04-11 13:52:37
 */
@Service
public class FollowServiceImpl extends ServiceImpl<FollowMapper, Follow>
        implements FollowService {

    @Autowired
    FollowMapper followMapper;

    @Override
    public List<Follow> getFollow(Integer uid) {
        Wrapper<Follow> wrapper = new QueryWrapper<Follow>().eq("uid_self", uid);
        return followMapper.selectList(wrapper);
    }

    @Override
    public List<Follow> getFans(Integer uid) {
        Wrapper<Follow> wrapper = new QueryWrapper<Follow>().eq("uid_follow", uid);
        return followMapper.selectList(wrapper);
    }

    @Override
    public boolean cancelFollow(Integer uid, Integer fid) {
        Wrapper<Follow> wrapper = new QueryWrapper<Follow>().eq("uid_self", uid)
                .eq("uid_follow", fid);
        return followMapper.delete(wrapper) > 0;
    }

}
