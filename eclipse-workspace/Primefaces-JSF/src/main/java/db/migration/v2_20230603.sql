alter table titulo ADD COLUMN campo_teste character varying(255);
update titulo set campo_teste = 'entendendo o flyway';