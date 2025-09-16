#!/bin/bash

echo "========================================"
echo "   CONFIGURACION DE ENTORNO DE PRUEBAS"
echo "========================================"
echo

echo "Verificando Chrome..."
if command -v google-chrome &> /dev/null || command -v chromium-browser &> /dev/null; then
    echo "✓ Chrome encontrado"
    echo "Configurando para usar Chrome..."
    cp serenity.properties serenity-backup.properties 2>/dev/null
    echo "Usando configuracion de Chrome"
else
    echo "✗ Chrome no encontrado"
    echo
    echo "Verificando Firefox..."
    if command -v firefox &> /dev/null; then
        echo "✓ Firefox encontrado"
        echo "Configurando para usar Firefox..."
        cp serenity-firefox.properties serenity.properties
        echo "Usando configuracion de Firefox"
    else
        echo "✗ Firefox no encontrado"
        echo
        echo "ERROR: No se encontraron navegadores compatibles"
        echo "Por favor instala Chrome o Firefox"
        exit 1
    fi
fi

echo
echo "========================================"
echo "   CONFIGURACION COMPLETADA"
echo "========================================"
echo
echo "Para ejecutar las pruebas:"
echo "  mvn clean test"
echo
