package controller;



import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.beans.factory.annotation.Autowired;
import javax.sql.DataSource;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;
import payload.CustomMessage;
import service.VideoService;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import com.VideoStreaming.entities.Video;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/videos")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @PostMapping("api/v1/videos")
    public ResponseEntity<?> create(
            @RequestParam("file")MultipartFile file ,
            @RequestParam("title") String title,
            @RequestParam("description") String description
            ){
        Video video = new Video();
        video.setTitle(title);
        video.setDescription(description);
        video.setVideoId(UUID.randomUUID().toString());
        Video savedVideo=videoService.saveVideo(video,file);

        if(savedVideo!=null)
        {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(video);
        }else
        {
            return ResponseEntity.
                    status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body(CustomMessage.builder().
                    message("Video not found").
                    success(false).
                    build()
                    );

        }





    }




}
