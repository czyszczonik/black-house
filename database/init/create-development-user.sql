CREATE DATABASE blackhouse;
CREATE USER 'developer' IDENTIFIED BY 'password';
GRANT USAGE ON *.* TO 'developer'@localhost IDENTIFIED BY 'password';
GRANT ALL privileges ON `blackhouse`.* TO 'developer'@localhost;
FLUSH PRIVILEGES;
