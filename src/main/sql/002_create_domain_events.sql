
create table domain_events (
  id serial primary key,
  occurredOn timestamp not null,
  version int not null,
  eventType varchar(255) not null,
  entityId varchar(36) not null,
  params json not null
);

update schema_info set version = 2;

