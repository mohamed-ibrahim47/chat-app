package com.example.chat.config;

import com.example.chat.payload.Message;
import com.example.chat.payload.MessageType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.time.Instant;

@Component
@Slf4j
@RequiredArgsConstructor
public class WSEventListener {

    final private SimpMessageSendingOperations operations;

    @EventListener
    public void handleWSDisconnectEvent(SessionDisconnectEvent sessionDisconnectEvent){
        StompHeaderAccessor stompHeaderAccessor = StompHeaderAccessor.wrap(sessionDisconnectEvent.getMessage());
        String userName = (String) stompHeaderAccessor.getSessionAttributes().get("username");
        if(StringUtils.hasText(userName)){
            var message = Message.builder()
                    .messageType(MessageType.LEAVE)
                    .created(Instant.now())
                    .senderUser(userName)
                    .build();
            operations.convertAndSend("/topic/public",message);
        }

    }
}
