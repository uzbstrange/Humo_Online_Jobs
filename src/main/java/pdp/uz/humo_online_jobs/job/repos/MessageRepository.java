package pdp.uz.humo_online_jobs.job.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.humo_online_jobs.job.MessageEntity;

public interface MessageRepository extends JpaRepository<MessageEntity, Long> {
}
