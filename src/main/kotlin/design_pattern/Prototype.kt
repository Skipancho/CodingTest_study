package design_pattern

class Prototype {
    fun main(){
        val man1 = Man("Tom")
        man1.children.add(Man("Jenny"))
        man1.children.add(Man("mike"))
        man1.children.add(Man("micheal"))
        val man2 = man1.clone()
        man1.print()
        println()
        man2.print()
    }

    data class Man(
        val name : String,
        val children : MutableList<Man> = mutableListOf()
    ) : Cloneable{
        public override fun clone(): Man {
            val newChildren = mutableListOf<Man>()
            children.forEach { newChildren.add(it.clone()) }
            return Man(name,newChildren)
        }
        fun print(){
            println(name)
            children.forEach { it.print() }
        }
    }
}

