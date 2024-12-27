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
                    description: 'Wir setzen Cookies und Tracking-Technologien ein. Diese sind für die Funktionalität erforderlich.',
                    acceptNecessaryBtn: 'Zustimmen',
                }
            }
        }
    }
});