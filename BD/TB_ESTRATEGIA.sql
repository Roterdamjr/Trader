--------------------------------------------------------
--  Arquivo criado - sábado-outubro-19-2019   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table TB_ESTRATEGIA
--------------------------------------------------------
begin
DELETE FROM tb_estrategia;
Insert into TRADER.TB_ESTRATEGIA (ATIVO,VL_COMPRA,STOP,GAIN,GAIN_PARC,SQ_ESTRATEGIA,QUANTIDADE,RISCO) values ('SULA11F','48,34','45,56','55,62','50,64','92','40',null);
Insert into TRADER.TB_ESTRATEGIA (ATIVO,VL_COMPRA,STOP,GAIN,GAIN_PARC,SQ_ESTRATEGIA,QUANTIDADE,RISCO) values ('TIET11','12,3','11,53','14,09','13,07','93','99',null);
Insert into TRADER.TB_ESTRATEGIA (ATIVO,VL_COMPRA,STOP,GAIN,GAIN_PARC,SQ_ESTRATEGIA,QUANTIDADE,RISCO) values ('UGPA3','19,43','18,07','23,51','20,79','94','200',null);
Insert into TRADER.TB_ESTRATEGIA (ATIVO,VL_COMPRA,STOP,GAIN,GAIN_PARC,SQ_ESTRATEGIA,QUANTIDADE,RISCO) values ('SAPR4','16,77','16,42','18,56','17,34','91','200',null);
Insert into TRADER.TB_ESTRATEGIA (ATIVO,VL_COMPRA,STOP,GAIN,GAIN_PARC,SQ_ESTRATEGIA,QUANTIDADE,RISCO) values ('VULC3','7,85','7,75','9,5','8,4','95','300',null);
Insert into TRADER.TB_ESTRATEGIA (ATIVO,VL_COMPRA,STOP,GAIN,GAIN_PARC,SQ_ESTRATEGIA,QUANTIDADE,RISCO) values ('VVAR3','8','7,45','9,65','8,55','96','200',null);
--------------------------------------------------------
--  DDL for Index TB_NEGOCIACAO_PK
--------------------------------------------------------
commit;
end;