package service.impl;
import Exception.ResourceNotFoundException;

import entities.Video;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repo.VideoRepository;
import service.VideoService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class VideoServiceImpl implements VideoService {


    private final VideoRepository videoRepository;

    @Autowired
    public VideoServiceImpl(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }


    @Override
    @Transactional
    public Video publishVideo(Video video) {
//        log.info("Publishing new video: {}", video.getTitle());

        // Prevent duplicate video entries
        videoRepository.findByTitleAndYearOfRelease(video.getTitle(), video.getYearOfRelease())
                .ifPresent(existingVideo -> {
                    throw new IllegalArgumentException("Video with the same title and year already exists.");
                });

        Video savedVideo = videoRepository.save(video);
//        log.info("Video published successfully: {}", savedVideo.getId());
        return savedVideo;
    }

    @Override
    @Transactional
    public Video updateVideo(UUID videoId, Video updatedVideo) {
//        log.info("Updating video with ID: {}", videoId);
        Video existingVideo = videoRepository.findById(videoId)
                .orElseThrow(() -> new ResourceNotFoundException("Video not found with ID: " + videoId));

        // Update fields
        existingVideo.setTitle(updatedVideo.getTitle());
        existingVideo.setSynopsis(updatedVideo.getSynopsis());
        existingVideo.setDirector(updatedVideo.getDirector());
        existingVideo.setMainActor(updatedVideo.getMainActor());
        existingVideo.setGenre(updatedVideo.getGenre());
        existingVideo.setYearOfRelease(updatedVideo.getYearOfRelease());
        existingVideo.setRunningTime(updatedVideo.getRunningTime());

        Video savedVideo = videoRepository.save(existingVideo);
//        log.info("Video updated successfully: {}", savedVideo.getId());
        return savedVideo;
    }



    @Override
    @Transactional
    public void deleteVideo(UUID videoId) {
//        log.info("Soft deleting video with ID: {}", videoId);
        Video video = videoRepository.findById(videoId)
                .orElseThrow(() -> new ResourceNotFoundException("Video not found with ID: " + videoId));

        video.setDeleted(true);
        videoRepository.save(video);
//        log.info("Video soft deleted: {}", videoId);
    }

    @Override
    public Optional<Video> getVideoById(UUID videoId) {
//        log.info("Fetching video details for ID: {}", videoId);
        return videoRepository.findById(videoId)
                .filter(video -> !video.isDeleted());
    }

    @Override
    public List<Video> getAllAvailableVideos() {
//        log.info("Fetching all available videos.");
        return videoRepository.findByIsDeletedFalse();
    }

    @Override
    public List<Video> searchByDirector(String director) {
//        log.info("Searching for videos by director: {}", director);
        return videoRepository.findByDirectorIgnoreCaseAndIsDeletedFalse(director);
    }

    @Override
    public List<Video> searchByGenre(String genre) {
//        log.info("Searching for videos by genre: {}", genre);
        return videoRepository.findByGenreAndIsDeletedFalse(genre);
    }
}

