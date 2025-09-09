<template>
  <div id="app-container">
    <router-view />
  </div>
</template>

<script setup>
// This is the main layout component.
// The <router-view> will be replaced by the component for the current route (e.g., Login, Register).
</script>

<style scoped>
#app-container {
  width: 100%;
  min-height: 100vh;
  background-color: #f0f2f5; /* A light grey background */
}
</style>
