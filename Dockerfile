# Dockerfile
FROM maven:3.9.6-eclipse-temurin-17

RUN apt-get update && apt-get install -y wget unzip curl gnupg ca-certificates \
 && wget -q https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb \
 && apt-get install -y ./google-chrome-stable_current_amd64.deb || apt-get -f install -y \
 && rm -f google-chrome-stable_current_amd64.deb

# Chromedriver que haga match con la major de Chrome (fallback a latest si falla)
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
