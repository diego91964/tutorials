package main

import "fmt"

    
type point struct {
    x, y int
}

func main() {

    //Criação de uma Instância do tipo point
	p := point{1, 2}

    //Print apenas dos dados da estrutura
    fmt.Printf("%v\n", p)

    //Print dos dados e os rótulos
    fmt.Printf("%+v\n", p)

    //Print dos dados, dos nomes e do nome completo da estrutura
    fmt.Printf("%#v\n", p)

    //Print apenas do nome completo da estrutura
    fmt.Printf("%T\n", p)

    //Print da representação do ponteiro
    fmt.Printf("%p\n", &p)
}