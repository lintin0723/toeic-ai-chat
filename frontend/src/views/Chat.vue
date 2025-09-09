<template>
  <div class="chat-container">
    <div class="chat-header">
      <h2>TOEIC AI Chat</h2>
    </div>
    <div class="chat-messages" ref="messagesContainer">
      <div v-for="(message, index) in messages" :key="index" class="message" :class="message.sender">
        <div class="avatar" :class="message.sender">
          {{ message.sender === 'user' ? 'You' : 'AI' }}
        </div>
        <div class="message-content">
          <p>{{ message.text }}</p>
        </div>
      </div>
      <div v-if="isLoading" class="message ai">
        <div class="avatar ai">AI</div>
        <div class="message-content">
          <div class="loading-indicator">
            <span></span><span></span><span></span>
          </div>
        </div>
      </div>
    </div>
    <div class="chat-input">
      <input
        type="text"
        v-model="newMessage"
        @keyup.enter="sendMessage"
        placeholder="Type your message..."
        :disabled="isLoading"
      />
      <button @click="sendMessage" :disabled="isLoading">Send</button>
    </div>
  </div>
</template>

<script setup>
import { ref, nextTick } from 'vue';
import axios from 'axios';

const messages = ref([
  { sender: 'ai', text: 'Hello! I am your TOEIC AI assistant. How can I help you today?' }
]);
const newMessage = ref('');
const isLoading = ref(false);
const messagesContainer = ref(null);

const scrollToBottom = async () => {
  await nextTick();
  if (messagesContainer.value) {
    messagesContainer.value.scrollTop = messagesContainer.value.scrollHeight;
  }
};

const sendMessage = async () => {
  const userMessageText = newMessage.value.trim();
  if (!userMessageText || isLoading.value) return;

  messages.value.push({ sender: 'user', text: userMessageText });
  newMessage.value = '';
  isLoading.value = true;
  await scrollToBottom();

  try {
    const user = JSON.parse(localStorage.getItem('user'));
    let headers = {};

    // THE FINAL, REAL FIX: Use 'accessToken' which is sent from the backend.
    if (user && user.accessToken) {
      headers.Authorization = 'Bearer ' + user.accessToken;
    }

    const response = await axios.post('/api/chat', {
      message: userMessageText
    }, { headers });

    messages.value.push({ sender: 'ai', text: response.data.reply });
  } catch (error) {
    console.error('Error sending message:', error);
    messages.value.push({ sender: 'ai', text: 'Sorry, I encountered an error. Please try again.' });
  } finally {
    isLoading.value = false;
    await scrollToBottom();
  }
};
</script>

<style scoped>
.chat-container {
  display: flex;
  flex-direction: column;
  height: 100vh;
  max-width: 768px;
  margin: 0 auto;
  background-color: #fff;
  box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
}

.chat-header {
  padding: 20px;
  background-color: #007bff; /* THEME COLOR: BLUE */
  color: white;
  text-align: center;
}

.chat-messages {
  flex: 1;
  padding: 20px;
  overflow-y: auto;
  display: flex;
  flex-direction: column;
  gap: 15px;
}

.message {
  display: flex;
  align-items: flex-end;
  gap: 10px;
  max-width: 80%;
}

.message.user {
  align-self: flex-end;
  flex-direction: row-reverse;
}

.message.ai {
  align-self: flex-start;
}

.avatar {
  width: 40px;
  height: 40px;
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  font-weight: bold;
  color: white;
}
.avatar.user {
  background-color: #007bff;
}
.avatar.ai {
  background-color: #6c757d; /* AI avatar color to a neutral grey */
}

.message-content {
  padding: 10px 15px;
  border-radius: 18px;
  background-color: #f1f1f1;
  word-wrap: break-word;
  max-width: calc(100% - 50px);
}

.message.user .message-content {
  background-color: #007bff;
  color: white;
}

.chat-input {
  display: flex;
  padding: 20px;
  border-top: 1px solid #ddd;
}

.chat-input input {
  flex: 1;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 20px;
  margin-right: 10px;
}

.chat-input button {
  padding: 10px 20px;
  border: none;
  background-color: #007bff; /* THEME COLOR: BLUE */
  color: white;
  border-radius: 20px;
  cursor: pointer;
}

.chat-input button:disabled {
  background-color: #aaa;
}

.loading-indicator span {
  display: inline-block;
  width: 8px;
  height: 8px;
  border-radius: 50%;
  background-color: #888;
  margin: 0 2px;
  animation: bounce 1.4s infinite ease-in-out both;
}

.loading-indicator span:nth-child(1) {
  animation-delay: -0.32s;
}

.loading-indicator span:nth-child(2) {
  animation-delay: -0.16s;
}

@keyframes bounce {
  0%, 80%, 100% {
    transform: scale(0);
  }
  40% {
    transform: scale(1.0);
  }
}
</style>
