insert into site_user (email, password, nickname, role) values
('junmop01@gmail.com', '1234', 'junmo01', 'ROLE_USER'),
('junmop02@gmail.com', '1234', 'junmo02', 'ROLE_USER'),
('admin', '1234', 'admin', 'ROLE_PRIME_ADMIN'),
('bdmin', '1234', 'bdmin', 'ROLE_BOARD_ADMIN')
;


insert into board (name) values
('board01'),
('board02'),
('board03'),
('board04'),
('board05')
;

INSERT INTO `site_user` (email, password, nickname) VALUES
('user1', '1234', 'iksadnorth'),
('user2', '1234', 'iksadsouth'),
('user3', '1234', 'junmo');

INSERT INTO `site_user` (email, password, nickname, role) VALUES
('admin', '1234', 'prime admin', 'ROLE_PRIME_ADMIN');

INSERT INTO `article` (title, content, board_id, writer_id) VALUES
('article1', '<h1>문제 해결1</h1>', 1, 1),
('article2', '<h1>문제 해결2</h1>', 2, 1),
('article1', '<h2>문제 해결1</h2>', 1, 2),
('article2', '<h2>문제 해결2</h2>', 2, 2),
('article1', '<h3>문제 해결1</h3>', 1, 3),
('article2', '<h3>문제 해결2</h3>', 2, 3);

insert into article (title, content, board_id, writer_id, views) values
('title01', 'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa - content01', 1, 1, 22),
('title02', 'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa - content02', 1, 2, 11),
('title03', 'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa - content03', 2, 1, 12),
('title04', 'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa - content04', 2, 1, 22),
('title05', 'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa - content05', 3, 2, 9),
('title06', 'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa - content06', 4, 1, 2),
('title07', 'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa - content07', 5, 1, 7),
('title08', 'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa - content08', 5, 1, 8),
('title09', 'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa - content09', 5, 1, 10)
;