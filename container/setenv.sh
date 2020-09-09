export JAVA_OPTS="$JAVA_OPTS -Ddatabase.driver=org.postgresql.Driver"
export JAVA_OPTS="$JAVA_OPTS -Ddatabase.dialect=org.hibernate.dialect.PostgreSQL9Dialect"
export JAVA_OPTS="$JAVA_OPTS -Ddatabase.url=jdbc:postgresql://${DB_URL}:5432/${DB_NAME}"
export JAVA_OPTS="$JAVA_OPTS -Ddatabase.user=${DB_USER}"
export JAVA_OPTS="$JAVA_OPTS -Ddatabase.password=${DB_PASSWORD}"
export JAVA_OPTS="$JAVA_OPTS -Ddatabase.driver=org.postgresql.Driver"
export JAVA_OPTS="$JAVA_OPTS -Dkey=test"
export JAVA_OPTS="$JAVA_OPTS -Dspring.profiles.active=dev"
export JAVA_OPTS="$JAVA_OPTS -Daws.region=us-east-1"
export JAVA_OPTS="$JAVA_OPTS -Daws.accessKeyId=AKIAWDMVRNFFYOI2DANQ"
export JAVA_OPTS="$JAVA_OPTS -Daws.secretKey=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"



