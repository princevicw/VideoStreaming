package service;

import entities.Video;
import org.springframework.stereotype.Service;
import repo.VideoRepository;

import java.util.List;
import java.util.Optional;

@Service
class VideoService {
    private final VideoRepository videoRepository;

    public VideoService(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    public Video publishVideo(Video video) {
        return videoRepository.save(video);
    }

    public Optional<Video> editVideoMetadata(Long id, Video updatedVideo) {
        return videoRepository.findById(id).map(video -> {
            video.setTitle(updatedVideo.getTitle());
            video.setDirector(updatedVideo.getDirector());
            video.setGenre(updatedVideo.getGenre());
            video.setRunningTime(updatedVideo.getRunningTime());
            return videoRepository.save(video);
        });
    }

    public void softDeleteVideo(Long id) {
        videoRepository.findById(id).ifPresent(video -> {
            video.setDeleted(true);
            videoRepository.save(video);
        });
    }

    public List<Video> listVideos() {
        return videoRepository.findByIsDeletedFalse();
    }

    public List<Video> searchVideos(String director) {
        return videoRepository.findByDirectorContainingIgnoreCaseAndIsDeletedFalse(director);
    }
}
