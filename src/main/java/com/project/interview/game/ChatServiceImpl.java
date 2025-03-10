package com.project.interview.game;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.project.interview.common.ErrorCode;
import com.project.interview.exception.BusinessException;
import com.project.interview.exception.ThrowUtils;
import com.project.interview.manager.ArkManager;
import com.volcengine.ark.runtime.model.completion.chat.ChatMessage;
import com.volcengine.ark.runtime.model.completion.chat.ChatMessageRole;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ChatServiceImpl implements ChatService {

    @Resource
    private ArkManager arkManager;

    public final Map<Long, List<ChatMessage>> globalMap = new HashMap<>();

    /**
     * 与 AI 在规定的房间进行对话
     *
     * @return
     */
    @Override
    public String doChat(ChatRequest chatRequest) {
        Long roomId = chatRequest.getRoomId();
        String message = chatRequest.getMessage();
        ThrowUtils.throwIf(ObjectUtil.isEmpty(roomId) || StrUtil.isBlank(message), ErrorCode.PARAMS_ERROR);
        // 系统预设
        final String systemPrompt = "1. 提供一道海龟汤谜题的“汤面”（故事表面描述）。  \n" +
                "2. 根据玩家的提问，仅回答“是”、“否”或“与此无关”。  \n" +
                "3. 在特定情况下结束游戏并揭示“汤底”（故事真相）。\n" +
                "游戏流程  \n" +
                "1. 当玩家输入“开始”时，你需立即提供一道海龟汤谜题的“汤面”。  \n" +
                "2. 玩家会依次提问，你只能回答以下三种之一：  \n" +
                "  ○ 是：玩家的猜测与真相相符。  \n" +
                "  ○ 否：玩家的猜测与真相不符。  \n" +
                "  ○ 与此无关：玩家的猜测与真相无直接关联。\n" +
                "3. 在以下情况下，你需要主动结束游戏并揭示“汤底”：  \n" +
                "  ○ 玩家明确表示“不想玩了”、“想要答案”或类似表达。  \n" +
                "  ○ 玩家几乎已经还原故事真相，或所有关键问题都已询问完毕。  \n" +
                "  ○ 玩家输入“退出”。  \n" +
                "  ○ 玩家连续提问 10 次仍未触及关键信息，或表现出完全无头绪的状态。\n" +
                "注意事项  \n" +
                "1. 汤面设计：谜题应简短、有趣且逻辑严密，答案需出人意料但合理。  \n" +
                "2. 回答限制：严格遵守“是”、“否”或“与此无关”的回答规则，不得提供额外提示。  \n" +
                "3. 结束时机：在符合结束条件时，及时揭示“汤底”，避免玩家陷入无效推理。\n" +
                "4. 当你决定结束时，必须在结束的消息中包含【游戏已结束】\n" +
                "示例 \n" +
                "● 玩家输入：“开始”  \n" +
                "● AI 回复（汤面）：\n" +
                "“一个人走进餐厅，点了一碗海龟汤，喝了一口后突然冲出餐厅自杀了。为什么？”  \n" +
                "● 玩家提问：“他是因为汤太难喝了吗？”  \n" +
                "● AI 回复：“否。”  \n" +
                "● 玩家提问：“他认识餐厅里的人吗？”  \n" +
                "● AI 回复：“与此无关。”  \n" +
                "● 玩家输入：“退出。”  \n" +
                "● AI 回复（汤底）：\n" +
                "“这个人曾和同伴在海上遇难，同伴死后，他靠吃同伴的尸体活了下来。餐厅的海龟汤让他意识到自己吃的其实是人肉，因此崩溃自杀。”";
        // 1. 准备消息列表（关联历史上下文）
        final ChatMessage systemMessage = ChatMessage.builder().role(ChatMessageRole.SYSTEM).content(systemPrompt).build();
        final ChatMessage userMessage = ChatMessage.builder().role(ChatMessageRole.USER).content(message).build();

        List<ChatMessage> messages = new ArrayList<>();
        // 首次开始时，需要初始化消息列表，并且额外添加系统消息到记录中
        if (!message.equals("开始") && globalMap.isEmpty()) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "请先开始游戏");
        }
        if (message.equals("开始") && !globalMap.containsKey(roomId)) {
            globalMap.put(roomId, messages);
            messages.add(systemMessage);
        } else {
            // 之后不用重复初始化，而是读取过去的消息列表
            messages = globalMap.get(roomId);
        }
        messages.add(userMessage);

        // 2. 调用 AI
        String answer = arkManager.doChat(messages);

        final ChatMessage assistantMessage = ChatMessage.builder().role(ChatMessageRole.ASSISTANT).content(answer).build();
        messages.add(assistantMessage);

        // 3. 返回消息
        if (answer.contains("【游戏已结束】")) {
            globalMap.remove(roomId);
        }
        globalMap.put(roomId, messages);
        return answer;
    }

    /**
     * 获取房间列表
     *
     * @return
     */
    @Override
    public List<ChatRoom> getChatRoomList() {
        List<ChatRoom> chatRoomList = new ArrayList<>();
        for (long id : globalMap.keySet()) {
            ChatRoom chatRoom = new ChatRoom();
            chatRoom.setRoomId(id);
            chatRoom.setChatMessageList(globalMap.get(id));
            chatRoomList.add(chatRoom);
        }
        return chatRoomList;
    }
}
