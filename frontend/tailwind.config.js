/** @type {import('tailwindcss').Config} */
module.exports = {
    content: [
        "./src/**/*.{html,ts}"
    ],
    theme: {
        extend: {
            colors: {
                stage: {
                    DEFAULT: '#0A0A0A',
                    mid: '#141414',
                    light: '#2A2A2A'
                },
                surface: {
                    DEFAULT: '#171717',
                    raised: '#262626',
                    sunken: '#0A0A0A'
                },
                ink: {
                    DEFAULT: '#FFFFFF',
                    muted: '#A3A3A3',
                    subtle: '#737373'
                },
                ruby: {
                    DEFAULT: '#BE123C',
                    bright: '#E11D48',
                    deep: '#881337'
                }
            },
            fontFamily: {
                display: ['"Barlow Condensed"', 'ui-sans-serif', 'system-ui', 'sans-serif']
            },
            backgroundImage: {
                spotlight: 'radial-gradient(ellipse 70% 55% at 50% 0%, theme(colors.stage.light) 0%, theme(colors.stage.mid) 45%, theme(colors.stage.DEFAULT) 100%)'
            },
            boxShadow: {
                'ruby-soft': '0 8px 24px -8px rgb(190 18 60 / 0.45)',
                'ruby-line': '0 0 8px rgb(190 18 60 / 0.35)',
                buzzer: '0 8px 0 0 theme(colors.ruby.deep), 0 12px 32px -6px rgb(190 18 60 / 0.4)',
                'buzzer-pressed': '0 0 0 0 theme(colors.ruby.deep), 0 6px 24px -6px rgb(190 18 60 / 0.5)'
            }
        },
    },
    plugins: [],
}
