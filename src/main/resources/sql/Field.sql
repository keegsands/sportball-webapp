CREATE TABLE "tap_softball"."field"
(
   id serial PRIMARY KEY NOT NULL,
   name varchar(40) NOT NULL
)
;
CREATE UNIQUE INDEX field_pkey ON "tap_softball"."field"(id)
;
