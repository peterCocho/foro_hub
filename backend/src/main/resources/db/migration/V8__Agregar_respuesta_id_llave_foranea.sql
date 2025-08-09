-- Agrega columna respuesta_id a la tabla Topico y su restricción de clave foránea
ALTER TABLE Topico
ADD COLUMN respuesta_id BIGINT;

ALTER TABLE Topico
ADD CONSTRAINT fk_Topico_Respuesta
FOREIGN KEY (respuesta_id)
REFERENCES Respuesta(id)
ON DELETE SET NULL
ON UPDATE CASCADE;


