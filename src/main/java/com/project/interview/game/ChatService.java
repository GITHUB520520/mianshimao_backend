package com.project.interview.game;

import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface ChatService{

    /**
     * 和 AI 对话
     *
     * @return AI 的结果
     */
    String doChat(ChatRequest chatRequest);

    /**
     * 获取对话列表
     *
     * @return 聊天室列表
     */
    List<ChatRoom> getChatRoomList();
}
