package design_pattern.observer_pattern

class ObserverB : Observer {
    override fun notify(): Boolean {
        println("ObserverB action")
        return false
    }
}