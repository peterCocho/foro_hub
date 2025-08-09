-- =================================================================
-- V21: Create the initial administrator user (Simple Version)
-- =================================================================
-- This script performs two direct insertions:
-- 1. Creates the user 'admin@foro.hub' with a pre-hashed password.
-- 2. Assigns the 'ROLE_ADMIN' and 'ROLE_USER' roles to that new user.

-- The password hash corresponds to 'admin123'

-- Step 1: Insert the new administrator user directly into the 'users' table.
INSERT INTO users (name, email, password)
VALUES ('Admin User', 'admin@foro.hub', '$2a$12$WIAXFwlOUq8Cj2Gi4pM4T.eTyfhNL3PffzHkgIEsOljUKTeJbGYey');

-- Step 2: Insert the profiles for the user we just created.
-- We use a subquery to find the ID of the user by their unique email.
-- We use LAST_INSERT_ID() to get the ID of the user created in the previous step. This is more efficient.
INSERT INTO user_profiles (user_id, profile)
VALUES
    (LAST_INSERT_ID(), 'ROLE_ADMIN'),
    (LAST_INSERT_ID(), 'ROLE_USER');