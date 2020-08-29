CREATE TABLE IF NOT EXISTS `team` (
  `team_id` int(11) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `fault_number` int(11) DEFAULT NULL,
  `pass_number` int(11) DEFAULT NULL,
  `team_name` varchar(100) NOT NULL,
  PRIMARY KEY (`team_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO team(
            team_id, created_at, updated_at, fault_number, pass_number, team_name)
    VALUES (1, '2020-08-28 05:34:09.565', '2020-08-28 05:34:09.565', 500, 5200 , 'FC-Barcelona');

INSERT INTO team(
            team_id, created_at, updated_at, fault_number, pass_number, team_name)
    VALUES (2, '2020-08-28 05:34:09.565', '2020-08-28 05:34:09.565', 600, 8200 , 'Real-Madrid');

CREATE TABLE IF NOT EXISTS `player` (
  `player_id` int(11) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `height` double NOT NULL,
  `nationality` varchar(80) NOT NULL,
  `player_name` varchar(150) NOT NULL,
  `right_handed` tinyint(1) DEFAULT '1',
  `team_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`player_id`),
  KEY `team_player_fkey` (`team_id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


INSERT INTO player(
            player_id, created_at, updated_at, height, nationality, player_name,
            right_handed, team_id)
    VALUES (1, '2020-08-28 05:34:09.565', '2020-08-28 05:34:09.565', 1.70, 'Argentino', 'Leonel Messi',
            false, 1);

INSERT INTO player(
            player_id, created_at, updated_at, height, nationality, player_name,
            right_handed, team_id)
    VALUES (2, '2020-08-28 05:34:09.565', '2020-08-28 05:34:09.565', 1.78, 'Español', 'Cristiano Ronaldo',
            true, 2);

CREATE TABLE IF NOT EXISTS `league` (
  `league_id` int(11) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `end_date` date NOT NULL,
  `game_number` int(11) NOT NULL,
  `league_name` varchar(100) NOT NULL,
  `start_date` date NOT NULL,
  PRIMARY KEY (`league_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `league_team` (
  `league_id` int(11) NOT NULL,
  `team_id` int(11) NOT NULL,
  KEY `FKrd24h3bourg4odyux7n0p2e2w` (`team_id`),
  KEY `FK7lw4tuad0laxyu4bm5bwov9t1` (`league_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO league(
            league_id, created_at, updated_at, end_date, game_number, league_name,
            start_date)
    VALUES (1, '2020-08-28 05:34:09.565', '2020-08-28 05:34:09.565', '2021-08-28', 30, 'Española', '2021-01-8');

INSERT INTO league(
            league_id, created_at, updated_at, end_date, game_number, league_name,
            start_date)
    VALUES (2, '2020-08-28 05:34:09.565', '2020-08-28 05:34:09.565', '2021-08-28', 32, 'Francesa', '2021-01-8');

INSERT INTO league(
            league_id, created_at, updated_at, end_date, game_number, league_name,
            start_date)
    VALUES (3, '2020-08-28 05:34:09.565', '2020-08-28 05:34:09.565', '2021-08-28', 32, 'Brazileña', '2021-01-8');

INSERT INTO league_team(
            league_id, team_id)
    VALUES (1, 1);

INSERT INTO league_team(
            league_id, team_id)
    VALUES (1, 2);

INSERT INTO league_team(
            league_id, team_id)
    VALUES (3, 2);

CREATE TABLE IF NOT EXISTS `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `rol` varchar(255) DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
