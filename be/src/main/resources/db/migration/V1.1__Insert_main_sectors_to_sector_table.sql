-- Manufacturing
WITH manufacturing AS (
INSERT
INTO sector (name)
VALUES ('Manufacturing') RETURNING id)
INSERT
INTO sector (name, parent_id)
VALUES ('Construction materials', (SELECT id FROM manufacturing)),
    ('Electronics and Optics', (SELECT id FROM manufacturing)),
    ('Food and Beverage', (SELECT id FROM manufacturing)),
    ('Furniture', (SELECT id FROM manufacturing)),
    ('Machinery', (SELECT id FROM manufacturing)),
    ('Metalworking', (SELECT id FROM manufacturing)),
    ('Plastic and Rubber', (SELECT id FROM manufacturing)),
    ('Printing', (SELECT id FROM manufacturing)),
    ('Textile and Clothing', (SELECT id FROM manufacturing)),
    ('Wood', (SELECT id FROM manufacturing));


-- Other
WITH other AS (
INSERT
INTO sector (name)
VALUES ('Other') RETURNING id)
INSERT
INTO sector (name, parent_id)
VALUES ('Creative industries', (SELECT id FROM other)),
    ('Energy technology', (SELECT id FROM other)),
    ('Environment', (SELECT id FROM other));

-- Service
WITH service AS (
INSERT
INTO sector (name)
VALUES ('Service') RETURNING id)
INSERT
INTO sector (name, parent_id)
VALUES ('Business services', (SELECT id FROM service)),
    ('Engineering', (SELECT id FROM service)),
    ('Information Technology and Telecommunications', (SELECT id FROM service)),
    ('Tourism', (SELECT id FROM service)),
    ('Translation services', (SELECT id FROM service)),
    ('Transport and Logistics', (SELECT id FROM service));