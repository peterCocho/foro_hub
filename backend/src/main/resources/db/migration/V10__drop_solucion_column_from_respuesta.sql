-- Elimina la columna redundante 'solucion' de la tabla 'respuesta' para evitar duplicados con 'solucionado'
ALTER TABLE respuesta DROP COLUMN solucion;