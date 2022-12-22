--liquibase formatted sql

--changeset dfuerst:1-1
CREATE sequence IF NOT EXISTS test;
