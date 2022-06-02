package design_pattern.observer_pattern

class SubjectA : Subject {
    private val observerCollection = mutableListOf<Observer>()
    var num = 0

    override fun registerObserver(observer: Observer) {
        observerCollection.add(observer)
    }

    override fun unregisterObserver(observer: Observer) {
        observerCollection.remove(observer)
    }

    override fun notifyObservers() {
        observerCollection.forEach {
            it.notify()
        }
    }
}