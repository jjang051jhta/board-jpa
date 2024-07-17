create table persistent_logins(
      username varchar2(100) not null,
      series varchar2(100) primary key,
      token varchar2(100)  not null,
      last_used timestamp not null
);
