CREATE TABLE "tap_softball"."player"
(
   id serial PRIMARY KEY NOT NULL,
   team_id int NOT NULL,
   first_name VARCHAR(64) DEFAULT NULL,
   last_name VARCHAR(64) DEFAULT NULL,
   status VARCHAR(24) DEFAULT NULL,
   gender VARCHAR(12) DEFAULT NULL),
   email VARCHAR(64) DEFAULT NULL);

ALTER TABLE "tap_softball"."player"
ADD CONSTRAINT team_FK
FOREIGN KEY (team_id)
REFERENCES "tap_softball"."team"(id);