package service;

import com.VideoStreaming.entities.Video;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Service
public interface VideoService {


    //save video
    public Video saveVideo(Video video, MultipartFile file) ;

    //get video by id
    Video getById(String videoId);

    Video getByTitle(String title);

    List<Video> getAllVideo();



}
