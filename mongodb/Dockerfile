FROM mongo:4

ADD ./dump /docker-entrypoint-initdb.d

ENV MONGO_INITDB_DATABASE blackhouse

CMD ["mongod"]