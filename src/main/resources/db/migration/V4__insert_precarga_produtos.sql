INSERT INTO tlproducao.produtos (id_produto,
                                categoria, 
                                nome,
					            tempo_preparo)
select 1 id,
       0 AS categoria,
       'X TUDÃO' AS nome,
       30 AS tempo_preparo
UNION ALL       
select 2 id,
       1 AS categoria,
       'BATATA FRITA COMPLETA' AS nome,
       15 AS tempo_preparo
UNION ALL       
select 3 id,
       2 AS categoria,
       'COCA COLA 2 LITROS' AS nome,
       0 AS tempo_preparo
UNION ALL       
select 4 id,
       3 AS categoria,
       'CASCATA KINDER' AS nome,
       30 AS tempo_preparo
