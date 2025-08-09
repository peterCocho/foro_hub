-- =================================================================
-- V20: Restore the correct, application-generated password for the user 'pedro'
-- =================================================================
-- This migration corrects the password for 'pedro@example.com' by using
-- the original BCrypt hash that was proven to work, undoing the
-- incorrect hash provided in previous steps.

UPDATE users
SET password = '$2a$10$CMOiHtmnmgYxFVJg29qmkeRVZ/M950xiGFopop2k.9n.8VwpU2Eya'
WHERE email = 'pedro@example.com';