-- Asegura que los perfiles básicos existan antes de usarlos.
-- Se usa INSERT IGNORE para evitar errores si los perfiles ya fueron creados en una migración anterior.
INSERT IGNORE INTO perfil (nombre) VALUES ('ROLE_USER'), ('ROLE_ADMIN');

-- Inserta los nuevos usuarios Juan y Daniel
-- No especificamos el ID, la base de datos lo asignará automáticamente (serán 4 y 5)
INSERT INTO usuario (nombre, correo_electronico, contrasena)
VALUES
  ('Juan', 'juan@example.com', '$2a$12$4Dd1M2FvskevDChspMfmve8kRHH5UCU4CgyGuHtPqs0tGMVOSQgKe'),
  ('Daniel', 'daniel@example.com', '$2a$12$Lga30QejERGpJVpmZEV1LeHEiu7TOwFlIqU9tj7ojLHvQMGA9XABW');

-- Asigna los perfiles a todos los usuarios para que la migración sea completa.
-- Se usa INSERT IGNORE para no fallar si las asignaciones ya existen.
INSERT IGNORE INTO usuario_perfil (usuario_id, perfil_id) VALUES
  (2, 1), -- Pedro (id=2) -> ROLE_USER (id=1)
  (3, 1), -- Maria (id=3) -> ROLE_USER (id=1)
  (4, 1), -- Juan (id=4) -> ROLE_USER (id=1)
  (5, 2); -- Daniel (id=5) -> ROLE_ADMIN (id=2)