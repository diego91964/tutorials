# Primeiro Programa em Go

## Estrutura

A estrutura de um programa em go é bem simples, pode possuir pacotes importes e funções.

Nesta introdução vamos mostrar apenas o que toda introdução de uma linguagem deve mostrar, um Hello World. 

Podemos perceber que existe a declaração package main e o nome da função main, que assim como na linguagem C, isto é necessário para a execução do programa.

Tente alterar o nome do pacote e você pode se deparar com uma mensagem do tipo: 'go run: cannot run non-main package'

A decleração import "fmt" que é o pacote de formatação de I/O, com funções bem semelhantes as funções print da linguagem C.

## Declaração de variáveis

A declaração de variáveis na linguagem Go se tornou bem mais simples do que em outras linguagem, onde se adiciona apenas o que importa, porém o nome da variável e o tipo são colocados em ordem inversa:

- x int
- p *int
- a [3]int

A utilização do (*) foi herdada da linguagem C, mas vale lembrar que a linguagem Go é posfixada, o que pode gerar alguns problemas.

## Tipos de Programa Go

Existem dois tipos de programas na linguagem Go, os executáveis e as bibliotecas. Os programas executáveis, assim como o que é mostrado nesta sessão, podem ser executados diretamente pelo terminal. As bibliotecas são basicamente conjuntos de códicos que podem ser utilizados através do comando import.

As principais bibliotecas podem ser encontradas [aqui](https://golang.org/pkg/)

## Controle de Fluxo

### For

Um for assim como na linguagem C, deve possuir um índice inicial, uma condição de parada e um algorítimo de incremento ou decremento.

for i := 0; i < 10; i++

Mas, não é obrigatório informar todos os parâmetros de um loop:

for ; sum < 1000;

### IF

Funciona de forma semelhante a linguagem C, porém sem ()

Um  grande diferencial é o if que pode conter atribuições e chamadas de função, como 
if v := math.Pow(2, 10); 1020 < v {}

## Modificadores

