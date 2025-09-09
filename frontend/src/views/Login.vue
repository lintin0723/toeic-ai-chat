<template>
  <div class="auth-container">
    <form @submit.prevent="handleLogin" class="auth-form">
      <h1>Login</h1>
      <div class="form-group">
        <label for="username">Username</label>
        <input type="text" id="username" v-model="username" required>
      </div>
      <div class="form-group">
        <label for="password">Password</label>
        <input type="password" id="password" v-model="password" required>
      </div>
      <button type="submit" class="auth-button">Login</button>

      <p v-if="message" class="error-message">{{ message }}</p>

      <div class="auth-switch">
        <p>Don't have an account? <router-link to="/register">Register here</router-link></p>
      </div>
    </form>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import AuthService from '../services/AuthService';

const username = ref('');
const password = ref('');
const message = ref('');
const router = useRouter();

const handleLogin = async () => {
  try {
    await AuthService.login({ 
      username: username.value, 
      password: password.value 
    });
    // On successful login, redirect to the chat page
    router.push('/chat');
  } catch (error) {
    message.value = error.response?.data?.message || 'An error occurred during login.';
  }
};
</script>

<style scoped>
/* Scoped styles will only apply to this component */
</style>
