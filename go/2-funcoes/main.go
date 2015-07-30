package main

import "fmt"

func main() {
  xs := []float64{456,4,4563,456,8}

  fmt.Println(media(xs))
}


func media(xs []float64) float64 {
  
  total := 0.0
  for _, v := range xs {
    total += v
  }
  return total / float64(len(xs))
}