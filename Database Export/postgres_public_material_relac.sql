create table material_relac
(
    id_material_relac bigserial
        constraint material_relac_pk
            primary key,
    material_relac    varchar(50) not null
);

alter table material_relac
    owner to postgres;

create unique index material_relac_id_material_relac_uindex
    on material_relac (id_material_relac);

create unique index material_relac_material_relac_uindex
    on material_relac (material_relac);

INSERT INTO public.material_relac (id_material_relac, material_relac) VALUES (2, 'Roble');
INSERT INTO public.material_relac (id_material_relac, material_relac) VALUES (3, 'Roble Fibra');
INSERT INTO public.material_relac (id_material_relac, material_relac) VALUES (4, 'LDPE');
INSERT INTO public.material_relac (id_material_relac, material_relac) VALUES (5, 'HDPE');
INSERT INTO public.material_relac (id_material_relac, material_relac) VALUES (6, 'Roble/PAD 1/1');
INSERT INTO public.material_relac (id_material_relac, material_relac) VALUES (7, 'Roble/PAD 75/25');
INSERT INTO public.material_relac (id_material_relac, material_relac) VALUES (8, 'MIX');
INSERT INTO public.material_relac (id_material_relac, material_relac) VALUES (9, 'TIPO A (CAT BAJO MUESTRA)');
INSERT INTO public.material_relac (id_material_relac, material_relac) VALUES (10, 'TIPO B (MUESTRA BAJO CAT)');
INSERT INTO public.material_relac (id_material_relac, material_relac) VALUES (11, 'PBD');
INSERT INTO public.material_relac (id_material_relac, material_relac) VALUES (12, 'PAD');
INSERT INTO public.material_relac (id_material_relac, material_relac) VALUES (13, 'ROBLE/LDPE 1:2');
INSERT INTO public.material_relac (id_material_relac, material_relac) VALUES (14, 'ROBLE/LDPE 1:1');
INSERT INTO public.material_relac (id_material_relac, material_relac) VALUES (15, 'ROBLE/LDPE 2:1');
INSERT INTO public.material_relac (id_material_relac, material_relac) VALUES (16, 'ROBLE/HDPE 1:2');
INSERT INTO public.material_relac (id_material_relac, material_relac) VALUES (17, 'ROBLE/HDPE 1:1');
INSERT INTO public.material_relac (id_material_relac, material_relac) VALUES (18, 'ROBLE/HDPE 2:1');
