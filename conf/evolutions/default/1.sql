# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table account (
  id                        bigint not null,
  username                  varchar(255),
  password                  varchar(255),
  type                      integer,
  constraint pk_account primary key (id))
;

create sequence account_seq;




# --- !Downs

drop table if exists account cascade;

drop sequence if exists account_seq;

