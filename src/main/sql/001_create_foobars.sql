
create table foobars (
  foo varchar(255) not null,
  bar integer not null,
  primary key(foo)
);

update schema_info set version = 1;

