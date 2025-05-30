
# 💱 Conversor de Monedas en Java

Este es un proyecto simple hecho en Java que permite convertir montos entre diferentes monedas usando una API pública de tasas de cambio y la biblioteca Gson para analizar respuestas JSON.

## 🚀 Tecnologías utilizadas

- Java 17 o superior
- Gson (de Google)
- API pública: [ExchangeRate-API](https://www.exchangerate-api.com/)
- HTTP Client (`java.net.http`)

## 🔗 API utilizada

Se utiliza la API de ExchangeRate API:

> ⚠️ Reemplazá `tu_api_key_aqui` con tu clave de API real.

## 🌎 Monedas disponibles

El conversor permite trabajar con las siguientes monedas:

- ARS - Peso argentino
- BOB - Boliviano boliviano
- BRL - Real brasileño
- CLP - Peso chileno
- COP - Peso colombiano
- USD - Dólar estadounidense

## 🎯 Funcionalidad

- Menú interactivo para seleccionar moneda base y moneda destino.
- Ingreso del monto a convertir.
- Llamada HTTP a la API para obtener la tasa de conversión.
- Conversión del monto y presentación del resultado.

## 🛠 Cómo ejecutar el proyecto

1. Clonar el repositorio:

```bash
git clone https://github.com/tu_usuario/conversor-monedas-java.git
cd conversor-monedas-java

