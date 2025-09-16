-- TDM demo: tabla de credenciales para pruebas
-- PostgreSQL
CREATE TABLE IF NOT EXISTS public.test_credentials (
    id SERIAL PRIMARY KEY,
    username VARCHAR(80) NOT NULL,
    password VARCHAR(120) NOT NULL,
    kind VARCHAR(20) NOT NULL -- valid | invalid | locked
);
INSERT INTO public.test_credentials (username, password, kind) VALUES
    ('standard_user', 'secret_sauce', 'valid'),
    ('locked_out_user', 'secret_sauce', 'locked'),
    ('standard_user', 'wrong_pass', 'invalid');

-- SQL Server
/*
IF OBJECT_ID('dbo.test_credentials','U') IS NULL
    CREATE TABLE dbo.test_credentials(
        id INT IDENTITY(1,1) PRIMARY KEY,
        username NVARCHAR(80) NOT NULL,
        password NVARCHAR(120) NOT NULL,
        kind NVARCHAR(20) NOT NULL
    );
INSERT INTO dbo.test_credentials (username, password, kind) VALUES
    ('standard_user','secret_sauce','valid'),
    ('locked_out_user','secret_sauce','locked'),
    ('standard_user','wrong_pass','invalid');
*/
