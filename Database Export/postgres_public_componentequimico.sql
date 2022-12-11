create table componentequimico
(
    compq_cas    varchar(30) not null
        primary key,
    compq_name   varchar(50) not null,
    familia_name varchar(30) not null
        constraint componentequimico_familia_familia_name_fk
            references familia (familia_name)
);

alter table componentequimico
    owner to postgres;

create unique index componentequimico_compq_name_uindex
    on componentequimico (compq_name);

INSERT INTO public.componentequimico (compq_cas, compq_name, familia_name) VALUES ('592-41-6', '1-Hexeno', 'Hidrocarburos');
INSERT INTO public.componentequimico (compq_cas, compq_name, familia_name) VALUES ('50-99-7', 'Glucosa', 'Sugars');
