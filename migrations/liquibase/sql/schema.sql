CREATE TABLE IF NOT EXISTS CHAT
(
    id bigint not null,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS URL
(
    id          bigint GENERATED ALWAYS AS IDENTITY,
    url         TEXT                     NOT NULL,
    last_check  timestamp with time zone NOT NULL,
    last_change timestamp with time zone NOT NULL,


    PRIMARY KEY (id),
    CONSTRAINT unique_url UNIQUE (url)
);

CREATE TABLE IF NOT EXISTS CHAT_URL
(
    url_id  BIGINT NOT NULL,
    chat_id BIGINT NOT NULL,

    PRIMARY KEY (url_id, chat_id),
    CONSTRAINT fk_url_id FOREIGN KEY (url_id) REFERENCES URL (id) ON delete cascade,
    CONSTRAINT fk_chat_id FOREIGN KEY (chat_id) REFERENCES CHAT (id) ON delete cascade
);

