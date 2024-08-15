package pdp.uz.humo_online_jobs.job.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pdp.uz.humo_online_jobs.job.JobEntity;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<JobEntity, Long> {
    List<JobEntity> findByIsActiveTrue(); // Retrieve all active jobs
    List<JobEntity> findByTitleContainingIgnoreCaseAndIsActiveTrue(String title); // Filter jobs by title
}
