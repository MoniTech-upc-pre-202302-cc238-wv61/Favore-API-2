package com.monitech.restapi.service;

import com.monitech.restapi.model.Chat;
import java.util.List;

public interface ChatService {

    Chat createChat(Chat chat);

    Chat getChatById(Long chat_id);

    List<Chat> getAllChats();

    Chat updateChat(Long chat_id, Chat chat);

    Chat deleteChat(Long chat_id);
}
