do
'
declare
begin
   IF (NOT EXISTS(select * from SODA where id=''0a818933-087d-47f2-ad83-2f986ed087eb'')) THEN
       INSERT INTO SODA(id, soda_name, soda_style, created_date, last_modified_date, min_on_hand, quantity_to_ware, price, upc, version) VALUES (''0a818933-087d-47f2-ad83-2f986ed087eb'',''Kızılay'',''LEMON'',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,11,200,12.99,3370100000,1);
       INSERT INTO SODA(id, soda_name, soda_style, created_date, last_modified_date, min_on_hand, quantity_to_ware, price, upc, version) VALUES (''a712d914-61ea-4623-8bd0-32c0f6545bfd'',''Beypazarı'',''SIMPLE'',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,11,200,13.99,3370100001,1);
       INSERT INTO SODA(id, soda_name, soda_style, created_date, last_modified_date, min_on_hand, quantity_to_ware, price, upc, version) VALUES (''026cc3c8-3a0c-4083-a05b-e908048c1b08'',''Sırma'',''CHERRY'',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,11,200,10.99,3370100002,1);
   END IF;
end;
' LANGUAGE PLPGSQL;
