
create table todo_items_page_projection (
  id varchar(36) primary key,
  todo_list_id varchar(36) not null,
  todo_item_text varchar(255) not null,
  done boolean not null default false
);

update schema_info set version = 3;

