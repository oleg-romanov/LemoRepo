
# Lemon AI 🍋

Проект Lemon AI представляет собой клиент-серверное мобильное приложение, которое позволяет определять качество лимона по фотографии.


## Features

- Возможности регистрации и аутентификации
- Просмотр профиля
- Просмотр истории сканирований в виде плитки, либо в виде списка
- Просмотр превью снимка перед отправкой
- Мультиплатформа работает как на IOS, так и на Android

## Tech Stack

**Client:** Jetpack compose, SwiftUI, Ktor, Kotlin Courutines, Kotlin, Swift

**Server:** Postgres, flask, psycopg2


## Deployment

Для развертки этого проектка необходимо:

```bash
  pip install -r Pipfile.txt
```

Заменить в server.py параметры connection и ссылку на нейронную модель model_url

Для установки клиента необходимо произвести git clone данного репозитория и запустить в Android studio или XCode (iosApp) 


## Authors

- [@BIBLETUM](https://github.com/BIBLETUM)
- [@Oleg Romanov](https://github.com/oleg-romanov)
- [@dish0nest](https://github.com/dish0nest)
- [@RobyBen](https://github.com/RobyBen)

