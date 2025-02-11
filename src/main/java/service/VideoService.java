package service;
import entities.Video;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VideoService {

    // Publish a new video
    Video publishVideo(Video video);

    // Update video metadata
    Video updateVideo(UUID videoId, Video updatedVideo);

    // Soft delete a video
    void deleteVideo(UUID videoId);

    // Load a video (fetch metadata and content)
    Optional<Video> getVideoById(UUID videoId);

    // List all available videos (excluding deleted ones)
    List<Video> getAllAvailableVideos();

    // Search videos by director
    List<Video> searchByDirector(String director);

    // Search videos by genre
    List<Video> searchByGenre(String genre);
}
