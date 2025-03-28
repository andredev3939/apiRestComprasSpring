ALTER TABLE product ADD COLUMN active BOOLEAN; -- Coluna de ativo e inativo para delete
UPDATE product SET active = true; -- Alterações no banco devem ser feitas com cautela, devendo consultar o tech lead, dba ou gestor de negócios
