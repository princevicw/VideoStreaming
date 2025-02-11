package controller;
import entities.Video;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.VideoService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/videos")

public class VideoController {

    private final VideoService videoService;

    @Autowired
    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }





    // Publish a new video

    @PostMapping("/publishVideo")
    public ResponseEntity<Video> publishVideo(@RequestBody Video video) {
        Video savedVideo = videoService.publishVideo(video);
        return ResponseEntity.ok(savedVideo);
    }

    // Get all videos
    @GetMapping("/getAllVideo")
    public ResponseEntity<List<Video>> getAllVideos() {
        List<Video> videos = videoService.getAllAvailableVideos();
        return ResponseEntity.ok(videos);
    }

    // Get video by ID
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Video>> getVideoById(@PathVariable UUID id) {
        return ResponseEntity.ok(videoService.getVideoById(id));
    }

    // Soft delete a video by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> softDeleteVideo(@PathVariable UUID id) {
        videoService.deleteVideo(id);
        return ResponseEntity.noContent().build();
    }
}