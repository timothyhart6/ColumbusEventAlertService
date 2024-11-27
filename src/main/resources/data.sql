CREATE TABLE events (
    id SERIAL PRIMARY KEY,
    event TEXT NOT NULL,
    location TEXT NOT NULL,
    date DATE NOT NULL,
    start_time TIME,
    end_time TIME
);

INSERT INTO events (event, location, date, start_time) VALUES ('OSU VS. Michigan', 'Ohio Stadium', '2024-10-26', '12:00');
INSERT INTO events (event, location, date, start_time, end_time) VALUES ('Hops on High', 'Short North', '2024-12-07','12:00', '20:00');
INSERT INTO events (event, location, date, start_time) VALUES ('Village Lights', 'German Village', '2024-12-08', '21:00');
