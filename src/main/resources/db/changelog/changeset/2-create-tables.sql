CREATE TABLE users
(
    id   UUID DEFAULT uuid_generate_v4() NOT NULL
        CONSTRAINT users_id_pkey PRIMARY KEY,
    name VARCHAR(255)                    NOT NULL
);

CREATE TABLE posts
(
    id            UUID               DEFAULT uuid_generate_v4() NOT NULL
        CONSTRAINT posts_id_pkey PRIMARY KEY,
    author_id     UUID      NOT NULL,
    description   varchar(255),
    likes         INTEGER   NOT NULL DEFAULT 0,
    pub_date      TIMESTAMP NOT NULL,
    modified_date TIMESTAMP
);

alter table posts
    add foreign key (author_id) references users;

CREATE TABLE pictures
(
    id      UUID               DEFAULT uuid_generate_v4() NOT NULL
        CONSTRAINT pictures_id_pkey PRIMARY KEY,
    content OID,
    created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    post_id uuid      NOT NULL
);

alter table pictures
    add foreign key (post_id) references posts;

CREATE TABLE comments
(
    id            UUID DEFAULT uuid_generate_v4() NOT NULL
        CONSTRAINT comments_id_pkey PRIMARY KEY,
    author_id     UUID                            not null,
    text          varchar(255)                    NOT NULL,
    pub_date      TIMESTAMP                       NOT NULL,
    modified_date TIMESTAMP,
    post_id       UUID                            NOT NULL
);

alter table comments
    add foreign key (author_id) references users;

alter table comments
    add foreign key (post_id) references posts;
