use deimos_spring_db;

truncate table posts;

# seed posts
insert into posts (title, body) values
('Post 1', 'This is the test post 1 body.'),
('Post 2', 'This is the test post 2 body.'),
('Post 3', 'This is the test post 3 body.'),
('Post 4', 'This is the test post 4 body.'),
('Post 5', 'This is the test post 5 body.');
