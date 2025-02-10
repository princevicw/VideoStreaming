CREATE TABLE videos (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255),
    synopsis TEXT,
    director VARCHAR(255),
    cast TEXT,
    release_year INT,
    genre VARCHAR(100),
    running_time INT,
    is_deleted BOOLEAN DEFAULT FALSE
);


CREATE TABLE video_engagement (
    id BIGSERIAL PRIMARY KEY,
    video_id BIGINT REFERENCES videos(id),
    impressions INT DEFAULT 0,
    views INT DEFAULT 0
);
