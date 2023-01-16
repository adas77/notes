
include .env

b:
	docker-compose -f ${COMPOSE_FILE_RUN} build

up:
	docker-compose -f ${COMPOSE_FILE_RUN} up

db:
	docker-compose -f ${COMPOSE_FILE_RUN} run apdibi bash -c "psql -h apdibi -d ${AP_POSTGRES_DB_NAME} -U ${AP_POSTGRES_DB_USER_NAME}"

apdown:
	docker-compose -f ${COMPOSE_FILE_RUN} down

apdownf:
	docker-compose -f ${COMPOSE_FILE_RUN} down --rmi all -v --remove-orphans

aprm:
	docker-compose -f ${COMPOSE_FILE_RUN} rm 

apps:
	docker-compose -f ${COMPOSE_FILE_RUN} ps
