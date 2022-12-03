include .env
secrets:
	SPRING_CONTAINER_NAME=${SPRING_CONTAINER_NAME},
	SPRING_PORT=${SPRING_PORT},
	POSTGRES_CONTAINER_NAME=${POSTGRES_CONTAINER_NAME},
	POSTGRES_PORT=${POSTGRES_PORT},
	POSTGRES_DB_NAME=${POSTGRES_DB_NAME},
	POSTGRES_DB_USER_NAME=${POSTGRES_DB_USER_NAME},
	POSTGRES_DB_PASSWORD_SECRET=${POSTGRES_DB_PASSWORD_SECRET},

db:
	docker-compose run dibi bash -c "psql -h dibi -d ${POSTGRES_DB_NAME} -U ${POSTGRES_DB_USER_NAME}"

up:
	docker-compose up

down:
	docker-compose down

rm:
	docker-compose rm

build:
	docker-compose build
