CREATE TABLE posts (
                       id UUID PRIMARY KEY,
                       user_id UUID,
                       likes INT DEFAULT 0,
                       date TIMESTAMP WITH TIME ZONE NOT NULL
);
