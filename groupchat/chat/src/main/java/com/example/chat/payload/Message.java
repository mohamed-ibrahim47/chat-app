package com.example.chat.payload;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Message {
    private String content;
    private String senderUser;
    private Instant created;
    private MessageType messageType ;
}
