CREATE TABLE "tap_softball"."season"
(
   id serial PRIMARY KEY NOT NULL,
   name varchar(40) NOT NULL,
   standings_supported bool DEFAULT false,
)
;
CREATE UNIQUE INDEX season_pkey ON "tap_softball"."season"(id)
;
