package pdp.uz.humo_online_jobs.job.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pdp.uz.humo_online_jobs.custom_responses.ApiResponse;
import pdp.uz.humo_online_jobs.chat.ChatService;
import pdp.uz.humo_online_jobs.job.dto.ChatDto;

@RestController
@RequestMapping("/api/chats")
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping("/start")
    public ResponseEntity<ApiResponse> startChat(@RequestBody ChatDto chatDto) {
        ApiResponse response = chatService.startChat(chatDto);
        return ResponseEntity.status(response.isSuccess() ? 201 : 400).body(response);
    }
}
