include .env
secrets:
	SPRING_CONTAINER_NAME=${SPRING_CONTAINER_NAME},
	SPRING_PORT=${SPRING_PORT},
	POSTGRES_CONTAINER_NAME=${POSTGRES_CONTAINER_NAME},
	POSTGRES_PORT=${POSTGRES_PORT},
	POSTGRES_DB_NAME=${POSTGRES_DB_NAME},
	POSTGRES_DB_USER_NAME=${POSTGRES_DB_USER_NAME},
	POSTGRES_DB_PASSWORD_SECRET=${POSTGRES_DB_PASSWORD_SECRET},

# https://stackoverflow.com/questions/769683/postgresql-show-tables-in-postgresql
db:
	docker-compose run dibi bash -c "psql -h dibi -d ${POSTGRES_DB_NAME} -U ${POSTGRES_DB_USER_NAME}"

up:
	docker-compose up

down:
	docker-compose down

downf:
	docker-compose down --rmi all -v --remove-orphans

rm:
	docker-compose rm 

ps:
	docker-compose ps

build:
	docker-compose build
