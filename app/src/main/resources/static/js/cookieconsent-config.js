import 'https://cdn.jsdelivr.net/gh/orestbida/cookieconsent@3.0.1/dist/cookieconsent.umd.js';

CookieConsent.run({

    categories: {
        necessary: {
            enabled: true,  // this category is enabled by default
            readOnly: true  // this category cannot be disabled
        },
        analytics: {}
    },

    language: {
        default: 'de',
        translations: {
            de: {
                consentModal: {
                    title: 'Herzlich willkommen beim evangeliumstaucher',
                    description: 'Wir setzen Cookies und Tracking-Technologien ein. Diese sind für die Funktionalität erforderlich. Bei einer Verknüpfung mit Ihrem Google-Konto, wird die E-Mail-Adresse für die Verwaltung Ihres Spielerprofils gespeichert.',
                    acceptNecessaryBtn: 'Zustimmen',
                }
            }
        }
    }
});