include .env



apbuild:
	docker-compose -f ${COMPOSE_FILE_RUN} build

apup:
	docker-compose -f ${COMPOSE_FILE_RUN} up

apdb:
	docker-compose -f ${COMPOSE_FILE_RUN} run apdibi bash -c "psql -h apdibi -d ${AP_POSTGRES_DB_NAME} -U ${AP_POSTGRES_DB_USER_NAME}"

apdown:
	docker-compose -f ${COMPOSE_FILE_RUN} down

apdownf:
	docker-compose -f ${COMPOSE_FILE_RUN} down --rmi all -v --remove-orphans

aprm:
	docker-compose -f ${COMPOSE_FILE_RUN} rm 

apps:
	docker-compose -f ${COMPOSE_FILE_RUN} ps



###
###
###


# xup:
# 	docker-compose -f ${COMPOSE_FILE_TEST} up

# xdown:
# 	docker-compose -f ${COMPOSE_FILE_TEST} down

# xstop:
# 	docker-compose -f ${COMPOSE_FILE_TEST} stop

# xdownf:
# 	docker-compose -f ${COMPOSE_FILE_TEST} down --rmi all -v --remove-orphans

# xrm:
# 	docker-compose -f ${COMPOSE_FILE_TEST} rm 

# xdb:
# 	docker-compose -f ${COMPOSE_FILE_TEST} run testdibi bash -c "psql -h testdibi -d ${POSTGRES_DB_DEV} -U ${POSTGRES_USER_DEV}"


###
###
###


# up:
# 	docker-compose -f ${COMPOSE_FILE} up

# db:
# 	docker-compose -f ${COMPOSE_FILE} run dibi bash -c "psql -h dibi -d ${POSTGRES_DB_NAME} -U ${POSTGRES_DB_USER_NAME}"

# down:
# 	docker-compose -f ${COMPOSE_FILE} down

# downf:
# 	docker-compose -f ${COMPOSE_FILE} down --rmi all -v --remove-orphans

# rm:
# 	docker-compose -f ${COMPOSE_FILE} rm 

# ps:
# 	docker-compose -f ${COMPOSE_FILE} ps

# build:
# 	docker-compose -f ${COMPOSE_FILE} build


###
###
###

# secrets:
# 	SPRING_CONTAINER_NAME=${SPRING_CONTAINER_NAME},
# 	SPRING_PORT=${SPRING_PORT},
# 	POSTGRES_CONTAINER_NAME=${POSTGRES_CONTAINER_NAME},
# 	POSTGRES_PORT=${POSTGRES_PORT},
# 	POSTGRES_DB_NAME=${POSTGRES_DB_NAME},
# 	POSTGRES_DB_USER_NAME=${POSTGRES_DB_USER_NAME},
# 	POSTGRES_DB_PASSWORD_SECRET=${POSTGRES_DB_PASSWORD_SECRET},
# 	COMPOSE_FILE,
# 	COMPOSE_FILE_TEST,
