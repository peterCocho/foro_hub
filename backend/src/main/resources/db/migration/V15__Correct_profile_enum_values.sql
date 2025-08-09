-- =================================================================
-- V18: Correct profile enum values to match the Java Enum
-- =================================================================
-- This data migration fixes inconsistencies between the string values
-- stored in the database and the expected values in the Profile.java enum.
-- It ensures that 'USER' is updated to 'ROLE_USER' and 'ADMIN' to 'ROLE_ADMIN'.

UPDATE user_profiles SET profile = 'ROLE_USER' WHERE profile = 'USER';
