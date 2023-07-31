#!/bin/bash

# Imposta la cartella di lavoro
DIR="/srv/init/cli/standalone"

# Cicla i file con estensione .cli nella cartella specificata
for file in "$DIR"/*.cli; do
    # Esegui il comando desiderato su ogni file
    # Ad esempio, per stampare il nome del file:
    echo "/opt/jboss/wildfly/bin/jboss-cli.sh" --connect --file="$file"
done