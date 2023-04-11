package com.xftxyz.blogsystem.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xftxyz.blogsystem.controller.utils.R;
import com.xftxyz.blogsystem.jb.Communicate;
import com.xftxyz.blogsystem.service.CommunicateService;

import lombok.Data;

@RestController
// @Api(value = "私聊界面接口", tags = "私聊界面接口")
public class ChatController {
    @Autowired
    CommunicateService communicateService;

    QueryWrapper<Communicate> wrapper;
    UpdateWrapper<Communicate> updateWrapper;

    // @ApiOperation(value = "获取历史记录", notes = "获取两个用户之前的消息记录(uid1 < uid2)")
    @GetMapping("/getAllMsg")
    public R getAllMsg(@RequestParam int uid1, @RequestParam int uid2) {
        wrapper = Wrappers.query();
        wrapper.eq("uid1", uid1).eq("uid2", uid2);
        Communicate cmu = communicateService.getOne(wrapper);
        String oriContent = cmu.getContent();
        ObjectMapper mapper = new ObjectMapper();
        List<Message> msg = new ArrayList<Message>();
        if (oriContent != null) {
            try {
                msg = mapper.readValue(oriContent, new TypeReference<List<Message>>() {
                });
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        return R.ok(msg);

    }

    // @ApiOperation(value = "插入新消息", notes = "插入新消息(uid1 < uid2)")
    @PostMapping("/addNewMsg")
    public boolean addNewMsg(@RequestParam int uid1, @RequestParam int uid2, @RequestParam String text) {
        String s = hideRude(text);
        return addMsg(uid1, uid2, s, uid1 < uid2 ? 0 : 1, 0, null);
    }

    // @ApiOperation(value = "图片消息接口", notes = "添加用户图片消息")
    @PostMapping("/addNewImage")
    public boolean addNewImage(@RequestParam int uid1, @RequestParam int uid2,
            @RequestParam(value = "file") MultipartFile multipartFile) throws IOException {
        byte[] img = multipartFile.getBytes();

        return addMsg(uid1, uid2, multipartFile.getOriginalFilename(), uid1 < uid2 ? 0 : 1, 1, img);
        // return text.getBytes(ISO_8859_1);
    }

    public boolean addMsg(int uid1, int uid2, String text, int sender, int type, byte[] file) {
        wrapper = Wrappers.query();
        wrapper.eq("uid1", uid1 > uid2 ? uid2 : uid1).eq("uid2", uid1 > uid2 ? uid1 : uid2);
        Communicate cmu = communicateService.getOne(wrapper);
        String oriContent = null;
        if (cmu != null)
            oriContent = cmu.getContent();
        ObjectMapper mapper = new ObjectMapper();
        boolean success = false;
        try {
            List<Message> msg = new ArrayList<Message>();
            if (cmu != null) {
                msg = mapper.readValue(oriContent, new TypeReference<List<Message>>() {
                });
                msg.add(new Message(text, sender, type, file));
                String newContent = mapper.writeValueAsString(msg);
                updateWrapper = new UpdateWrapper<>();
                updateWrapper.eq("uid1", uid1 > uid2 ? uid2 : uid1).eq("uid2", uid1 > uid2 ? uid1 : uid2);
                cmu.setContent(newContent);
                if (communicateService.update(cmu, updateWrapper))
                    success = true;
            } else {
                msg.add(new Message(text, sender, type, file));
                // TODO: 这里在干什么？
                // cmu = new Communicate(mapper.writeValueAsString(msg), uid1 > uid2 ? uid2 : uid1,
                //         uid1 > uid2 ? uid1 : uid2);
                if (communicateService.save(cmu))
                    success = true;
            }

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return success;
    }

    public String hideRude(String text) {
        String temp = text;
        String[] illegal = { "你妈", "傻逼", "煞笔", "沙比", "骚逼", "烧杯", "尼玛", "sb", "Sb", "SB", "sB", "tmd", "TMD" };
        for (String s : illegal) {
            temp = temp.replace(s, "***");
        }
        return temp;
    }
}

@Data
class Message {
    String content;
    int sender;
    int type;
    byte[] file;

    Message() {
        content = null;
        sender = 0;
        type = 0;
        file = null;
    }

    public Message(String ct, int sd, int ty) {
        content = ct;
        sender = sd;
        type = ty;
        file = null;
    }

    public Message(String ct, int sd, int ty, byte[] bt) {
        content = ct;
        sender = sd;
        type = ty;
        file = bt;
    }
}