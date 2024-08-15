package pdp.uz.humo_online_jobs.job.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class JobSearchDto {
    private String title; // Optional: Filter by job title
}
