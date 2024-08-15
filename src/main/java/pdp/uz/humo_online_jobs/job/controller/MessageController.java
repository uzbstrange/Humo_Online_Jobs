package pdp.uz.humo_online_jobs.job.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pdp.uz.humo_online_jobs.custom_responses.ApiResponse;
import pdp.uz.humo_online_jobs.chat.ChatService;
import pdp.uz.humo_online_jobs.job.dto.MessageDto;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private final ChatService chatService;

    public MessageController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping("/send")
    public ResponseEntity<ApiResponse> sendMessage(@RequestBody MessageDto messageDto) {
        ApiResponse response = chatService.sendMessage(messageDto);
        return ResponseEntity.status(response.isSuccess() ? 201 : 400).body(response);
    }
}
