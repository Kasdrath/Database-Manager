create table cond_exp
(
    id_cond_exp    bigserial
        constraint cond_exp_pk
            primary key,
    nombre_exp     varchar(100)     not null,
    catalizador    varchar(34)      not null
        constraint cond_exp_catalizador_catalizador_fk
            references catalizador (catalizador),
    temp_pirolisis bigint           not null
        constraint cond_exp_temp_pirolisis_temp_pirolisis_fk
            references temp_pirolisis (temp_pirolisis),
    tasa_calent    varchar(20)      not null
        constraint cond_exp_tasa_calent_tasa_calent_fk
            references tasa_calent (tasa_calent),
    material_relac varchar(50)      not null
        constraint cond_exp_material_relac_material_relac_fk
            references material_relac (material_relac),
    compq_name     varchar(50)      not null
        constraint cond_exp_componentequimico_compq_name_fk
            references componentequimico (compq_name),
    porcentarea    double precision not null
);

alter table cond_exp
    owner to postgres;

create unique index cond_exp_id_cond_exp_uindex
    on cond_exp (id_cond_exp);

INSERT INTO public.cond_exp (id_cond_exp, nombre_exp, catalizador, temp_pirolisis, tasa_calent, material_relac, compq_name, porcentarea) VALUES (4, 'E1', 'No Catalizador', 550, 'FAST', 'LDPE', '1-Hexeno', 6.133);
INSERT INTO public.cond_exp (id_cond_exp, nombre_exp, catalizador, temp_pirolisis, tasa_calent, material_relac, compq_name, porcentarea) VALUES (37, 'E1', 'No Catalizador', 550, 'SLOW', 'LDPE', 'Glucosa', 100);
