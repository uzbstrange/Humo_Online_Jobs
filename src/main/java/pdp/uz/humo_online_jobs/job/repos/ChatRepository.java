package pdp.uz.humo_online_jobs.job.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.humo_online_jobs.job.ChatEntity;

public interface ChatRepository extends JpaRepository<ChatEntity, Long> {

}
