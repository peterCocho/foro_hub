-- =================================================================
-- V13: Corrección del mapeo de datos para categorías
-- =================================================================
UPDATE courses
    SET category = 'BASE_DE_DATOS'
-- Usamos una comparación exacta (ignorando mayúsculas/minúsculas) para mayor precisión.
WHERE LOWER(category) = 'bases de datos';

UPDATE topics
    SET status = 'NO_RESPONSE'
WHERE LOWER(status) = 'activo';

UPDATE topics
    SET status = 'SOLVED'
WHERE LOWER(status) = 'solucionado';