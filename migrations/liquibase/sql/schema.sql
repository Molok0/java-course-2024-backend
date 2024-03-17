CREATE TABLE IF NOT EXISTS CHAT
(
    id  bigint GENERATED ALWAYS AS IDENTITY,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS URL
(
    id             bigint GENERATED ALWAYS AS IDENTITY,
    url            TEXT  NOT NULL,
    last_check     TIME NOT NULL,

    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS CHAT_URL
(
    url_id         BIGINT NOT NULL,
    chat_id        BIGINT NOT NULL,

    PRIMARY KEY (url_id, chat_id),
    CONSTRAINT fk_url_id FOREIGN KEY (url_id) REFERENCES URL(id),
    CONSTRAINT fk_chat_id FOREIGN KEY (chat_id) REFERENCES URL(id)
);

