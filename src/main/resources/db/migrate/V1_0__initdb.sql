create sequence orders_id_seq;

create table orders (
    id bigint primary key default nextval('orders_id_seq'),
    number integer,
    dishes character varying(300),
    table_number integer,
    waiter_name character varying(50)
)