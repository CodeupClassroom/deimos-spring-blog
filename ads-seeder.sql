use deimos_spring_db;

truncate table ads;

# seed ads
insert into ads (title, description) values
('B', 'This is the test ad 1 description'),
('A', 'This is the test ad 2 description'),
('D', 'This is the test ad 3 description'),
('C', 'This is the test ad 4 description'),
('Very Long Ad 5 Title', 'This is the test ad 5 description');

SELECT title FROM ads WHERE LENGTH(title) < 10