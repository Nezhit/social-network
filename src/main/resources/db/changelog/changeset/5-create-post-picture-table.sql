CREATE TABLE post_picture (
                              post_id UUID NOT NULL,
                              picture_id UUID NOT NULL,
                              PRIMARY KEY (post_id, picture_id)
);
