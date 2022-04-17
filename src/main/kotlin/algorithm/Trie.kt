package algorithm

class Trie{

    data class TrieNode(
        var v : Int,
        val children : MutableMap<Char, TrieNode> = mutableMapOf()
    ){
        fun insert(word : String , value : Int){
            var curNode = this
            for(c in word){
                if(!curNode.children.containsKey(c)){
                    curNode.children[c] = TrieNode(0)
                }
                curNode = curNode.children[c]!!
            }
            curNode.v = value
        }

        fun find(word : String) : Int {
            var curNode = this
            for (c in word){
                if (curNode.children.containsKey(c)){
                    curNode = curNode.children[c]!!
                } else return 0 //default value
            }
            return curNode.v
        }
    }
}
