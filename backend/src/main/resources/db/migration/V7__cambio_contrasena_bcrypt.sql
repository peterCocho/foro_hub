-- Actualizar la contraseña de Pedro con el hash BCrypt correcto de "miContrasena123"
UPDATE usuario
SET contrasena = '$2a$10$CMOiHtmnmgYxFVJg29qmkeRVZ/M950xiGFopop2k.9n.8VwpU2Eya'
WHERE correo_electronico = 'pedro@example.com';