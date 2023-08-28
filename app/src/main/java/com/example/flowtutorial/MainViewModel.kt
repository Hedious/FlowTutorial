package com.example.flowtutorial

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.fold
import kotlinx.coroutines.flow.reduce
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    val countDownFlow = flow<Int> {
        val startingValue = 10
        var currentValue = startingValue
        emit(startingValue)
        while (currentValue > 0) {
            delay(1000L)
            currentValue--
            emit(currentValue)
        }
    }

    init {
        collectFlow()
    }

    private fun collectFlow() {
        val flow1 = flow {
            emit(1)
            delay(500L)
            emit(2)
        }
        val flow2 = (1..5).asFlow()
        viewModelScope.launch {
            val reduceResult = countDownFlow.fold(1) { accumulator, value ->
                accumulator + value
            }

//            flow1.flatMapConcat {
//                flow {
//                    emit(it + 1)
//                    delay(500L)
//                    emit(it + 2)
//                }
//            }.collect { value ->
//                println("mapconcat" +value)
//            }
//            println("reduceResult $reduceResult")


            //                .filter {
            //                    it % 2 == 0
            //                }
            //                .collect { time ->
            //                    println("Time is $time")
            //                }
        }
    }
}