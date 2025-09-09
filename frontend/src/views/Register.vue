<template>
  <div class="auth-container">
    <form @submit.prevent="handleRegister" class="auth-form">
      <h1>Create Account</h1>
      <div class="form-group">
        <label for="username">Username</label>
        <input type="text" id="username" v-model="username" required>
      </div>
      <!-- ADDED EMAIL INPUT FIELD -->
      <div class="form-group">
        <label for="email">Email</label>
        <input type="email" id="email" v-model="email" required>
      </div>
      <div class="form-group">
        <label for="password">Password</label>
        <input type="password" id="password" v-model="password" required>
      </div>
      <button type="submit" class="auth-button">Register</button>
      
      <p v-if="message" class="error-message">{{ message }}</p>

      <div class="auth-switch">
        <p>Already have an account? <router-link to="/login">Login here</router-link></p>
      </div>
    </form>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import AuthService from '../services/AuthService';

const username = ref('');
const email = ref(''); // ADDED EMAIL REF
const password = ref('');
const message = ref('');
const router = useRouter();

const handleRegister = async () => {
  try {
    // PASS EMAIL TO THE SERVICE
    const response = await AuthService.register({ 
      username: username.value, 
      email: email.value,
      password: password.value 
    });
    message.value = response.data.message + ' Redirecting to login...';
    setTimeout(() => {
      router.push('/login');
    }, 2000);
  } catch (error) {
    message.value = error.response?.data?.message || 'An error occurred during registration.';
  }
};
</script>

<style scoped>
/* Scoped styles will only apply to this component */
</style>
