CREATE TABLE technicians (
    user_id BIGINT NOT NULL,
    PRIMARY KEY (user_id),
    CONSTRAINT FK_user_technician FOREIGN KEY (user_id) REFERENCES users (user_id)
);
