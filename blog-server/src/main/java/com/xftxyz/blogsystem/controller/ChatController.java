package com.xftxyz.blogsystem.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.xftxyz.blogsystem.controller.utils.R;
import com.xftxyz.blogsystem.controller.utils.Util;
import com.xftxyz.blogsystem.jb.Communicate;
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
    public R<Object> getAllMsg(@RequestParam Integer uid1, @RequestParam Integer uid2) {
        return R.ok(communicateService.getAllMsg(uid1, uid2));
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
    public R<Object> addNewMsg(@RequestParam Integer uid1, @RequestParam Integer uid2, @RequestParam String text) {
        String s = Util.maskSensitiveWords(text);
        communicateService.addMsg(uid1, uid2, s, uid1 < uid2 ? 0 : 1, 0, null);
        return R.ok();
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
    public R<Object> addNewImage(@RequestParam Integer uid1, @RequestParam Integer uid2,
            @RequestParam(value = "file") MultipartFile multipartFile) throws IOException {
        byte[] img = multipartFile.getBytes();
        communicateService.addMsg(uid1, uid2, multipartFile.getOriginalFilename(), uid1 < uid2 ? 0 : 1, 1, img);
        return R.ok();
    }

}