-- =================================================================
-- V15: Assign a default profile to existing users
-- =================================================================
-- This data migration script ensures that all users created before
-- the introduction of the user_profiles table are assigned a
-- default 'USER' profile.

INSERT INTO user_profiles (user_id, profile)
SELECT id, 'USER' FROM users
WHERE id NOT IN (SELECT user_id FROM user_profiles);