FROM node:18-alpine

WORKDIR /app

# Copy package.json and package-lock.json
COPY dashboard/package.json dashboard/package-lock.json* ./

# Install dependencies
RUN npm install

# Copy the rest of the dashboard files
COPY dashboard/ ./

# Update API URL for production
ENV NEXT_PUBLIC_API_URL=http://localhost:8082

# Build the application
RUN npm run build

# Expose the port the app runs on
EXPOSE 3000

# Command to run the application
CMD ["npm", "start"]
