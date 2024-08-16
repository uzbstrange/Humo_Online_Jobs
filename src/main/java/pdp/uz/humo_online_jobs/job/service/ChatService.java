package pdp.uz.humo_online_jobs.job.service;

import org.springframework.stereotype.Service;
import pdp.uz.humo_online_jobs.custom_responses.ApiResponse;
import pdp.uz.humo_online_jobs.job.ChatEntity;
import pdp.uz.humo_online_jobs.job.MessageEntity;
import pdp.uz.humo_online_jobs.job.dto.ChatDto;
import pdp.uz.humo_online_jobs.job.dto.MessageDto;
import pdp.uz.humo_online_jobs.job.repos.ChatRepository;
import pdp.uz.humo_online_jobs.job.repos.MessageRepository;
import pdp.uz.humo_online_jobs.user.UserEntity;
import pdp.uz.humo_online_jobs.user.UserRepository;

@Service
public class ChatService {

    private final UserRepository userRepository;
    private final ChatRepository chatRepository;
    private final MessageRepository messageRepository;

    public ChatService(UserRepository userRepository, ChatRepository chatRepository, MessageRepository messageRepository) {
        this.userRepository = userRepository;
        this.chatRepository = chatRepository;
        this.messageRepository = messageRepository;
    }

    public ApiResponse startChat(ChatDto chatDto) {
        UserEntity employer = userRepository.findById(chatDto.getEmployerId())
                .orElseThrow(() -> new IllegalStateException("Employer not found"));
        UserEntity jobSeeker = userRepository.findById(chatDto.getJobSeekerId())
                .orElseThrow(() -> new IllegalStateException("Job Seeker not found"));

        ChatEntity chat = new ChatEntity();
        chat.setEmployer(employer);
        chat.setJobSeeker(jobSeeker);
        chatRepository.save(chat);

        return new ApiResponse("Chat started successfully", true);
    }

    public ApiResponse sendMessage(MessageDto messageDto) {
        ChatEntity chat = chatRepository.findById(messageDto.getChatId())
                .orElseThrow(() -> new IllegalStateException("Chat not found"));
        UserEntity sender = userRepository.findById(messageDto.getSenderId())
                .orElseThrow(() -> new IllegalStateException("Sender not found"));

        MessageEntity message = new MessageEntity();
        message.setChat(chat);
        message.setSender(sender);
        message.setContent(messageDto.getContent());
        message.setTimestamp(System.currentTimeMillis());
        messageRepository.save(message);

        return new ApiResponse("Message sent successfully", true);
    }
}
