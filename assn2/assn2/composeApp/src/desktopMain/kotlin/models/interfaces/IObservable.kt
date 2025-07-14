package models.interfaces

interface IObservable {
    fun addObserver(observer: IObserver)
    fun removeObserver(observer: IObserver)
}