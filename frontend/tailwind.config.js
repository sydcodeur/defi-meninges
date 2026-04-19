/** @type {import('tailwindcss').Config} */
module.exports = {
    content: [
        "./src/**/*.{html,ts}"
    ],
    theme: {
        extend: {
            colors: {
                background: '#f8fafc',
                surface: '#ffffff',
                primary: '#10b981',
                textMain: '#334155'
            }
        },
    },
    plugins: [],
}