-- data.sql
-- Insert data into the users table
INSERT IGNORE INTO users (reference, password, first_name, last_name, nickname, email, is_active, created_at, updated_at, version)
VALUES
('17334fcc-8b65-11ed-a1eb-0242ac120002', MD5(RAND()), 'Leonardo', 'da Vinci', 'LeoVinci', 'leonardo.davinci@example.com', true, NOW(), NOW(), 0),
('17335260-8b65-11ed-a1eb-0242ac120002', MD5(RAND()), 'Galileo', 'Galilei', 'StarGazer', 'galileo.galilei@example.com', true, NOW(), NOW(), 0),
('17335396-8b65-11ed-a1eb-0242ac120002', MD5(RAND()), 'Dante', 'Alighieri', 'PoetDante', 'dante.alighieri@example.com', true, NOW(), NOW(), 0),
('173354c2-8b65-11ed-a1eb-0242ac120002', MD5(RAND()), 'Michelangelo', 'Buonarroti', 'ArtMaster', 'michelangelo.buonarroti@example.com', true, NOW(), NOW(), 0),
('173355da-8b65-11ed-a1eb-0242ac120002', MD5(RAND()), 'Enrico', 'Fermi', 'AtomEnrico', 'enrico.fermi@example.com', false, NOW(), NOW(), 0),
('173356f2-8b65-11ed-a1eb-0242ac120002', MD5(RAND()), 'Giuseppe', 'Garibaldi', 'HeroGiuseppe', 'giuseppe.garibaldi@example.com', true, NOW(), NOW(), 0),
('1733580a-8b65-11ed-a1eb-0242ac120002', MD5(RAND()), 'Guglielmo', 'Marconi', 'WaveWizard', 'guglielmo.marconi@example.com', true, NOW(), NOW(), 0),
('17335922-8b65-11ed-a1eb-0242ac120002', MD5(RAND()), 'Marco', 'Polo', 'VoyagerMarco', 'marco.polo@example.com', true, NOW(), NOW(), 0),
('17335a3a-8b65-11ed-a1eb-0242ac120002', MD5(RAND()), 'Giacomo', 'Puccini', 'OperaGiacomo', 'giacomo.puccini@example.com', true, NOW(), NOW(), 0),
('17335b52-8b65-11ed-a1eb-0242ac120002', MD5(RAND()), 'Antonio', 'Vivaldi', 'FourSeasons', 'antonio.vivaldi@example.com', true, NOW(), NOW(), 0),
('17335c6a-8b65-11ed-a1eb-0242ac120002', MD5(RAND()), 'Sandro', 'Botticelli', 'CanvasKing', 'sandro.botticelli@example.com', true, NOW(), NOW(), 0),
('17335d82-8b65-11ed-a1eb-0242ac120002', MD5(RAND()), 'Federico', 'Fellini', 'CinemaFred', 'federico.fellini@example.com', true, NOW(), NOW(), 0),
('17335e9a-8b65-11ed-a1eb-0242ac120002', MD5(RAND()), 'Sophia', 'Loren', 'ScreenQueen', 'sophia.loren@example.com', true, NOW(), NOW(), 0),
('17335fb2-8b65-11ed-a1eb-0242ac120002', MD5(RAND()), 'Maria', 'Montessori', 'EduMaria', 'maria.montessori@example.com', true, NOW(), NOW(), 0),
('173360ca-8b65-11ed-a1eb-0242ac120002', MD5(RAND()), 'Rita', 'Levi-Montalcini', 'NobelRita', 'rita.levi-montalcini@example.com', true, NOW(), NOW(), 0),
('173361e2-8b65-11ed-a1eb-0242ac120002', MD5(RAND()), 'Giorgio', 'Moroder', 'DiscoKing', 'giorgio.moroder@example.com', true, NOW(), NOW(), 0),
('173362fa-8b65-11ed-a1eb-0242ac120002', MD5(RAND()), 'Luciano', 'Pavarotti', 'TenorLuciano', 'luciano.pavarotti@example.com', true, NOW(), NOW(), 0),
('17336412-8b65-11ed-a1eb-0242ac120002', MD5(RAND()), 'Andrea', 'Palladio', 'ArchMaster', 'andrea.palladio@example.com', true, NOW(), NOW(), 0),
('1733652a-8b65-11ed-a1eb-0242ac120002', MD5(RAND()), 'Giorgio', 'Vasari', 'ArtGiorgio', 'giorgio.vasari@example.com', true, NOW(), NOW(), 0),
('17336642-8b65-11ed-a1eb-0242ac120002', MD5(RAND()), 'Alessandro', 'Volta', 'VoltAlessandro', 'alessandro.volta@example.com', true, NOW(), NOW(), 0),
('1733675a-8b65-11ed-a1eb-0242ac120002', MD5(RAND()), 'Donatello', 'di Niccolò', 'SculptorDon', 'donatello.diniccolo@example.com', true, NOW(), NOW(), 0);


-- Insert data into the roles table with explicit IDs
INSERT IGNORE INTO roles (id, reference, name, created_at, updated_at, version)
VALUES
(1, '17334fdd-8b65-11ed-a1eb-0242ac120002', 'ROLE_ADMIN', NOW(), NOW(), 0),
(2, '17334fde-8b65-11ed-a1eb-0242ac120002', 'ROLE_USER', NOW(), NOW(), 0);


-- Insert data into the users_roles table
INSERT IGNORE INTO users_roles (users_id, roles_id)
VALUES
((SELECT id FROM users WHERE reference = '17334fcc-8b65-11ed-a1eb-0242ac120002'), (SELECT id FROM roles WHERE reference = '17334fdd-8b65-11ed-a1eb-0242ac120002')), -- Leonardo da Vinci as ADMIN
((SELECT id FROM users WHERE reference = '17335260-8b65-11ed-a1eb-0242ac120002'), (SELECT id FROM roles WHERE reference = '17334fde-8b65-11ed-a1eb-0242ac120002')), -- Galileo Galilei as USER
((SELECT id FROM users WHERE reference = '17335396-8b65-11ed-a1eb-0242ac120002'), (SELECT id FROM roles WHERE reference = '17334fde-8b65-11ed-a1eb-0242ac120002')), -- Dante Alighieri as USER
((SELECT id FROM users WHERE reference = '173354c2-8b65-11ed-a1eb-0242ac120002'), (SELECT id FROM roles WHERE reference = '17334fde-8b65-11ed-a1eb-0242ac120002')), -- Michelangelo Buonarroti as USER
((SELECT id FROM users WHERE reference = '173355da-8b65-11ed-a1eb-0242ac120002'), (SELECT id FROM roles WHERE reference = '17334fde-8b65-11ed-a1eb-0242ac120002')), -- Enrico Fermi as USER
((SELECT id FROM users WHERE reference = '173356f2-8b65-11ed-a1eb-0242ac120002'), (SELECT id FROM roles WHERE reference = '17334fde-8b65-11ed-a1eb-0242ac120002')), -- Giuseppe Garibaldi as USER
((SELECT id FROM users WHERE reference = '1733580a-8b65-11ed-a1eb-0242ac120002'), (SELECT id FROM roles WHERE reference = '17334fde-8b65-11ed-a1eb-0242ac120002')), -- Guglielmo Marconi as USER
((SELECT id FROM users WHERE reference = '17335922-8b65-11ed-a1eb-0242ac120002'), (SELECT id FROM roles WHERE reference = '17334fde-8b65-11ed-a1eb-0242ac120002')), -- Marco Polo as USER
((SELECT id FROM users WHERE reference = '17335a3a-8b65-11ed-a1eb-0242ac120002'), (SELECT id FROM roles WHERE reference = '17334fde-8b65-11ed-a1eb-0242ac120002')), -- Giacomo Puccini as USER
((SELECT id FROM users WHERE reference = '17335b52-8b65-11ed-a1eb-0242ac120002'), (SELECT id FROM roles WHERE reference = '17334fde-8b65-11ed-a1eb-0242ac120002')), -- Antonio Vivaldi as USER
((SELECT id FROM users WHERE reference = '17335c6a-8b65-11ed-a1eb-0242ac120002'), (SELECT id FROM roles WHERE reference = '17334fde-8b65-11ed-a1eb-0242ac120002')), -- Sandro Botticelli as USER
((SELECT id FROM users WHERE reference = '17335d82-8b65-11ed-a1eb-0242ac120002'), (SELECT id FROM roles WHERE reference = '17334fde-8b65-11ed-a1eb-0242ac120002')), -- Federico Fellini as USER
((SELECT id FROM users WHERE reference = '17335e9a-8b65-11ed-a1eb-0242ac120002'), (SELECT id FROM roles WHERE reference = '17334fde-8b65-11ed-a1eb-0242ac120002')), -- Sophia Loren as USER
((SELECT id FROM users WHERE reference = '17335fb2-8b65-11ed-a1eb-0242ac120002'), (SELECT id FROM roles WHERE reference = '17334fde-8b65-11ed-a1eb-0242ac120002')), -- Maria Montessori as USER
((SELECT id FROM users WHERE reference = '173360ca-8b65-11ed-a1eb-0242ac120002'), (SELECT id FROM roles WHERE reference = '17334fde-8b65-11ed-a1eb-0242ac120002')), -- Rita Levi-Montalcini as USER
((SELECT id FROM users WHERE reference = '173361e2-8b65-11ed-a1eb-0242ac120002'), (SELECT id FROM roles WHERE reference = '17334fde-8b65-11ed-a1eb-0242ac120002')), -- Giorgio Moroder as USER
((SELECT id FROM users WHERE reference = '173362fa-8b65-11ed-a1eb-0242ac120002'), (SELECT id FROM roles WHERE reference = '17334fde-8b65-11ed-a1eb-0242ac120002')), -- Luciano Pavarotti as USER
((SELECT id FROM users WHERE reference = '17336412-8b65-11ed-a1eb-0242ac120002'), (SELECT id FROM roles WHERE reference = '17334fde-8b65-11ed-a1eb-0242ac120002')), -- Andrea Palladio as USER
((SELECT id FROM users WHERE reference = '1733652a-8b65-11ed-a1eb-0242ac120002'), (SELECT id FROM roles WHERE reference = '17334fde-8b65-11ed-a1eb-0242ac120002')), -- Giorgio Vasari as USER
((SELECT id FROM users WHERE reference = '17336642-8b65-11ed-a1eb-0242ac120002'), (SELECT id FROM roles WHERE reference = '17334fde-8b65-11ed-a1eb-0242ac120002')), -- Alessandro Volta as USER
((SELECT id FROM users WHERE reference = '1733675a-8b65-11ed-a1eb-0242ac120002'), (SELECT id FROM roles WHERE reference = '17334fde-8b65-11ed-a1eb-0242ac120002')); -- Donatello di Niccolò as USER
