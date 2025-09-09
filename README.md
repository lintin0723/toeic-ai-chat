# TOEIC AI Chat

## 專案介紹

TOEIC AI Chat 是一個全端 Web 應用程式，旨在提供一個互動式的聊天環境，讓使用者可以透過與 AI 助教的對話，來練習並準備多益 (TOEIC) 考試。本專案的核心，是整合了 Google 的 `gemma:7b` 大型語言模型，並透過一個現代化的、安全的前後端架構，來提供流暢、即時的聊天體驗。

## 主要功能

- **使用者安全驗證**：提供完整的使用者註冊與登入功能。
- **現代化驗證機制**：採用基於 Token 的 JWT (JSON Web Token) 驗證，取代傳統的 Session 機制，確保 API 的無狀態與安全性。
- **即時聊天介面**：使用 Vue.js 打造了一個響應式、美觀的聊天室介面。
- **AI 助教**：整合了由 Ollama 驅動的 `gemma:7b` 模型，能夠回答使用者提出的多益相關問題。
- **容器化部署**：整個應用程式（前端、後端、資料庫）都已經被完整地容器化，可以使用 Docker Compose 一鍵啟動，極大地簡化了部署流程。

## 技術棧

- **前端**
  - Vue.js 3 (Composition API)
  - Axios (用於 API 請求)
- **後端**
  - Java 17
  - Spring Boot 3
  - Spring Security (JWT)
  - Spring Data JPA
- **資料庫**
  - MySQL
- **AI 模型**
  - Ollama
  - Google Gemma:7b
- **容器化**
  - Docker
  - Docker Compose

## 如何啟動

1.  請先確保您的電腦上已經安裝了 Docker 和 Docker Compose。
2.  將本專案複製到您的本地電腦。
3.  打開終端機，並進入到專案的根目錄。
4.  執行以下指令來建置並啟動所有服務：
    ```sh
    docker-compose up --build -d
    ```
5.  等待所有容器啟動完成後，您就可以在瀏覽器中打開 `http://localhost:3000` 來開始使用。

---
*本 README 由 Gemini AI 助理協助產生。*
