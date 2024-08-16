package pdp.uz.humo_online_jobs.job.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pdp.uz.humo_online_jobs.job.dto.ChatDto;
import pdp.uz.humo_online_jobs.job.dto.JobDto;
import pdp.uz.humo_online_jobs.job.service.ChatService;
import pdp.uz.humo_online_jobs.job.service.JobService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class JobController {

    private final JobService jobService;
    private final ChatService chatService;

    public JobController(JobService jobService, ChatService chatService) {
        this.jobService = jobService;
        this.chatService = chatService;
    }

    @PostMapping("/jobs/post")
    public ResponseEntity<?> postJob(@RequestParam Long employerId, @RequestBody JobDto jobDto) {
        return ResponseEntity.ok(jobService.postJob(employerId, jobDto));
    }

    @GetMapping("/jobs")
    public ResponseEntity<List<JobDto>> searchJobs(@RequestParam String title) {
        return ResponseEntity.ok(jobService.findJobs(title));
    }

    @PostMapping("/api/chats/start")
    public ResponseEntity<?> startChat(@RequestBody ChatDto chatDto) {
        return ResponseEntity.ok(chatService.startChat(chatDto));
    }
}
