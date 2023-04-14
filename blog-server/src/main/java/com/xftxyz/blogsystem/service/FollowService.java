package com.xftxyz.blogsystem.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xftxyz.blogsystem.jb.Follow;

/**
 * @author 25810
 * @description 针对表【t_follow】的数据库操作Service
 * @createDate 2023-04-11 13:52:37
 */
public interface FollowService extends IService<Follow> {

    List<Follow> getFollow(Integer uid);

    List<Follow> getFans(Integer uid);

    boolean cancelFollow(Integer uid, Integer fid);

}
