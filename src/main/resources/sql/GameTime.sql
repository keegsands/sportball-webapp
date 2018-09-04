CREATE TABLE "tap_softball"."game_time"
(
   id serial PRIMARY KEY NOT NULL,
   timeslot time NOT NULL
)
;
CREATE UNIQUE INDEX game_time_pkey ON "tap_softball"."game_time"(id)
;
