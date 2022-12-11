create table tasa_calent
(
    id_tasa_calent bigserial
        constraint tasa_calent_pk
            primary key,
    tasa_calent    varchar(20) not null
);

alter table tasa_calent
    owner to postgres;

create unique index tasa_calent_tasa_calent_uindex
    on tasa_calent (tasa_calent);

INSERT INTO public.tasa_calent (id_tasa_calent, tasa_calent) VALUES (1, 'SLOW');
INSERT INTO public.tasa_calent (id_tasa_calent, tasa_calent) VALUES (3, 'FAST');
