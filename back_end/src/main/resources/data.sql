insert into site_user (email, password, nickname, role) values
('junmop01@gmail.com', '1234', 'junmo01', 'ROLE_USER'),
('junmop02@gmail.com', '1234', 'junmo02', 'ROLE_USER'),
('admin', '1234', 'admin', 'ROLE_PRIME_ADMIN'),
('bdmin', '1234', 'bdmin', 'ROLE_BOARD_ADMIN'),
('user1', '1234', 'iksadnorth', 'ROLE_USER'),
('user2', '1234', 'iksadsouth', 'ROLE_USER'),
('user3', '1234', 'junmo', 'ROLE_USER'),
('iksadnorth@gmail.com', '1234', 'iksad', 'ROLE_USER')
;

insert into board (name) values
('board01'),
('board02'),
('board03'),
('board04'),
('board05')
;

insert into article (title, content, board_id, writer_id, views) values
('title01', 'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa - content01', 1, 1, 22),
('title02', 'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa - content02', 1, 2, 11),
('title03', 'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa - content03', 2, 1, 12),
('title04', 'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa - content04', 2, 1, 22),
('title05', 'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa - content05', 3, 2, 9),
('title06', 'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa - content06', 4, 1, 2),
('title07', 'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa - content07', 5, 1, 7),
('title08', 'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa - content08', 5, 1, 8),
('title09', 'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa - content09', 5, 1, 10),
('article1', '<h1>문제 해결1</h1>', 1, 1, 0),
('article2', '<h1>문제 해결2</h1>', 2, 1, 0),
('article1', '<h2>문제 해결1</h2>', 1, 2, 0),
('article2', '<h2>문제 해결2</h2>', 2, 2, 0),
('article1', '<h3>문제 해결1</h3>', 1, 3, 0),
('article2', '<h3>문제 해결2</h3>', 2, 3, 0)
;


insert into article_comment (content, article_id, writer_id) values
('comment 01', 1, 1),
('comment 02', 2, 1),
('comment 03', 3, 1),
('comment 04', 4, 1),
('comment 05', 1, 2),
('comment 06', 2, 2),
('comment 07', 3, 2),
('comment 08', 4, 2)
;

insert into article_likes (arg, user_id, article_id) values
(1, 1, 1),
(1, 1, 1),
(1, 2, 1),
(0, 2, 1),
(0, 3, 1),
(-1, 3, 1),

(1, 1, 2),
(1, 1, 2),
(1, 2, 2),
(0, 2, 2),
(0, 3, 2),
(-1, 3, 2),

(1, 1, 3),
(1, 1, 3),
(1, 2, 3),
(0, 2, 3),
(0, 3, 3),
(-1, 3, 3)
;

insert into article_comment_likes (arg, user_id, article_comment_id) values
(1, 1, 1),
(1, 1, 1),
(1, 2, 1),
(0, 2, 1),
(0, 3, 1),
(-1, 3, 1),

(1, 1, 2),
(1, 1, 2),
(1, 2, 2),
(0, 2, 2),
(0, 3, 2),
(-1, 3, 2),

(1, 1, 3),
(1, 1, 3),
(1, 2, 3),
(0, 2, 3),
(0, 3, 3),
(-1, 3, 3)
;