use deimos_spring_db;

# seed post_details
insert into post_details (is_awesome, history_of_post, topic_description) values
(true, 'Post 1 history', 'Topic for 1'),
(false, 'Post 2 history', 'Topic for 2'),
(true, 'Post 3 history', 'Topic for 3'),
(false, 'Post 4 history', 'Topic for 4'),
(true, 'Post 5 history', 'Topic for 5');

# seed posts
insert into posts (title, body, post_details_id) values
('Post 1', 'This is the test post 1 body.', 1),
('Post 2', 'This is the test post 2 body.', 2),
('Post 3', 'This is the test post 3 body.', 3),
('Post 4', 'This is the test post 4 body.', 4),
('Post 5', 'This is the test post 5 body.', 5);

