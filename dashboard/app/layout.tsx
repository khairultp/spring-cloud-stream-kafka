import './globals.css'
import type { Metadata } from 'next'
import { Inter } from 'next/font/google'

const inter = Inter({ subsets: ['latin'] })

export const metadata: Metadata = {
  title: 'Kafka Log Dashboard',
  description: 'Real-time log monitoring for Kafka consumer',
}

export default function RootLayout({
  children,
}: {
  children: React.ReactNode
}) {
  return (
    <html lang="en">
      <body className={inter.className}>
        <header className="bg-elastic-dark text-white p-4">
          <div className="container mx-auto">
            <h1 className="text-2xl font-bold">Kafka Log Dashboard</h1>
            <p className="text-sm">Real-time log monitoring for Kafka consumer</p>
          </div>
        </header>
        <main className="container mx-auto py-6 px-4">
          {children}
        </main>
      </body>
    </html>
  )
}