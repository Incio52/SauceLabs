FROM maven:3.9.6-eclipse-temurin-17

# Libs que Chrome usa en headless
RUN apt-get update && apt-get install -y \
    wget unzip curl gnupg ca-certificates \
    libasound2 libatk-bridge2.0-0 libatk1.0-0 libcups2 libdrm2 libgbm1 \
    libgtk-3-0 libnspr4 libnss3 libx11-xcb1 libxcomposite1 libxdamage1 \
    libxfixes3 libxrandr2 xdg-utils fonts-liberation

# Chrome estable (vía .deb)
RUN wget -q https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb \
 && apt-get install -y ./google-chrome-stable_current_amd64.deb || apt-get -f install -y \
 && rm -f google-chrome-stable_current_amd64.deb

# Chromedriver que haga match con la mayor de Chrome (fallback a latest)
RUN set -eux; \
  CHROME_MAJOR=$(google-chrome --version | grep -oE '[0-9]+' | head -1 || true); \
  if [ -n "$CHROME_MAJOR" ]; then \
    VER=$(curl -fsSL "https://googlechromelabs.github.io/chrome-for-testing/LATEST_RELEASE_${CHROME_MAJOR}" || true); \
  fi; \
  case "${VER:-}" in (*.*) : ;; (*) VER=$(curl -fsSL https://googlechromelabs.github.io/chrome-for-testing/LATEST_RELEASE);; esac; \
  curl -fsSL -o /tmp/chromedriver.zip "https://storage.googleapis.com/chrome-for-testing-public/${VER}/linux64/chromedriver-linux64.zip"; \
  unzip -o /tmp/chromedriver.zip -d /tmp; \
  mv /tmp/chromedriver-linux64/chromedriver /usr/local/bin/chromedriver; \
  chmod +x /usr/local/bin/chromedriver; \
  rm -rf /tmp/*

ENV WEBDRIVER_CHROME_DRIVER=/usr/local/bin/chromedriver \
    CHROME_BIN=/usr/bin/google-chrome

WORKDIR /workspace
