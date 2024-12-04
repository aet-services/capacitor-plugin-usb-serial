#!/bin/sh
export NVM_DIR="$HOME/.nvm"
[ -s "$NVM_DIR/nvm.sh" ] && \. "$NVM_DIR/nvm.sh"
if [ -f .nvmrc ]; then
NODE_VERSION=$(cat .nvmrc)
nvm use "$NODE_VERSION" || nvm install "$NODE_VERSION"
else
nvm use default
fi
exec $SHELL
