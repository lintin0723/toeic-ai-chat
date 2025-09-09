# Stage 1: Build the Vue.js application
FROM node:18-alpine AS build

# Set the working directory in the container
WORKDIR /app

# Copy package.json and package-lock.json (if available) first
# This leverages Docker layer caching to speed up subsequent builds
COPY package*.json ./

# Install dependencies
RUN npm install

# Copy the rest of the application's source code
COPY . .

# Build the application for production
RUN npm run build

# Stage 2: Serve the static files with Nginx
FROM nginx:1.25-alpine

# Copy the build output from the build stage to Nginx's web server directory
# The output of 'npm run build' for Vite is in the 'dist' folder
COPY --from=build /app/dist /usr/share/nginx/html

# Copy the custom Nginx configuration
COPY nginx.conf /etc/nginx/conf.d/default.conf

# Expose port 80 for the Nginx server
EXPOSE 80

# The default Nginx image command will start the server automatically
# So, we don't need to override the CMD unless we have custom configurations.
