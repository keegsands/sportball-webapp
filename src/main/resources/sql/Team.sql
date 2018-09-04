CREATE TABLE "tap_softball"."team"
(
   name varchar(40) NOT NULL,
   id serial PRIMARY KEY NOT NULL
)
;
CREATE UNIQUE INDEX PK_TEAM ON "tap_softball"."team"(id)
;
