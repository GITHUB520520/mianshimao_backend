package com.project.interview.game;


import lombok.Data;

import java.io.Serializable;

@Data
public class ChatRequest implements Serializable {

    private Long roomId;

    private String message;

    private static final long serialVersionUID = 1L;
}
