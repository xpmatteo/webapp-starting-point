
create table domain_events (
  id serial primary key,
  occurredOn timestamp,
  version int,
  eventType varchar(255),
  params json
);

update schema_info set version = 2;

