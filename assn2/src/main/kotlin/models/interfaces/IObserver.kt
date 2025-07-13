package models.interfaces

interface IObserver<T> where T : IObservable {
    fun update(observable: T)
}