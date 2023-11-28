INSERT INTO band (name, founded)
VALUES ('Metallica', '1981-6-4');
INSERT INTO band (name, founded)
VALUES ('Iron Maiden', '1967-2-3');
INSERT INTO band (name, founded)
VALUES ('Nightwish', '1967-3-5');

--
-- Metallica
--

INSERT INTO member (name)
VALUES ('James Hetfield');

INSERT INTO band_members (band_id, member_id)
VALUES (1, 1);

INSERT INTO member (name)
VALUES ('Lars Ulrich');

INSERT INTO band_members (band_id, member_id)
VALUES (1, 2);

INSERT INTO member (name)
VALUES ('Kirk Hammett');

INSERT INTO band_members (band_id, member_id)
VALUES (1, 3);

INSERT INTO member (name)
VALUES ('Robert Trujillo');

INSERT INTO band_members (band_id, member_id)
VALUES (1, 4);

insert INTO album (band_id, name, released)
VALUES (1, 'Ride The Lightning', '1984-4-9');

insert INTO album (band_id, name, released)
VALUES (1, 'Master of Puppets', '1986-3-1');

insert INTO album (band_id, name, released)
VALUES (1, '...and Justice for All', '1988-12-7');
