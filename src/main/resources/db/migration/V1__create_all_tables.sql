create table customers(
	id  bigserial not null,
	name varchar(20) not null,
	password varchar(20),
	email varchar(30),
	primary key (id)
);

create table coffee(
	id bigserial not null,
	price money,
	type varchar(20),
	primary key (id)
);

create table coffee_order(
	id bigserial not null,
	order_time timestamp,
	status varchar(5),
	total money,
	primary key (id)
);
create table order_detail(
	id bigserial not null,
	order_time timestamp,
	coffee_id bigint,
	quantity int,
	order_id bigint,
	sub_total money,
	primary key (id)
);
alter table order_detail add constraint coffee_id_fk foreign key (coffee_id) references coffee (id);
alter table order_detail add constraint order_id_fk foreign key (order_id) references coffee_order (id);