INSERT INTO topico (titulo, mensaje, fecha_creacion, curso_id, autor_id)
VALUES
-- Tópicos para Spring Boot Pro
('Filtros en Spring Security', '¿Cómo configurar OncePerRequestFilter para validar el JWT?', CURRENT_TIMESTAMP, 2, 2),
('Autenticación personalizada', '¿Es recomendable extender UsernamePasswordAuthenticationFilter?', CURRENT_TIMESTAMP, 2, 3),
('DTOs con Java Records', '¿Cómo usar records para crear respuestas limpias y seguras?', CURRENT_TIMESTAMP, 2, 2),

-- Tópicos para Dominando SQL
('Funciones agregadas avanzadas', '¿Cómo aprovechar GROUPING SETS y ROLLUP?', CURRENT_TIMESTAMP, 2, 3),
('Índices en base de datos', '¿Cómo afectan los índices compuestos al rendimiento?', CURRENT_TIMESTAMP, 2, 2),
('Optimización de JOINs', '¿Cómo elegir entre INNER, LEFT y CROSS JOIN según el caso?', CURRENT_TIMESTAMP, 2, 3);
