/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    './pages/**/*.{js,ts,jsx,tsx,mdx}',
    './components/**/*.{js,ts,jsx,tsx,mdx}',
    './app/**/*.{js,ts,jsx,tsx,mdx}',
  ],
  theme: {
    extend: {
      colors: {
        'elastic-blue': '#0077cc',
        'elastic-dark': '#1a1c21',
        'elastic-light': '#f5f7fa',
      },
    },
  },
  plugins: [],
}