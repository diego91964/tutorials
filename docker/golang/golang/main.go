package main

import (
    "fmt"
    //"html"
    "log"
    "net/http"
    "time"
)

func main() {

    t := time.Now()

    http.HandleFunc("/", func(w http.ResponseWriter, r *http.Request) {
        fmt.Fprintf(w, "{current-time=%q}", t.Format(time.RFC850))
    })

    log.Fatal(http.ListenAndServe(":8080", nil))

}
