CREATE TABLE users ( -- Em Postgre, user é uma "tabela reservada"
    id TEXT PRIMARY KEY UNIQUE NOT NULL,
    login TEXT NOT NULL UNIQUE, -- Pode ser um usuário ou um e-mail
    senha TEXT NOT NULL,
    funcao TEXT NOT NULL -- Tipo
)