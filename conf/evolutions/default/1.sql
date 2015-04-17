# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table account (
  id                        bigint auto_increment not null,
  username                  varchar(255),
  password                  varchar(255),
  type                      integer,
  teams_id                  bigint,
  constraint pk_account primary key (id))
;

create table log (
  id                        bigint auto_increment not null,
  username                  varchar(255),
  date                      datetime,
  constraint pk_log primary key (id))
;

create table rate (
  id                        bigint auto_increment not null,
  users_id                  bigint,
  teams_id                  bigint,
  rate_rec_id               bigint,
  constraint pk_rate primary key (id))
;

create table rate_criteria (
  id                        bigint auto_increment not null,
  criteria_name             varchar(255),
  constraint pk_rate_criteria primary key (id))
;

create table rate_records (
  id                        bigint auto_increment not null,
  score                     integer,
  criteria_id               bigint,
  constraint pk_rate_records primary key (id))
;

create table team (
  id                        bigint auto_increment not null,
  team_name                 varchar(255),
  project_name              varchar(255),
  description               varchar(255),
  constraint pk_team primary key (id))
;

create table vote (
  id                        bigint auto_increment not null,
  users_id                  bigint,
  teams_id                  bigint,
  vote_rec_id               bigint,
  constraint pk_vote primary key (id))
;

create table vote_categories (
  id                        bigint auto_increment not null,
  categories_name           varchar(255),
  constraint pk_vote_categories primary key (id))
;

create table vote_records (
  id                        bigint auto_increment not null,
  categories_id             bigint,
  constraint pk_vote_records primary key (id))
;

alter table account add constraint fk_account_teams_1 foreign key (teams_id) references team (id) on delete restrict on update restrict;
create index ix_account_teams_1 on account (teams_id);
alter table rate add constraint fk_rate_users_2 foreign key (users_id) references account (id) on delete restrict on update restrict;
create index ix_rate_users_2 on rate (users_id);
alter table rate add constraint fk_rate_teams_3 foreign key (teams_id) references team (id) on delete restrict on update restrict;
create index ix_rate_teams_3 on rate (teams_id);
alter table rate add constraint fk_rate_rate_rec_4 foreign key (rate_rec_id) references rate_records (id) on delete restrict on update restrict;
create index ix_rate_rate_rec_4 on rate (rate_rec_id);
alter table rate_records add constraint fk_rate_records_criteria_5 foreign key (criteria_id) references rate_criteria (id) on delete restrict on update restrict;
create index ix_rate_records_criteria_5 on rate_records (criteria_id);
alter table vote add constraint fk_vote_users_6 foreign key (users_id) references account (id) on delete restrict on update restrict;
create index ix_vote_users_6 on vote (users_id);
alter table vote add constraint fk_vote_teams_7 foreign key (teams_id) references team (id) on delete restrict on update restrict;
create index ix_vote_teams_7 on vote (teams_id);
alter table vote add constraint fk_vote_vote_rec_8 foreign key (vote_rec_id) references vote_records (id) on delete restrict on update restrict;
create index ix_vote_vote_rec_8 on vote (vote_rec_id);
alter table vote_records add constraint fk_vote_records_categories_9 foreign key (categories_id) references vote_categories (id) on delete restrict on update restrict;
create index ix_vote_records_categories_9 on vote_records (categories_id);



# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table account;

drop table log;

drop table rate;

drop table rate_criteria;

drop table rate_records;

drop table team;

drop table vote;

drop table vote_categories;

drop table vote_records;

SET FOREIGN_KEY_CHECKS=1;

