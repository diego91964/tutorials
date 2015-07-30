package main

    
import (
    "bufio"
    "fmt"
    "io"
    "io/ioutil"
    "os"
)


func main() {
    writeSomeThing("/tmp/arquivoTeste1" , "/tmp/arquivoTeste2")
    copyFile("/tmp/arquivoTeste1" , "/tmp/arquivoTeste3")
    fmt.Print("Verificando Cópia")
    fmt.Print("Arquivo Origem\n")
    readfile("/tmp/arquivoTeste1")
    fmt.Print("Arquivo Destino\n")
    readfile("/tmp/arquivoTeste3")
    fmt.Print("Lendo Apenas os 4 primeiros bytes\n")
    readAByteArray(4 , "/tmp/arquivoTeste3")
    fmt.Print("Lendo Cursor de 1 a 4\n")
    readACursor(1,1,"/tmp/arquivoTeste3")
}


func copyFile(srcName,dstName string) (written int64, err error) {
    src, err := os.Open(srcName)
    if err != nil {
        return
    }
    defer src.Close()

    dst, err := os.Create(dstName)
    if err != nil {
        return
    }
    defer dst.Close()

    return io.Copy(dst, src)
}


func writeSomeThing (fileName1, fileName2 string) {

    // Somente escrita no arquivo
    d1 := []byte("hello\ngo\n")
    err := ioutil.WriteFile(fileName1, d1, 0644)
    check(err)

    // Criar e escrever
    f, err := os.Create(fileName2)
    check(err)

    defer f.Close() // Quando a função terminar, ela deve fechar o arquivo
    d2 := []byte{115, 111, 109, 101, 10} // String "some" em bytes
    n2, err := f.Write(d2)
    fmt.Printf("Bytes escritos: %d", n2)
    check(err)

    
    f.Sync()
    w := bufio.NewWriter(f) // Criando buffer
    n4, err := w.WriteString("buffer\n") // Adicionando ao buffer
    fmt.Printf("Bytes escritos: %d", n4)
    w.Flush() // Salvando buffer no arquivo fisico
}

func readfile (fileName string) {
    dat, err := ioutil.ReadFile(fileName)
    check(err)
    fmt.Print(string(dat))
}

func readAByteArray (arraySize int , fileName string) {

    f, err := os.Open(fileName)
    defer f.Close()
    b1 := make([]byte, arraySize)
    n1, err := f.Read(b1)
    check(err)
    fmt.Printf("%d bytes: %s\n", n1, string(b1))
}   

func readACursor (offset int64 , inicio int , fileName string) {

    f, err := os.Open(fileName)
    defer f.Close()
    o3, err := f.Seek(offset, inicio)
    check(err)
    b3 := make([]byte, 2)
    n3, err := io.ReadAtLeast(f, b3, 2)
    check(err)
    fmt.Printf("%d bytes @ %d: %s\n", n3, o3, string(b3))
}

func check(e error) { // Função que verifica se ocorreu algum erro
    if e != nil {
        panic(e)
    }
}