CREATE TABLE "tap_softball"."campaign"
(
   id serial PRIMARY KEY NOT NULL,
   season_id int NOT NULL,
   team_id int NOT NULL,
   division_id int NOT NULL,
   name varchar(40)
)
;
CREATE UNIQUE INDEX campaign_pkey ON "tap_softball"."campaign"(id)
;

ALTER TABLE "tap_softball"."campaign"
ADD CONSTRAINT season_FK
FOREIGN KEY (season_id)
REFERENCES "tap_softball"."season"(id)
;
CREATE INDEX fki_season ON "tap_softball"."campaign"(season_id)
;

ALTER TABLE "tap_softball"."campaign"
ADD CONSTRAINT team_FK
FOREIGN KEY (team_id)
REFERENCES "tap_softball"."team"(id)
;
CREATE INDEX fki_campaignteam ON "tap_softball"."campaign"(team_id)
;

ALTER TABLE "tap_softball"."campaign"
ADD CONSTRAINT division_FK
FOREIGN KEY (division_id)
REFERENCES "tap_softball"."division"(id)
;
CREATE INDEX fki_campaigndivision ON "tap_softball"."campaign"(division_id)
;