    package main

import "fmt"
import "math"

    
type point struct {
    x, y int
}

type  estruturaSemNomes struct{
    int        
    string       
}

type Abser interface {
    Abs() float64
}

type MyFloat float64

type MyString string

var m map[string]point

func main() {

  
  trabalhandoComInterface()
  trabalhandoComStruct()
  trabalhandoComSlice()
  trabalhandoComMaps()
  trabalhandoComCannels()
}


// A struct with four anonymous fields of type T1, *T2, P.T3 and *P.T4


//Exemplo Struct
func trabalhandoComStruct () {
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

    //Criando uma instância de uma estrutura anônima
    x := estruturaSemNomes{1, "Teste"}

    //Imprimindo Valores
    fmt.Printf("%v\n", x)    

    //Imprimindo valores e rótulos
    fmt.Printf("%+v\n", x)
}

// Exemplo de Slice
func trabalhandoComSlice() {
    p := []int{2, 3, 5, 7, 11, 13}

    //Acessando elementos da posição 1 até 4
    fmt.Println("p ==", p)
    fmt.Println("p[1:4] ==", p[1:4])

    // Quando o índice não é mostrado o padrão é 0
    fmt.Println("p[:3] ==", p[:3])

    // Quando o índicie final não é mostrado o padrão é o último elemento
    fmt.Println("p[4:] ==", p[4:])
}

func trabalhandoComInterface () {

    var a Abser // Criando uma variável do tipo interface
    var b Abser // Criando uma variável do tipo interface
    f := MyFloat(-math.Sqrt2) // criando uma interface com a função
    v := MyString("string")
    
    a = f  // Adicionando uma implementação
    b = v // Adicionando outra implementação
 
    fmt.Println(a.Abs())
    fmt.Println(b.Abs())

}

// Implementação da Interface
func (f MyFloat) Abs() float64 {
    if f < 0 {
        return float64(-f)
    }
    return float64(f)
}

// Implementação da Interface
func (f MyString) Abs() float64 {
   return float64(666)
}


func trabalhandoComMaps () {

    m = make(map[string]point)
    m["endereco"] = point{
        40, 74,
    }
    fmt.Println(m["endereco"])
}


func sum(a []int, c chan int) {
    sum := 0
    for _, v := range a {
        sum += v
    }
    c <- sum // send sum to c
}

func  trabalhandoComCannels() {
    
    a := []int{7, 2, 8, -9, 4, 0}

    c := make(chan int)

    go sum(a[:3], c)
    go sum(a[3:], c)
    go sum(a[0:1], c)

    y := <-c
    x := <-c
    z := <-c

    go sum(a[0:1], c)

    k := <-c

   

   fmt.Println(x, y, z, k, x+y)
}
