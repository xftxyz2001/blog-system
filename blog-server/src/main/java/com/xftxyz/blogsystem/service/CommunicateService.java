package com.xftxyz.blogsystem.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xftxyz.blogsystem.jb.Communicate;
import com.xftxyz.blogsystem.jb.Message;

/**
 * @author 25810
 * @description 针对表【t_communicate】的数据库操作Service
 * @createDate 2023-04-11 13:52:37
 */
public interface CommunicateService extends IService<Communicate> {

    List<Message> getAllMsg(Integer uid1, Integer uid2);

    void addMsg(Integer uid1, Integer uid2, String text, int sender, int type, byte[] file);
}
