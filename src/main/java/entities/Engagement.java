package entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "engagements")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Engagement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "video_id", nullable = false)
    private entities.Video video;

    private int impressions; // Video loaded count
    private int views;       // Video played count

    private LocalDateTime lastUpdated;
}