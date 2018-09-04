CREATE TABLE "tap_softball"."division"
(
   id serial PRIMARY KEY NOT NULL,
   name varchar(40) NOT NULL,
   conference_id int NOT NULL
)
;
CREATE UNIQUE INDEX division_pkey ON "tap_softball"."division"(id)
;

ALTER TABLE "tap_softball"."division"
ADD CONSTRAINT conferene_FK
FOREIGN KEY (conference_id)
REFERENCES "tap_softball"."conference"(id)
;
CREATE INDEX fki_conference ON "tap_softball"."division"(conference_id)
;
