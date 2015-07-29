# Linguagem Go

A linguagem Go vem sendo utilizada em diversos projetos, então, é necessário um breve conhecimento da mesma.

Nestes tutoriais serão abordados:
- Introdução
- Estrutura de Dados
- Funções 
- Controle de Erros
- Interação Web via Http
- Interação com Banco de Dados
- Leitura de Arquivos
- Banco de Dados


## Instalação Ubuntu

### Clonar repositório na pasta do usuário

`bash < <(curl -s -S -L https://raw.githubusercontent.com/moovweb/gvm/master/binscripts/gvm-installer)´

### Adicionar script no ~/.bashrc

`[[ -s "$HOME/.gvm/scripts/gvm" ]] && source "$HOME/.gvm/scripts/gvm"´

### Carregar configurações do arquivo .bashrc

`source ~/.bashrc´

### Verificar a versão do GVM

`gvm version´

### Listar versões Go

`gvm listall´

### Escolha a versão desejada (Ex.: go1.4)

`gvm install go1.4´

### Habilite o uso da API

`gvm use go1.4´

### Verifique a instalação 

`go version´

Créditos: [Hosting Advice] (http://www.hostingadvice.com/how-to/install-golang-on-ubuntu/)