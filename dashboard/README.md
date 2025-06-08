# Kafka Log Dashboard

A real-time dashboard for monitoring Kafka consumer logs, inspired by Elastic's log monitoring features.

## Features

- Real-time log monitoring with live tail
- Search and filter logs
- Highlighting of search terms
- Connection status indicator

## Technologies Used

- Next.js
- TypeScript
- Tailwind CSS
- Socket.io (for real-time updates)

## Getting Started

### Prerequisites

- Node.js 18.17.0 or higher
- npm or yarn
- Running Kafka consumer application (on port 8082)

### Installation

1. Navigate to the dashboard directory:
   ```
   cd dashboard
   ```

2. Install dependencies:
   ```
   npm install
   # or
   yarn install
   ```

3. Start the development server:
   ```
   npm run dev
   # or
   yarn dev
   ```

4. Open [http://localhost:3000](http://localhost:3000) in your browser to see the dashboard.

## Usage

The dashboard will automatically connect to the Kafka consumer application running on `http://localhost:8082` and display logs in real-time.

### Live Tail

The live tail feature automatically scrolls to show the latest logs as they are received. You can toggle this feature on/off using the "Live Tail" button.

### Filtering Logs

Use the search box to filter logs by employee name, department, or any text in the log message. The matching text will be highlighted in the logs.

## Architecture

The dashboard connects to the Kafka consumer application's REST API to fetch logs. The consumer application exposes the following endpoints:

- `GET /api/logs` - Returns all logs
- `GET /api/logs/latest?count=100` - Returns the latest logs (default: 100)

The dashboard polls these endpoints at regular intervals to provide real-time updates.
