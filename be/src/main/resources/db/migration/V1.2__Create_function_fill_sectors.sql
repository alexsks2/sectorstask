CREATE OR REPLACE FUNCTION fill_sectors(category_name VARCHAR, sectors text[])
    RETURNS VOID
    LANGUAGE plpgsql
AS
$$
DECLARE
each_sector VARCHAR;
BEGIN
    FOREACH each_sector IN ARRAY sectors
        LOOP
            INSERT
            INTO sector (name, parent_id)
            VALUES (each_sector, (SELECT id FROM sector WHERE name = category_name));
END LOOP;
end;
$$;