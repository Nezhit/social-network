CREATE TABLE pictures
(
    id      UUID PRIMARY KEY,
    content BYTEA                    NOT NULL,
    created  TIMESTAMP WITH TIME ZONE NOT NULL
);
