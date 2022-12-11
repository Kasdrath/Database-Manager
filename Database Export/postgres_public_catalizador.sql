create table catalizador
(
    id_catalizador serial
        constraint catalizador_pk
            primary key,
    catalizador    varchar(34) not null
);

alter table catalizador
    owner to postgres;

create unique index catalizador_catalizador_uindex
    on catalizador (catalizador);

create unique index catalizador_id_catalizador_uindex
    on catalizador (id_catalizador);

INSERT INTO public.catalizador (id_catalizador, catalizador) VALUES (6, 'No Catalizador');
