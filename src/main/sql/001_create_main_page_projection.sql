
create table todo_lists_main_page_projection (
  id serial primary key,
  name varchar(255) not null
);

update schema_info set version = 1;

