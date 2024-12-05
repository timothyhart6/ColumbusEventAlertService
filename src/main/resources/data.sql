CREATE TABLE events (
    id SERIAL PRIMARY KEY,
    event TEXT NOT NULL,
    location TEXT NOT NULL,
    date DATE NOT NULL,
    start_time TIME,
    end_time TIME
);

INSERT INTO events (event, location, date, start_time, end_time) VALUES ('Hops on High', 'Short North', '2024-12-07','12:00', '20:00');
INSERT INTO events (event, location, date, start_time) VALUES ('OSU VS. Michigan', 'Ohio Stadium', '2024-10-26', '12:00');
INSERT INTO events (event, location, date, start_time) VALUES ('Village Lights', 'German Village', '2024-12-08', '21:00');
INSERT INTO events (event, location, date) VALUES ('The Arnold Classic', 'Convention Center', '2025-02-28');
INSERT INTO events (event, location, date) VALUES ('The Arnold Classic', 'Convention Center', '2025-03-01');
INSERT INTO events (event, location, date) VALUES ('The Arnold Classic', 'Convention Center', '2025-03-02');
INSERT INTO events (event, location, date, start_time, end_time) VALUES ('Cap City Marathon', 'Columbus', '2025-04-26', '08:00', '13:00');
INSERT INTO events (event, location, date, start_time) VALUES ('OSU Commencement', 'Ohio Stadium', '2025-05-04', '12:00');
INSERT INTO events (event, location, date) VALUES ('Arts Festival', 'Downtown Riverfront', '2025-06-06');
INSERT INTO events (event, location, date) VALUES ('Arts Festival', 'Downtown Riverfront', '2025-06-07');
INSERT INTO events (event, location, date) VALUES ('Arts Festival', 'Downtown Riverfront', '2025-06-08');
INSERT INTO events (event, location, date) VALUES ('Origins Game Fair', 'Convention Center', '2025-06-18');
INSERT INTO events (event, location, date) VALUES ('Origins Game Fair', 'Convention Center', '2025-06-19');
INSERT INTO events (event, location, date) VALUES ('Origins Game Fair', 'Convention Center', '2025-06-20');
INSERT INTO events (event, location, date) VALUES ('Origins Game Fair', 'Convention Center', '2025-06-21');
INSERT INTO events (event, location, date) VALUES ('Origins Game Fair', 'Convention Center', '2025-06-22');
INSERT INTO events (event, location, date) VALUES ('Buckeye Country Superfest', 'Ohio Stadium', '2025-06-21');
INSERT INTO events (event, location, date) VALUES ('Comfest', 'Goodale Park', '2025-06-27');
INSERT INTO events (event, location, date) VALUES ('Comfest', 'Goodale Park', '2025-06-28');
INSERT INTO events (event, location, date) VALUES ('Comfest', 'Goodale Park', '2025-06-29');
INSERT INTO events (event, location, date) VALUES ('Red, White & Boom', 'Downtown', '2025-07-03');
INSERT INTO events (event, location, date) VALUES ('Doo Dah Parade', 'Short North', '2025-07-04');
INSERT INTO events (event, location, date) VALUES ('Jazz & Rib Fest', 'Scioto Mile', '2025-07-18');
INSERT INTO events (event, location, date) VALUES ('Jazz & Rib Fest', 'Scioto Mile', '2025-07-19');
INSERT INTO events (event, location, date) VALUES ('Jazz & Rib Fest', 'Scioto Mile', '2025-07-20');
