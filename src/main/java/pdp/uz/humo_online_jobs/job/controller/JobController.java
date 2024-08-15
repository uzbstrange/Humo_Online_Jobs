package pdp.uz.humo_online_jobs.job.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pdp.uz.humo_online_jobs.custom_responses.ApiResponse;
import pdp.uz.humo_online_jobs.job.JobEntity;
import pdp.uz.humo_online_jobs.job.dto.JobDto;
import pdp.uz.humo_online_jobs.job.dto.JobSearchDto;
import pdp.uz.humo_online_jobs.job.service.JobService;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping("/post")
    public ResponseEntity<ApiResponse> postJob(@RequestBody JobDto jobDto, @RequestParam Long employerId) {
        ApiResponse response = jobService.postJob(jobDto, employerId);
        return ResponseEntity.status(response.isSuccess() ? 201 : 400).body(response);
    }

    @GetMapping
    public ResponseEntity<List<JobEntity>> findJobs(@RequestParam(required = false) String title) {
        JobSearchDto searchDto = new JobSearchDto();
        searchDto.setTitle(title);
        List<JobEntity> jobs = jobService.findJobs(searchDto);
        return ResponseEntity.ok(jobs);
    }
}
