package main

import (
	"fmt"
)

var AmountOfItems = 20

func ivanovWork(takenChan chan int) {
	for i := 0; i < AmountOfItems; i++ {
		fmt.Println("Ivanov took from storage.")
		takenChan <- 1
	}
}

func petrovWork(takenChan chan int, loadedChan chan int) {
	for i := 0; i < AmountOfItems; i++ {
		<-takenChan
		fmt.Println("Petrov loaded to lorry.")
		loadedChan <- 1
	}
}

func nechiporukWork(loadedChan chan int) {
	for i := 0; i < AmountOfItems; i++ {
		<-loadedChan
		fmt.Println("Nechiporuk counted total price.")
	}
}

func main() {
	var takenChan = make(chan int, 1)
	var loadedChan = make(chan int, 1)

	go ivanovWork(takenChan)
	go petrovWork(takenChan, loadedChan)
	go nechiporukWork(loadedChan)

	fmt.Scanln()
}