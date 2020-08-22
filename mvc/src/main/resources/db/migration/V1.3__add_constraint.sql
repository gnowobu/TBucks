ALTER TABLE orders
ADD COLUMN customer_id bigserial;
alter table orders add constraint customer_id_fk foreign key (customer_id) references customers (id)
