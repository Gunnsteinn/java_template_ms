#!/bin/bash

# Stop the running container
docker-compose down

sudo /etc/init.d/mysql stop

# Remove the MySQL data directory
sudo rm -rf template-db/

# Prompt for confirmation before pruning Docker resources
read -p "Are you sure you want to prune all unused Docker resources? (y/N) " confirmation
if [[ $confirmation =~ ^[Yy]$ ]]; then
    # Prune all unused Docker resources
    docker system prune --all -f
else
    echo "Skipping Docker system prune."
fi

# Prompt for confirmation before pruning all volumes
read -p "Are you sure you want to prune all Docker volumes? (y/N) " confirmation
if [[ $confirmation =~ ^[Yy]$ ]]; then
    # Prune all Docker volumes
    docker volume prune --all -f
else
    echo "Skipping Docker volume prune."
fi

# Rebuild and start the containers
docker-compose up -d --build

