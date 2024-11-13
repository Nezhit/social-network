CREATE TABLE pictures
(

    id      UUID               DEFAULT uuid_generate_v4() NOT NULL
        CONSTRAINT pictures_id_pkey PRIMARY KEY,
    content OID       NOT NULL,
    created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE users
(
    id        UUID DEFAULT uuid_generate_v4() NOT NULL
        CONSTRAINT users_id_pkey PRIMARY KEY,
    avatar_id UUID                            REFERENCES pictures (id) ON DELETE SET NULL,
    name      VARCHAR(255)                    NOT NULL
);

CREATE TABLE posts
(
    id        UUID               DEFAULT uuid_generate_v4() NOT NULL
        CONSTRAINT posts_id_pkey PRIMARY KEY,
    pic_id    UUID      REFERENCES pictures (id) ON DELETE SET NULL,
    author_id UUID REFERENCES users (id) ON DELETE CASCADE,
    likes     INTEGER   NOT NULL DEFAULT 0,
    pub_date  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE comments
(
    id        UUID               DEFAULT uuid_generate_v4() NOT NULL
        CONSTRAINT comments_id_pkey PRIMARY KEY,
    author_id UUID REFERENCES users (id) ON DELETE CASCADE,
    text      TEXT      NOT NULL,
    published TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    post_id   UUID REFERENCES posts (id) ON DELETE CASCADE
);
