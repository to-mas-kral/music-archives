INSERT INTO band (name, founded, genre, country)
VALUES ('Metallica', '1981-6-4', 'thrash metal', 'USA');
INSERT INTO band (name, founded, genre, country)
VALUES ('Iron Maiden', '1967-2-3', 'heavy metal', 'Great Britain');
INSERT INTO band (name, founded, genre, country)
VALUES ('Nightwish', '1967-3-5', 'symphonic metal', 'Finland');

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

INSERT INTO album (band_id, name, released)
VALUES (1, 'Ride The Lightning', '1984-4-9');

INSERT INTO album (band_id, name, released)
VALUES (1, 'Master of Puppets', '1986-3-1');

INSERT INTO album (band_id, name, released)
VALUES (1, '...and Justice for All', '1988-12-7');

INSERT INTO song (album_id, order_in_album, name, length)
VALUES (1, 1, 'Fight Fire with Fire', '00:04:45');

INSERT INTO song (album_id, order_in_album, name, length)
VALUES (1, 2, 'Ride the Lightning', '00:06:36');

INSERT INTO song (album_id, order_in_album, name, length)
VALUES (1, 3, 'For Whom the Bell Tolls', '00:05:10');

INSERT INTO song (album_id, order_in_album, name, length)
VALUES (1, 4, 'Fade to Black', '00:06:57');

INSERT INTO song (album_id, order_in_album, name, length)
VALUES (1, 5, 'Trapped Under Ice', '00:04:04');

INSERT INTO song (album_id, order_in_album, name, length)
VALUES (1, 6, 'Escape', '00:04:24');

INSERT INTO song (album_id, order_in_album, name, length)
VALUES (1, 7, 'Creeping Death', '00:06:35');

INSERT INTO song (album_id, order_in_album, name, length)
VALUES (1, 8, 'The Call of Ktulu', '00:08:53');


--
-- Users
--

INSERT INTO _users (role, email, password)
VALUES (0, 'user@user.com', '$2a$10$b67iUefyn6trm.GfhuVaAOAQ599piXrkXVq5A2Icsej/mapSDJpvq')
