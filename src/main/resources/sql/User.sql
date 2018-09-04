CREATE TABLE "tap_softball"."user"
(
   id serial PRIMARY KEY NOT NULL,
   username varchar(64) NOT NULL,
   password varchar(127) NOT NULL,
   email varchar(64) NOT NULL,
   roles varchar(64) NOT NULL,
   enabled bool DEFAULT false);

