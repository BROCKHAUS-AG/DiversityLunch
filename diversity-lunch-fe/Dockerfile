FROM node:16-alpine as build

WORKDIR /app

COPY package*.json /app/
RUN npm install

COPY ./public /app/public/
COPY ./src /app/src/
COPY ./.env.production /app/.env
COPY ./tsconfig.json /app/tsconfig.json
COPY ./.eslintrc.json /app/
RUN npm run build

### STAGE 2: Run ###
FROM nginx:1.17.1-alpine

COPY --from=build /app/build /usr/share/nginx/html
COPY nginx.conf /etc/nginx/nginx.conf

ENTRYPOINT ["/bin/sh", "/usr/share/nginx/html/config/app_config_generator.sh"]

#COPY build /usr/share/nginx/html
