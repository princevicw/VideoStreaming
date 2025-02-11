package repo;


import entities.Engagement;
import entities.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EngagementRepository extends JpaRepository<Engagement, UUID> {

    // Find engagement stats for a video
    Optional<Engagement> findByVideo(Video video);
}