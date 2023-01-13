include .env
secrets:
	SPRING_CONTAINER_NAME=${SPRING_CONTAINER_NAME},
	SPRING_PORT=${SPRING_PORT},
	POSTGRES_CONTAINER_NAME=${POSTGRES_CONTAINER_NAME},
	POSTGRES_PORT=${POSTGRES_PORT},
	POSTGRES_DB_NAME=${POSTGRES_DB_NAME},
	POSTGRES_DB_USER_NAME=${POSTGRES_DB_USER_NAME},
	POSTGRES_DB_PASSWORD_SECRET=${POSTGRES_DB_PASSWORD_SECRET},
	COMPOSE_FILE,
	COMPOSE_FILE_TEST,

# https://stackoverflow.com/questions/769683/postgresql-show-tables-in-postgresql
db:
	docker-compose -f ${COMPOSE_FILE} run dibi bash -c "psql -h dibi -d ${POSTGRES_DB_NAME} -U ${POSTGRES_DB_USER_NAME}"

xdb:
	docker-compose -f ${COMPOSE_FILE_TEST} run testdibi bash -c "psql -h testdibi -d ${POSTGRES_DB_DEV} -U ${POSTGRES_USER_DEV}"

# dbup:
# 	docker-compose -f ${COMPOSE_FILE} up dibi



up:
	docker-compose -f ${COMPOSE_FILE} up

xup:
	docker-compose -f ${COMPOSE_FILE_TEST} up


down:
	docker-compose -f ${COMPOSE_FILE} down

downf:
	docker-compose -f ${COMPOSE_FILE} down --rmi all -v --remove-orphans

rm:
	docker-compose -f ${COMPOSE_FILE} rm 

ps:
	docker-compose -f ${COMPOSE_FILE} ps

build:
	docker-compose -f ${COMPOSE_FILE} build
