package pdp.uz.humo_online_jobs.job.service;

import org.springframework.stereotype.Service;
import pdp.uz.humo_online_jobs.custom_responses.ApiResponse;
import pdp.uz.humo_online_jobs.job.JobEntity;
import pdp.uz.humo_online_jobs.job.dto.JobDto;
import pdp.uz.humo_online_jobs.job.dto.JobSearchDto;
import pdp.uz.humo_online_jobs.job.repos.JobRepository;
import pdp.uz.humo_online_jobs.user.UserEntity;
import pdp.uz.humo_online_jobs.user.UserRepository;

import java.util.List;

@Service
public class JobService {

    private final JobRepository jobRepository;
    private final UserRepository userRepository;

    public JobService(JobRepository jobRepository, UserRepository userRepository) {
        this.jobRepository = jobRepository;
        this.userRepository = userRepository;
    }

    public ApiResponse postJob(JobDto jobDto, Long employerId) {
        UserEntity employer = userRepository.findById(employerId)
                .orElseThrow(() -> new IllegalStateException("Employer not found"));

        JobEntity job = new JobEntity();
        job.setTitle(jobDto.getTitle());
        job.setDescription(jobDto.getDescription());
        job.setEmployer(employer);
        job.setActive(true);
        jobRepository.save(job);

        return new ApiResponse("Job posted successfully", true);
    }

    public List<JobEntity> findJobs(JobSearchDto searchDto) {
        if (searchDto.getTitle() != null && !searchDto.getTitle().isEmpty()) {
            return jobRepository.findByTitleContainingIgnoreCaseAndIsActiveTrue(searchDto.getTitle());
        }
        return jobRepository.findByIsActiveTrue(); // Retrieve all active jobs
    }

}
