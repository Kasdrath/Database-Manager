create table temp_pirolisis
(
    id_temp_pirol  bigserial
        constraint temp_pirolisis_pk
            primary key,
    temp_pirolisis bigint not null
);

alter table temp_pirolisis
    owner to postgres;

create unique index temp_pirolisis_temp_pirolisis_uindex
    on temp_pirolisis (temp_pirolisis);

INSERT INTO public.temp_pirolisis (id_temp_pirol, temp_pirolisis) VALUES (2, 550);
INSERT INTO public.temp_pirolisis (id_temp_pirol, temp_pirolisis) VALUES (3, 450);
INSERT INTO public.temp_pirolisis (id_temp_pirol, temp_pirolisis) VALUES (4, 350);
