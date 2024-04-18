# Use the official image as a parent image
FROM mysql:8.0

# Set the working directory in the container
WORKDIR /docker-entrypoint-initdb.d

# Copy the database initialize script:
# Contents of /docker-entrypoint-initdb.d directory are executed
# in alphabetical order.
COPY ./db/ct_agency.sql .

# Make MySQL listen on all interfaces.
ENV MYSQL_ROOT_HOST=%

# Use root/1234 as user/password credentials
ENV MYSQL_ROOT_PASSWORD=1234
ENV MYSQL_DATABASE=ct_agency

# Expose port 3306 to the outside world
EXPOSE 3306
