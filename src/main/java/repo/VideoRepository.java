package repo;


import entities.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface VideoRepository extends JpaRepository<Video, UUID> {

    // Find a video by title and year (to prevent duplicate entries)
    Optional<Video> findByTitleAndYearOfRelease(String title, int yearOfRelease);

    // Fetch all videos that are not soft deleted
    List<Video> findByIsDeletedFalse();

    // Search videos by director (case insensitive)
    List<Video> findByDirectorIgnoreCaseAndIsDeletedFalse(String director);

    // Search videos by genre
    List<Video> findByGenreAndIsDeletedFalse(String genre);
}