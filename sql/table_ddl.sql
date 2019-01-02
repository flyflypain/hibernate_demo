
drop table if EXISTS trade cascade;
create table IF NOT EXISTS trade(
	trade_id SERIAL PRIMARY KEY,
	target_account VARCHAR(10),
	execute_account VARCHAR(10),
	amount int NOT NULL,
	trade_type TEXT NOT NULL
);

drop table if EXISTS cashflow cascade;
create table IF NOT EXISTS cashflow(
	cashflow_id SERIAL PRIMARY KEY,
	trade_id int not null,
	cashflow_side text not null,
	target_account VARCHAR(10),
	execute_account VARCHAR(10),
	amount int NOT NULL,
	FOREIGN KEY (trade_id) REFERENCES trade (trade_id)
);

drop table if EXISTS balance cascade;
create table IF NOT EXISTS balance(
	balance_id SERIAL PRIMARY KEY,
	account text not null,
	amount int not null,
	version int not null
);