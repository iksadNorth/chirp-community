insert into site_user (email, password, nickname, role) values
('junmop01@gmail.com', '1234', 'junmo01', 'ROLE_USER'),
('junmop02@gmail.com', '1234', 'junmo02', 'ROLE_USER')
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
('title09', 'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa - content09', 5, 1, 10)
;