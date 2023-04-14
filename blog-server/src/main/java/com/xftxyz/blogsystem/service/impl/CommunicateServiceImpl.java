package com.xftxyz.blogsystem.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xftxyz.blogsystem.controller.utils.BlogException;
import com.xftxyz.blogsystem.jb.Communicate;
import com.xftxyz.blogsystem.jb.Message;
import com.xftxyz.blogsystem.mapper.CommunicateMapper;
import com.xftxyz.blogsystem.service.CommunicateService;

/**
 * @author 25810
 * @description 针对表【t_communicate】的数据库操作Service实现
 * @createDate 2023-04-11 13:52:37
 */
@Service
public class CommunicateServiceImpl extends ServiceImpl<CommunicateMapper, Communicate>
        implements CommunicateService {

    @Autowired
    ObjectMapper mapper;

    @Override
    public List<Message> getAllMsg(Integer uid1, Integer uid2) {
        Wrapper<Communicate> wrapper = new QueryWrapper<Communicate>()
                .eq("uid1", uid1)
                .eq("uid2", uid2);
        Communicate communicate = this.getOne(wrapper);
        String oriContent = communicate.getContent();
        List<Message> msg = new ArrayList<Message>();
        if (oriContent != null) {
            try {
                msg = mapper.readValue(oriContent, new TypeReference<List<Message>>() {
                });
            } catch (JsonProcessingException e) {
                throw new BlogException("JsonProcessingException");
            }
        }
        return msg;

    }

    @Override
    public void addMsg(Integer uid1, Integer uid2, String text, int sender, int type, byte[] file) {
        // 调整uid1和uid2的大小，使得uid1<uid2
        if (uid1 > uid2) {
            int temp = uid1;
            uid1 = uid2;
            uid2 = temp;
        }
        Wrapper<Communicate> wrapper = new QueryWrapper<Communicate>()
                .eq("uid1", uid1)
                .eq("uid2", uid2);
        Communicate cmu = this.getOne(wrapper);
        try {
            if (cmu != null) {
                // 获取原始内容
                String oriContent = cmu.getContent();
                List<Message> msg = mapper.readValue(oriContent, new TypeReference<List<Message>>() {
                });
                msg.add(new Message(text, sender, type, file));
                String newContent = mapper.writeValueAsString(msg);
                cmu.setContent(newContent);
            } else {
                List<Message> msg = new ArrayList<Message>();
                msg.add(new Message(text, sender, type, file));
                cmu = new Communicate(uid1, uid2, mapper.writeValueAsString(msg));
            }
        } catch (JsonProcessingException e) {
            throw new BlogException("JsonProcessingException");
        }
        this.saveOrUpdate(cmu);

    }

}
