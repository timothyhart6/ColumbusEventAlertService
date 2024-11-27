CREATE TABLE events (
    id SERIAL PRIMARY KEY,
    event TEXT NOT NULL,
    location TEXT NOT NULL,
    date DATE NOT NULL,
    start_time TIME,
    end_time TIME
);
INSERT INTO countries (id, name) VALUES (1, 'USA');
INSERT INTO countries (id, name) VALUES (2, 'France');
INSERT INTO countries (id, name) VALUES (3, 'Brazil');
INSERT INTO countries (id, name) VALUES (4, 'Italy');
INSERT INTO countries (id, name) VALUES (5, 'Canada');