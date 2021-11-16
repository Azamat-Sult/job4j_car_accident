CREATE TABLE if not exists accidentTypes (
  id serial primary key,
  type_name varchar(255)
);

CREATE TABLE if not exists accidentRules (
  id serial primary key,
  name varchar(255)
);

CREATE TABLE if not exists accidents (
  id serial primary key,
  name varchar(255),
  text varchar(255),
  address varchar(255),
  accidentType_id INT references accidentTypes(id)
);

CREATE TABLE if not exists accidents_accidentrules (
  id serial primary key,
  accident_id INT references accidents(id),
  rules_id INT references accidentRules(id)
);