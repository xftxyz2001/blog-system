package com.xftxyz.blogsystem.jb;

import lombok.Data;

@Data
public class Message {
    private String content;
    private int sender;
    private int type;
    private byte[] file;

    public Message(String content, int sender, int type, byte[] file) {
        this.content = content;
        this.sender = sender;
        this.type = type;
        this.file = file;
    }

    public Message(String content, int sender, int type) {
        this(content, sender, type, null);
    }

    public Message() {
        this(null, 0, 0, null);
    }

}
