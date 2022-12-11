create table analisis_en_t
(
    id_analisis_en_t bigserial
        constraint analisis_en_t_pk
            primary key,
    tiempo           integer not null
);

alter table analisis_en_t
    owner to postgres;

create unique index analisis_en_t_id_analisis_en_t_uindex
    on analisis_en_t (id_analisis_en_t);

create unique index analisis_en_t_tiempo_uindex
    on analisis_en_t (tiempo);

INSERT INTO public.analisis_en_t (id_analisis_en_t, tiempo) VALUES (4, 0);
INSERT INTO public.analisis_en_t (id_analisis_en_t, tiempo) VALUES (5, 1);
