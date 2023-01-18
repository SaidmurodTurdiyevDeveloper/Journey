package com.journey.myjourney.other

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 12/1/2022.
 */
sealed class StateViewModel<T>(var lastData: T?) {
    class Loading<T>(lastData: T?) : StateViewModel<T>(lastData)
    class Error<T>(var message: String, lastData: T?) : StateViewModel<T>(lastData)
    class Success<T>(var data: T) : StateViewModel<T>(data)
}
