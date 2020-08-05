drop table order_detail;
alter table coffee_order rename to orders;


create table coffee_order (
                              order_id bigserial not null,
                              coffee_id bigserial not null,
                              primary key(order_id,coffee_id)
);
alter table coffee_order add constraint coffee_id_fk foreign key (coffee_id) references coffee (id);
alter table coffee_order add constraint order_id_fk foreign key (order_id) references orders (id);