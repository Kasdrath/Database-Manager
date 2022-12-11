create table familia
(
    familia_id   integer     not null
        primary key,
    familia_name varchar(50) not null
);

alter table familia
    owner to postgres;

create unique index familia_familia_name_uindex
    on familia (familia_name);

INSERT INTO public.familia (familia_id, familia_name) VALUES (2, 'Aldehidos');
INSERT INTO public.familia (familia_id, familia_name) VALUES (4, 'Acidos');
INSERT INTO public.familia (familia_id, familia_name) VALUES (1, 'Alcoholes');
INSERT INTO public.familia (familia_id, familia_name) VALUES (5, 'Amides');
INSERT INTO public.familia (familia_id, familia_name) VALUES (6, 'Amines');
INSERT INTO public.familia (familia_id, familia_name) VALUES (7, 'Aromaticos');
INSERT INTO public.familia (familia_id, familia_name) VALUES (8, 'Cetonas');
INSERT INTO public.familia (familia_id, familia_name) VALUES (9, 'Esteres');
INSERT INTO public.familia (familia_id, familia_name) VALUES (10, 'HC_Aromatico');
INSERT INTO public.familia (familia_id, familia_name) VALUES (11, 'Hidrocarburos');
INSERT INTO public.familia (familia_id, familia_name) VALUES (12, 'Phenols');
INSERT INTO public.familia (familia_id, familia_name) VALUES (13, 'Standart_Internal');
INSERT INTO public.familia (familia_id, familia_name) VALUES (14, 'Sugars');
INSERT INTO public.familia (familia_id, familia_name) VALUES (15, 'Unknown');
