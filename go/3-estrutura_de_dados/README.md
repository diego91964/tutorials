#3 - Estrutura de Dados da Linguagem Go

## [Method sets](https://golang.org/ref/spec#Method_sets)

Um tipo pode ter um method set associado a ele. O method set de um tipo interface é a sua interface. O method set de qualquer outro tipo X consiste de todos os métodos com o receptor X. O method set de um tipo determina a interface que o tipo implementa e o método que pode ser chamado utilizando o receptor daquele tipo.

## [Boolean types](https://golang.org/ref/spec#Boolean_types)

Um tipo boolean é a representação dos valores constantes para true ou false.

## [Numeric types](https://golang.org/ref/spec#Numeric_types)

Os tipois numéricos são semelhantes aos tipos de linguagem C:

- uint8. Inteiros sem sinal que podem ser representados por 8 bits, ou seja, 0 a 255
- uint16. Inteiros sem sinal que podem ser representados por 16 bits, ou seja, 0 a to 65535)
- uint32. Inteiros sem sinal que podem ser representados por 32 bits, ou seja, 0 a 4294967295)
- uint64. Inteiros sem sinal que podem ser representados por 64 bits, ou seja, 0 a18446744073709551615)

- int8. Inteiros sem sinal que podem ser representados por 8 bits, ou seja, -128 to 127
- int16. Inteiros sem sinal que podem ser representados por 16 bits, ou seja, -32768 to 32767
- int32. Inteiros sem sinal que podem ser representados por 32 bits, ou seja, -2147483648 to 2147483647
- int64. Inteiros sem sinal que podem ser representados por 64 bits, ou seja, -9223372036854775808 to 9223372036854775807
- float32. Conjunto de todos os números de pontos flutuantes de 32 bits definidos pela IEEE-754
- float64. Conjunto de todos os números de pontos flutuantes de 64 bits definidos pela IEEE-754
- [IEEE-754](https://pt.wikipedia.org/wiki/IEEE_754)

- complex64   Conjunto de todos os números complexos divididos em 1 float32 para a parte real e 1 para a parte imaginária
- complex128  Conjunto de todos os números complexos divididos em 1 float32 para a parte real e 1 para a parte imaginária

- byte. Representação para uint8
- rune. Representação paraint32

## [String types](https://golang.org/ref/spec#String_types)
## [Array types](https://golang.org/ref/spec#Array_types)
## [Slice types](https://golang.org/ref/spec#Slice_types)
## [Struct types](https://golang.org/ref/spec#Struct_types)
## [Pointer types](https://golang.org/ref/spec#Pointer_types)
## [Function types](https://golang.org/ref/spec#Function_types)
## [Interface types](https://golang.org/ref/spec#Interface_types)
## [Map types](https://golang.org/ref/spec#Map_types)
## [Channel types](https://golang.org/ref/spec#Channel_types)


