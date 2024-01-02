package com.duongph.moneynote.action

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

abstract class BaseAction<P : BaseAction.RequestValue, R> {
    open val dispatcher: CoroutineDispatcher = Dispatchers.IO

    protected lateinit var flowCollector: FlowCollector<R>

    interface RequestValue

    class VoidRequest : RequestValue

    abstract suspend fun execute(requestValue: P): R

    suspend fun invoke(
        requestValue: P,
    ): Flow<R> {
        return flow {
            val response = withContext(Dispatchers.IO) {
                execute(requestValue)
            }
            this.emit(response)
        }
    }
}