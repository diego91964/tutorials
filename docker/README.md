# Docker

##Instalar

https://docs.docker.com/installation/

## Docker sem ser Root (Ubuntu)

* Adicionar o grupo docker se ele não existir 

$sudo groupadd docker

* Adicionar o usuário atual "${USER}" no grupo docker.

$sudo gpasswd -a ${USER} docker

* Reiniciar o Docker daemon:

$sudo service docker restart

* Se estiver usando Ubuntu 14.04 utilize docker.io ao invés de:

$sudo service docker.io restart

Execute ou faça logout/login:

$newgrp docker
