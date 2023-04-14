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
import com.xftxyz.blogsystem.controller.utils.Util;
import com.xftxyz.blogsystem.jb.Communicate;
import com.xftxyz.blogsystem.jb.Message;
import com.xftxyz.blogsystem.service.CommunicateService;

/**
 * 私聊界面接口
 */
@RestController
public class ChatController {
    @Autowired
    CommunicateService communicateService;

    QueryWrapper<Communicate> wrapper;
    UpdateWrapper<Communicate> updateWrapper;

    /**
     * 获取两个用户之前的消息记录(uid1 < uid2)
     * 
     * @param uid1
     * @param uid2
     * @return
     */
    @GetMapping("/getAllMsg")
    public R<Object> getAllMsg(@RequestParam int uid1, @RequestParam int uid2) {
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

    /**
     * 插入新消息(uid1 < uid2)
     * 
     * @param uid1
     * @param uid2
     * @param text
     * @return
     */
    @PostMapping("/addNewMsg")
    public boolean addNewMsg(@RequestParam int uid1, @RequestParam int uid2, @RequestParam String text) {
        String s = Util.maskSensitiveWords(text);
        return addMsg(uid1, uid2, s, uid1 < uid2 ? 0 : 1, 0, null);
    }

    /**
     * 添加用户图片消息
     * 
     * @param uid1
     * @param uid2
     * @param multipartFile
     * @return
     * @throws IOException
     */
    @PostMapping("/addNewImage")
    public boolean addNewImage(@RequestParam int uid1, @RequestParam int uid2,
            @RequestParam(value = "file") MultipartFile multipartFile) throws IOException {
        byte[] img = multipartFile.getBytes();

        return addMsg(uid1, uid2, multipartFile.getOriginalFilename(), uid1 < uid2 ? 0 : 1, 1, img);
        // return text.getBytes(ISO_8859_1);
    }

    private boolean addMsg(int uid1, int uid2, String text, int sender, int type, byte[] file) {
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
                // ？
                // cmu = new Communicate(mapper.writeValueAsString(msg), uid1 > uid2 ? uid2 :
                // uid1, uid1 > uid2 ? uid1 : uid2);
                if (communicateService.save(cmu))
                    success = true;
            }

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return success;
    }

}