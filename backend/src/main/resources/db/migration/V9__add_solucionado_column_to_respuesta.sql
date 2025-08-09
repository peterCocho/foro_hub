-- Agrega la columna 'solucionado' a la tabla 'respuesta'
ALTER TABLE respuesta
ADD COLUMN solucionado BOOLEAN NOT NULL DEFAULT FALSE;