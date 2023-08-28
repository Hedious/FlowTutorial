package com.example.flowtutorial

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


fun main() {

    runBlocking {

        sharedFlow()
        delay(1000L)
        println("Finished")
//        coldFlow()
//        stateFlow()
    }

}

suspend fun coldFlow() {
    val numbers = (1..5).asFlow()
    numbers.transform {
        emit("A $it")
        emit("B $it")
    }.collect { a ->
        println(a)
    }
}

suspend fun stateFlow() {
    val count = MutableStateFlow(1)
    count.value = 1

    count.update {
        it + 1
    }

    count.collect {
        println("Count Value $it")
    }
}

suspend fun sharedFlow() {
    val mySharedFlow = MutableSharedFlow<String>()

    CoroutineScope(Dispatchers.Default).launch {
        while (true) {
            mySharedFlow.emit("My Data")
            delay(500L)
        }
    }
    CoroutineScope(Dispatchers.Default).launch {
        mySharedFlow.collect {
            println("Received Data $it")
        }
    }
}


