package com.project.interview.game;


import com.project.interview.common.BaseResponse;
import com.project.interview.common.ErrorCode;
import com.project.interview.common.ResultUtils;
import com.project.interview.exception.ThrowUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping("/chat")
@RestController
public class ChatController {

    @Resource
    private ChatService chatService;

    @PostMapping
    public BaseResponse<String> doChat(@RequestBody ChatRequest chatRequest) {
        ThrowUtils.throwIf(chatRequest == null, ErrorCode.PARAMS_ERROR);
        String answer = chatService.doChat(chatRequest);
        return ResultUtils.success(answer);
    }

    @GetMapping("/rooms")
    public BaseResponse<List<ChatRoom>> getChatRoomList() {
        return ResultUtils.success(chatService.getChatRoomList());
    }
}
