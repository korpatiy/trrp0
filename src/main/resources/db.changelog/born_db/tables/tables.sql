create table t_discipline
(
    disc_id              bigint generated by default as identity
        constraint t_discipline_pkey
            primary key,
    disc_name            varchar(100) not null,
    number_of_fire_lines bigint       not null,
    fine_unit            bigint       not null
);

create table t_track
(
    track_id bigint generated by default as identity
        constraint t_track_pkey
            primary key,
    location varchar(100) not null,
    length   bigint       not null
);

create table t_country
(
    country_id   bigint generated by default as identity
        constraint t_country_pkey
            primary key,
    country_name varchar(100) not null unique
);


create table t_city
(
    city_id    bigint generated by default as identity
        constraint t_city_pkey
            primary key,
    city_name  varchar(100) not null,
    country_id bigint       not null
        constraint fk_city_country_id_t_city
            references t_country,
    unique (city_id, country_id)
);

create table t_championship
(
    champ_id   bigint generated by default as identity
        constraint t_championship_pkey
            primary key,
    champ_name varchar(100) not null unique,
    start_date date         not null,
    end_date   date         not null
);


create table t_stage
(
    stage_id   bigint generated by default as identity
        constraint t_stage_pkey
            primary key,
    stage_name varchar(100) not null,
    city_id    bigint       not null
        constraint fk_stage_city_id_t_city
            references t_city,
    champ_id   bigint       not null
        constraint fk_stage_champ_id_t_championship
            references t_championship,
    start_date date         not null,
    end_date   date         not null
);


create table t_race
(
    race_id    bigint generated by default as identity
        constraint t_race_pkey
            primary key,
    stage_id   bigint      not null
        constraint fk_race_stage_id_t_stage
            references t_stage,
    track_id   bigint      not null
        constraint fk_race_track_id_t_track
            references t_track,
    disc_id    bigint      not null
        constraint fk_race_disc_id_t_discipline
            references t_discipline,
    sex        varchar(50) not null,
    date       date        not null,
    start_time timestamp   not null
);

create table t_coach
(
    coach_id   bigint generated by default as identity
        constraint t_coach_pkey
            primary key,
    fio        varchar(200) not null,
    experience bigint       not null,
    country_id bigint       not null
        constraint fk_coach_country_id_t_country
            references t_country
);

create table t_team
(
    team_id    bigint generated by default as identity
        constraint t_team_pkey
            primary key,
    team_name  varchar(100) not null unique,
    country_id bigint       not null
        constraint fk_team_country_id_t_country
            references t_country
);


create table t_season
(
    season_id   bigint generated by default as identity
        constraint t_season_pkey
            primary key,
    season_name varchar(100) not null
);

create table t_sportsman
(
    sportsman_id  bigint generated by default as identity
        constraint t_sportsman_pkey
            primary key,
    fio           varchar(200) not null,
    date_of_birth date         not null
);

create table t_sportsman_season
(
    sportsman_id bigint not null
        constraint fk_sportsman_season_sportsman_id_t_sportsman
            references t_sportsman,
    season_id    bigint not null
        constraint fk_sportsman_season_season_id_t_season
            references t_season,
    team_id      bigint not null
        constraint fk_sportsman_season_team_id_t_team
            references t_team,
    coach_id     bigint not null
        constraint fk_sportsman_season_coach_id_t_coach
            references t_coach,
    constraint t_sportsman_season_pkey
        primary key (season_id, sportsman_id)
);


create table t_result
(
    race_id      bigint    not null
        constraint fk_result_race_id_t_race
            references t_race,
    sportsman_id bigint    not null
        constraint fk_result_sportsman_id_t_sportsman
            references t_sportsman,
    season_id    bigint    not null
        constraint fk_result_season_id_t_season
            references t_season,
    start_number bigint    not null,
    start_time   timestamp not null,
    finish_time  timestamp not null,
    firing_time  bigint    not null,
    misses       bigint    not null,
    fine         bigint,
    result_time  timestamp not null,
    result_place bigint    not null,
    constraint t_result_pkey
        primary key (season_id, race_id, sportsman_id)
);

