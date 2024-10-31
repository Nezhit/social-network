CREATE TABLE pictures (
                          id UUID PRIMARY KEY,
                          content BYTEA NOT NULL,
                          create TIMESTAMP WITH TIME ZONE NOT NULL
);
