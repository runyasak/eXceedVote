# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table account (
  id                        bigint auto_increment not null,
  username                  varchar(255),
  password                  varchar(255),
  type                      integer,
  constraint pk_account primary key (id))
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table account;

SET FOREIGN_KEY_CHECKS=1;

