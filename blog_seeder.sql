use deimos_spring_db;

# seed users
insert into users (email, password, username) values
('bob@email.com', 'letmein', 'bob123');


# seed posts
insert into posts (title, body, user_id) values
('Post 1', 'This is the test post 1 body.', 1),
('Post 2', 'This is the test post 2 body.', 1),
('Post 3', 'This is the test post 3 body.', 1),
('Post 4', 'This is the test post 4 body.', 1),
('Post 5', 'This is the test post 5 body.', 1);
