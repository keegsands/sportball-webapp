CREATE TABLE "tap_softball"."game"
(
   id serial PRIMARY KEY NOT NULL,
   season_id int NOT NULL,
   home_team_id int NOT NULL,
   away_team_id int NOT NULL,
   home_team_runs int DEFAULT 0 NOT NULL,
   away_team_runs int DEFAULT 0 NOT NULL,
   original_date date NOT NULL,
   scheduled_date date NOT NULL,
   field_id int NOT NULL,
   time_id int NOT NULL,
   complete bool DEFAULT false,
   status VARCHAR(64) DEFAULT NULL);

ALTER TABLE "tap_softball"."game"
ADD CONSTRAINT season_FK
FOREIGN KEY (season_id)
REFERENCES "tap_softball"."season"(id)
;
ALTER TABLE "tap_softball"."game"
ADD CONSTRAINT away_team_FK
FOREIGN KEY (away_team_id)
REFERENCES "tap_softball"."team"(id)
;
ALTER TABLE "tap_softball"."game"
ADD CONSTRAINT field_FK
FOREIGN KEY (field_id)
REFERENCES "tap_softball"."field"(id)
;
ALTER TABLE "tap_softball"."game"
ADD CONSTRAINT home_team_FK
FOREIGN KEY (home_team_id)
REFERENCES "tap_softball"."team"(id)
;
ALTER TABLE "tap_softball"."game"
ADD CONSTRAINT time_FK
FOREIGN KEY (time_id)
REFERENCES "tap_softball"."game_time"(id)
;
CREATE INDEX fki_gametime ON "tap_softball"."game"(time_id)
;
CREATE INDEX fki_ ON "tap_softball"."game"(season_id)
;
CREATE INDEX fki_team ON "tap_softball"."game"(field_id)
;
CREATE UNIQUE INDEX game_pkey ON "tap_softball"."game"(id)
;
