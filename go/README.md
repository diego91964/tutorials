# Linguagem Go

A linguagem Go vem sendo utilizada em diversos projetos, então, é necessário um breve conhecimento da mesma.

Nestes tutoriais serão abordados:
- [1-Introdução](https://github.com/diego91964/tutorials/tree/master/go/1-introducao)
- [2-Funções](https://github.com/diego91964/tutorials/tree/master/go/2-funcoes)
- [3-Estrutura de Dados](https://github.com/diego91964/tutorials/tree/master/go/3-estrutura_de_dados)
- [4-Manipulação de Arquivos](https://github.com/diego91964/tutorials/tree/master/go/4-arquivos)
- Interação Web via Http
- Interação com Banco de Dados
- Banco de Dados
- Autenticação
- Migração de Banco de Dados


## Instalação Ubuntu

### Clonar repositório na pasta do usuário

bash < <(curl -s -S -L https://raw.githubusercontent.com/moovweb/gvm/master/binscripts/gvm-installer)

### Adicionar script no ~/.bashrc

[[ -s "$HOME/.gvm/scripts/gvm" ]] && source "$HOME/.gvm/scripts/gvm"

### Carregar configurações do arquivo .bashrc

source ~/.bashrc

### Verificar a versão do GVM

gvm version

### Listar versões Go

gvm listall

### Escolha a versão desejada (Ex.: go1.4)

gvm install go1.4

### Habilite o uso da API

gvm use go1.4

### Verifique a instalação 

go version

Créditos: [Hosting Advice](http://www.hostingadvice.com/how-to/install-golang-on-ubuntu/)

## Execução de um programa em go

Para a grande parte dos exemplos, vamos utilizar apenas o comando

go run nomeDoArquivo.go

## Referências

Estes tutoriais são uma compilação de várias referências de forma a facilitar o entendimento.

[Go By Example](https://gobyexample.com)
[Blog Go Lang](http://blog.golang.org)
[Go Lang Book](https://www.golang-book.com)