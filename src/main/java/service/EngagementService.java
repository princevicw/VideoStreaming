package service;

import entities.Engagement;

import java.util.Optional;
import java.util.UUID;

public interface EngagementService {

    // Track when a video is loaded (Impression)
    void trackImpression(UUID videoId);

    // Track when a video is played (View)
    void trackView(UUID videoId);

    // Retrieve engagement statistics for a video
    Optional<Engagement> getEngagementStats(UUID videoId);
}