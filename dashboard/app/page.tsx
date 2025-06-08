'use client'

import { useState, useEffect } from 'react'
import LogViewer from './components/LogViewer'

export default function Home() {
  return (
    <div className="flex flex-col space-y-6">
      <div className="bg-white rounded-lg shadow-md p-6">
        <h2 className="text-xl font-semibold mb-4">Real-time Troubleshooting with Live Tail</h2>
        <p className="text-gray-600 mb-6">
          Monitor Kafka consumer logs in real-time to quickly identify and troubleshoot issues.
          The live tail feature shows you the most recent logs as they are generated.
        </p>
        
        <LogViewer />
      </div>
    </div>
  )
}