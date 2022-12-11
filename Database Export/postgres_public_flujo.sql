create table flujo
(
    id_flujo serial
        constraint flujo_pk
            primary key,
    flujo    integer not null
);

alter table flujo
    owner to postgres;

create unique index flujo_flujo_uindex
    on flujo (flujo);

create unique index flujo_id_flujo_uindex
    on flujo (id_flujo);

INSERT INTO public.flujo (id_flujo, flujo) VALUES (3, 60);
INSERT INTO public.flujo (id_flujo, flujo) VALUES (4, 120);
INSERT INTO public.flujo (id_flujo, flujo) VALUES (5, 180);
