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

create table rate (
  id                        bigint auto_increment not null,
  users_id                  bigint,
  teams_id                  bigint,
  rate_cate_id              bigint,
  constraint pk_rate primary key (id))
;

create table rate_categories (
  id                        bigint auto_increment not null,
  score                     integer,
  topics_id                 bigint,
  constraint pk_rate_categories primary key (id))
;

create table team (
  id                        bigint auto_increment not null,
  team_name                 varchar(255),
  description               varchar(255),
  constraint pk_team primary key (id))
;

create table topic (
  id                        bigint auto_increment not null,
  topic_name                varchar(255),
  constraint pk_topic primary key (id))
;

create table vote (
  id                        bigint auto_increment not null,
  users_id                  bigint,
  teams_id                  bigint,
  vote_cate_id              bigint,
  constraint pk_vote primary key (id))
;

create table vote_categories (
  id                        bigint auto_increment not null,
  topics_id                 bigint,
  constraint pk_vote_categories primary key (id))
;

alter table account add constraint fk_account_teams_1 foreign key (teams_id) references team (id) on delete restrict on update restrict;
create index ix_account_teams_1 on account (teams_id);
alter table rate add constraint fk_rate_users_2 foreign key (users_id) references account (id) on delete restrict on update restrict;
create index ix_rate_users_2 on rate (users_id);
alter table rate add constraint fk_rate_teams_3 foreign key (teams_id) references team (id) on delete restrict on update restrict;
create index ix_rate_teams_3 on rate (teams_id);
alter table rate add constraint fk_rate_rate_cate_4 foreign key (rate_cate_id) references rate_categories (id) on delete restrict on update restrict;
create index ix_rate_rate_cate_4 on rate (rate_cate_id);
alter table rate_categories add constraint fk_rate_categories_topics_5 foreign key (topics_id) references topic (id) on delete restrict on update restrict;
create index ix_rate_categories_topics_5 on rate_categories (topics_id);
alter table vote add constraint fk_vote_users_6 foreign key (users_id) references account (id) on delete restrict on update restrict;
create index ix_vote_users_6 on vote (users_id);
alter table vote add constraint fk_vote_teams_7 foreign key (teams_id) references team (id) on delete restrict on update restrict;
create index ix_vote_teams_7 on vote (teams_id);
alter table vote add constraint fk_vote_vote_cate_8 foreign key (vote_cate_id) references vote_categories (id) on delete restrict on update restrict;
create index ix_vote_vote_cate_8 on vote (vote_cate_id);
alter table vote_categories add constraint fk_vote_categories_topics_9 foreign key (topics_id) references topic (id) on delete restrict on update restrict;
create index ix_vote_categories_topics_9 on vote_categories (topics_id);



# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table account;

drop table rate;

drop table rate_categories;

drop table team;

drop table topic;

drop table vote;

drop table vote_categories;

SET FOREIGN_KEY_CHECKS=1;

