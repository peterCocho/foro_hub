-- Actualizar la contraseña de Pedro con "miContrasena123"
UPDATE usuario 
SET contrasena = '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDa' 
WHERE correo_electronico = 'pedro@example.com'; 