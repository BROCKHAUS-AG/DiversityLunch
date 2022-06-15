FROM node:16-alpine as build

WORKDIR /app

#ENV PATH /app/node_modules/.bin:$PATH

COPY package*.json /app/
RUN npm install

COPY ./public /app/public/
COPY ./src /app/src/
COPY ../.env /app/.env
COPY ./tsconfig.json /app/tsconfig.json
COPY ./.eslintrc.json /app/

RUN npm run build

### STAGE 2: Run ###
FROM nginx:1.17.1-alpine
COPY nginx.conf /etc/nginx/nginx.conf
COPY --from=build /app/build /usr/share/nginx/html

# Copy .env. file and shell script to container
WORKDIR /usr/share/nginx/html
COPY ./env.sh .
COPY ../.env .

# Add bash
RUN apk add --no-cache bash

# Make our shell script executable
RUN chmod +x env.sh

# Start env-script and Nginx server
CMD ["/bin/bash", "-c", "/usr/share/nginx/html/env.sh && nginx -g \"daemon off;\""]
