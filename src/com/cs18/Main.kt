package com.cs18

import java.util.*

private const val NO_ERROR_DETECTED: Int = 0
private val sentDataHolder = createDefaultArray()
private val receivedDataHolder = createDefaultArray()

fun main(args: Array<String>) {
    getSentData(sentDataHolder)

    println("Full message is:")
    for (i in 0..6) print(sentDataHolder[i])

    println("\nEnter received data: ")
    for (i in 0..6) receivedDataHolder[i] = Scanner(System.`in`).nextInt()

    val detectedErrorPosition = checkIfErrorOccurred(receivedDataHolder)

    if (detectedErrorPosition == NO_ERROR_DETECTED) println("No errors occurred") else showErrorMessage(detectedErrorPosition)
}

private fun createDefaultArray(): Array<Int> = Array(7, { i -> i * 0 })

private fun getSentData(sentData: Array<Int>) {
    println("Enter the sent data: ")
    sentData[0] = Scanner(System.`in`).nextInt()
    sentData[1] = Scanner(System.`in`).nextInt()
    sentData[2] = Scanner(System.`in`).nextInt()
    sentData[4] = Scanner(System.`in`).nextInt()
    //Parity
    sentData[6] = sentData[0].xor(sentData[2]).xor(sentData[4])
    sentData[5] = sentData[0].xor(sentData[1]).xor(sentData[4])
    sentData[3] = sentData[0].xor(sentData[1]).xor(sentData[2])
}

private fun checkIfErrorOccurred(receivedData: Array<Int>): Int {
    val p1 = receivedData[6].xor(receivedData[4]).xor(receivedData[2]).xor(receivedData[0])
    val p2 = receivedData[5].xor(receivedData[4]).xor(receivedData[1]).xor(receivedData[0])
    val p3 = receivedData[3].xor(receivedData[2]).xor(receivedData[1]).xor(receivedData[0])
    return p3 * 4 + p2 * 2 + p1
}

private fun showErrorMessage(detectedErrorPosition: Int) {
    println("An error occurred at that position: " + detectedErrorPosition)

    println("Data sent:")
    for (i in 0..6) print(sentDataHolder[i])

    println("\nData received:")
    for (i in 0..6) print(receivedDataHolder[i])

    println("\nCorrect message:")
    if (receivedDataHolder[7 - detectedErrorPosition] == 0) {
        receivedDataHolder[7 - detectedErrorPosition] = 1
    } else {
        receivedDataHolder[7 - detectedErrorPosition] = 0
    }
    for (i in 0..6) print(receivedDataHolder[i])
}