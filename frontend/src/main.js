import './assets/main.css'

import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import setupInterceptors from './services/setupInterceptors'; // IMPORT THE INTERCEPTOR

const app = createApp(App)

app.use(router)

// SETUP THE INTERCEPTOR
setupInterceptors();

app.mount('#app')
