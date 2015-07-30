package main

import "fmt"
import "math"

// Comentarios sao feitos assim

func main() {

	// Print Simples
    fmt.Println("Hello Git")

    // Print em uma String
    s := fmt.Sprintf("Hello %s", "Git")
    fmt.Println(s)

    //Print boolean
    fmt.Printf("%t\n", true)

    //Notação Científica
    fmt.Printf("%e\n", 123400000.0)

    //Utilizando Formatação
    fmt.Printf("|%6d|%6d|\n", 12, 345)


    fmt.Println("Trabalhando com FOR e Indice")
    sum := 0
	for i := 0; i < 10; i++ {
		sum += i
	}
	fmt.Println(sum)

	fmt.Println("Trabalhando com FOR sem indice e sem condição")
	sum2 := 1
	for ; sum2 < 1000; {
		sum2 += sum2
	}
	fmt.Println(sum2)

	fmt.Println("For se passando por um While")
	sum3 := 1
	for sum3 < 1000 {
		sum3 += sum3
	}
	fmt.Println(sum3)

	//Tente digitar for { fmt.Println("While(1)")	}
	
	if sum3 == 1024 {
		fmt.Println("O IF funciona mesmo")
	}

	if v := math.Pow(2, 10); 1020 < v {
		fmt.Println("Tuning a IF Condição 1")
	}else {
		fmt.Println("Tuning a IF Condição 2")
	}

	
}

