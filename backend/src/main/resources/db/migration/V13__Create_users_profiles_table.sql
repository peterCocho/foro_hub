-- =================================================================
-- V13: Create user_profiles table for user roles
-- =================================================================
-- This table supports a many-to-many-like relationship between users
-- and their profiles (roles) using an @ElementCollection in the User entity.

CREATE TABLE user_profiles (
    user_id BIGINT NOT NULL,
    profile VARCHAR(255) NOT NULL,
    PRIMARY KEY (user_id, profile),
    CONSTRAINT fk_user_profiles_to_users
        FOREIGN KEY (user_id)
        REFERENCES users(id)
);