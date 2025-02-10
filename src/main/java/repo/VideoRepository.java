package repo;

import entities.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideoRepository extends JpaRepository<Video, Long> {
    List<Video> findByIsDeletedFalse();
    List<Video> findByDirectorContainingIgnoreCaseAndIsDeletedFalse(String director);
}

