'use client'

import { useState, useEffect, useRef } from 'react'
import { io } from 'socket.io-client'

interface LogEntry {
  id: string
  timestamp: string
  message: string
  employeeName: string
  department: string
}

export default function LogViewer() {
  const [logs, setLogs] = useState<LogEntry[]>([])
  const [isLiveTail, setIsLiveTail] = useState(true)
  const [searchTerm, setSearchTerm] = useState('')
  const [isConnected, setIsConnected] = useState(false)
  const logContainerRef = useRef<HTMLDivElement>(null)

  const fetchLogs = async () => {
    try {
      // Use environment variable for API URL with fallback to localhost
      const apiUrl = process.env.NEXT_PUBLIC_API_URL || 'http://localhost:8082'
      const response = await fetch(`${apiUrl}/api/logs/latest?count=100`)
      if (!response.ok) {
        throw new Error('Failed to fetch logs')
      }

      const data = await response.json()

      // Transform the data to match our LogEntry interface
      const transformedLogs: LogEntry[] = data.map((log: any) => ({
        id: log.id,
        timestamp: log.timestamp,
        message: log.message,
        employeeName: log.employeeName,
        department: log.department
      }))

      setLogs(transformedLogs)
      setIsConnected(true)
    } catch (error) {
      console.error('Error fetching logs:', error)
      setIsConnected(false)
    }
  }

  useEffect(() => {
    // Fetch initial logs
    fetchLogs()

    // Set up polling for live tail
    const intervalId = setInterval(() => {
      if (isLiveTail) {
        fetchLogs()
      }
    }, 2000) // Poll every 2 seconds

    return () => {
      clearInterval(intervalId)
    }
  }, [isLiveTail])

  useEffect(() => {
    if (isLiveTail && logContainerRef.current) {
      logContainerRef.current.scrollTop = logContainerRef.current.scrollHeight
    }
  }, [logs, isLiveTail])

  const filteredLogs = logs.filter(log => 
    log.message.toLowerCase().includes(searchTerm.toLowerCase()) ||
    log.employeeName.toLowerCase().includes(searchTerm.toLowerCase()) ||
    log.department.toLowerCase().includes(searchTerm.toLowerCase())
  )

  const formatTimestamp = (timestamp: string) => {
    const date = new Date(timestamp)
    return date.toLocaleTimeString(undefined, { hour12: false })
  }

  const highlightSearchTerm = (text: string) => {
    if (!searchTerm) return text

    const regex = new RegExp(`(${searchTerm})`, 'gi')
    return text.replace(regex, '<span class="log-highlight">$1</span>')
  }

  return (
    <div className="flex flex-col h-full">
      <div className="flex items-center justify-between mb-4">
        <div className="flex items-center">
          <div className={`w-3 h-3 rounded-full mr-2 ${isConnected ? 'bg-green-500' : 'bg-red-500'}`}></div>
          <span className="text-sm">{isConnected ? 'Connected' : 'Disconnected'}</span>
        </div>
        <div className="flex items-center">
          <input
            type="text"
            placeholder="Filter logs..."
            className="border rounded-md px-3 py-1 mr-4 text-sm"
            value={searchTerm}
            onChange={(e) => setSearchTerm(e.target.value)}
          />
          <button
            className={`px-3 py-1 rounded-md text-sm ${isLiveTail ? 'bg-elastic-blue text-white' : 'bg-gray-200'}`}
            onClick={() => setIsLiveTail(!isLiveTail)}
          >
            {isLiveTail ? 'Live Tail: On' : 'Live Tail: Off'}
          </button>
        </div>
      </div>

      <div 
        ref={logContainerRef}
        className="bg-elastic-light rounded-md border border-gray-200 h-96 overflow-y-auto"
      >
        {filteredLogs.length === 0 ? (
          <div className="flex items-center justify-center h-full text-gray-500">
            No logs found
          </div>
        ) : (
          filteredLogs.map(log => (
            <div key={log.id} className="log-entry">
              <span className="log-timestamp">{formatTimestamp(log.timestamp)}</span>
              <span 
                className="log-content"
                dangerouslySetInnerHTML={{ __html: highlightSearchTerm(log.message) }}
              />
            </div>
          ))
        )}
      </div>
    </div>
  )
}
