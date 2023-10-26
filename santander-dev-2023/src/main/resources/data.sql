INSERT INTO TB_ACCOUNT (number, agency, balance, additional_limit)
VALUES ('01.097954-4', '2030', 624.12, 1000.00);

INSERT INTO TB_CARD (number, available_limit)
VALUES ('xxxx xxxx xxxx 1111', 2000.00);

INSERT INTO TB_FEATURE (icon, description) VALUES ('https://digitalinnovationone.github.io/santander-dev-week-2023-api/icons/pix.svg', 'PIX');
INSERT INTO TB_FEATURE (icon, description) VALUES ('https://digitalinnovationone.github.io/santander-dev-week-2023-api/icons/pay.svg', 'Pagar');
INSERT INTO TB_FEATURE (icon, description) VALUES ('https://digitalinnovationone.github.io/santander-dev-week-2023-api/icons/transfer.svg', 'Transferir');
INSERT INTO TB_FEATURE (icon, description) VALUES ('https://digitalinnovationone.github.io/santander-dev-week-2023-api/icons/account.svg', 'Conta Corrente');
INSERT INTO TB_FEATURE (icon, description) VALUES ('https://digitalinnovationone.github.io/santander-dev-week-2023-api/icons/cards.svg', 'Cartões');

INSERT INTO TB_NEWS (icon, description) VALUES ('https://digitalinnovationone.github.io/santander-dev-week-2023-api/icons/insurance.svg', 'Santander Seguro Casa, seu faz-tudo. Mais de 50 serviços pra você. Confira!');
INSERT INTO TB_NEWS (icon, description) VALUES ('https://digitalinnovationone.github.io/santander-dev-week-2023-api/icons/credit.svg','O Santander tem soluções de crédito sob medida pra você. Confira!');

INSERT INTO TB_USER (name, card_id, account_id) VALUES ('Alice', 1, 1);

INSERT INTO TB_USER_FEATURES (features_id, tb_user_id) VALUES (1, 1);
INSERT INTO TB_USER_FEATURES (features_id, tb_user_id) VALUES (2, 1);
INSERT INTO TB_USER_FEATURES (features_id, tb_user_id) VALUES (3, 1);
INSERT INTO TB_USER_FEATURES (features_id, tb_user_id) VALUES (4, 1);
INSERT INTO TB_USER_FEATURES (features_id, tb_user_id) VALUES (5, 1);

INSERT INTO TB_USER_NEWS (news_id, tb_user_id) VALUES (1, 1);
INSERT INTO TB_USER_NEWS (news_id, tb_user_id) VALUES (2, 1);
