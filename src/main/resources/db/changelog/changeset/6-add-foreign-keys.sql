ALTER TABLE posts
    ADD CONSTRAINT fk_posts_user
        FOREIGN KEY (user_id) REFERENCES users(id);

ALTER TABLE comments
    ADD CONSTRAINT fk_comments_post
        FOREIGN KEY (post_id) REFERENCES posts(id);

ALTER TABLE comments
    ADD CONSTRAINT fk_comments_user
        FOREIGN KEY (user_id) REFERENCES users(id);

ALTER TABLE pictures
    ADD COLUMN user_id UUID UNIQUE;

ALTER TABLE pictures
    ADD CONSTRAINT fk_pictures_user_avatar
        FOREIGN KEY (user_id) REFERENCES users(id);

ALTER TABLE post_picture
    ADD CONSTRAINT fk_post_picture_post
        FOREIGN KEY (post_id) REFERENCES posts(id);

ALTER TABLE post_picture
    ADD CONSTRAINT fk_post_picture_picture
        FOREIGN KEY (picture_id) REFERENCES pictures(id);
