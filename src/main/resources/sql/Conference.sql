CREATE TABLE "tap_softball"."conference"
(
   id serial PRIMARY KEY NOT NULL,
   name varchar(40) NOT NULL
)
;
CREATE UNIQUE INDEX conference_pkey ON "tap_softball"."conference"(id)
;
