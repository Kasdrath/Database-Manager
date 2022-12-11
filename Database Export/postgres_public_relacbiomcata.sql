create table relacbiomcata
(
    id_relac      bigint default nextval('"relac_biom/cat_id_relac_seq"'::regclass) not null
        constraint "relac_biom/cat_pk"
            primary key,
    relacbiomcata varchar(50)                                                       not null
);

alter table relacbiomcata
    owner to postgres;

create unique index "relac_biom/cat_relac_biom/cat_uindex"
    on relacbiomcata (relacbiomcata);

INSERT INTO public.relacbiomcata (id_relac, relacbiomcata) VALUES (2, '1/4');
INSERT INTO public.relacbiomcata (id_relac, relacbiomcata) VALUES (3, '1/2');
INSERT INTO public.relacbiomcata (id_relac, relacbiomcata) VALUES (4, '1/6');
INSERT INTO public.relacbiomcata (id_relac, relacbiomcata) VALUES (5, '1/7');
INSERT INTO public.relacbiomcata (id_relac, relacbiomcata) VALUES (6, '1/10');
INSERT INTO public.relacbiomcata (id_relac, relacbiomcata) VALUES (7, '1/14');
