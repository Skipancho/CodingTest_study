package algorithm

class Bitmasking {
    var cur = 0

    //n번 원소 추가
    fun add(n : Int){
        cur = cur.or(1.shl(n))
        println(Integer.toBinaryString(cur))
    }
    //n번 원소 삭제
    fun delete(n : Int){
        cur = cur.and(1.shl(n).inv())
        println(Integer.toBinaryString(cur))
    }
    //n번 원소가 있으면 제거 없으면 추가
    fun toggle(n : Int){
        cur = cur.xor(1.shl(n))
        println(Integer.toBinaryString(cur))
    }
    //원소 존재 여부 확인
    fun exist(n : Int) : Boolean{
        if (cur.and(1.shl(n))==0){
            return false
        }
        //cur.and(1.shl(n)) == 1.shl(n)
        return true
    }
    /*
    합집합 : A | B   ->  A.or(B)
    교집합 : A & B   ->  A.and(B)
    차집합 : A & ~B  ->  A.and(B.inv())
    (A ∪ B)-(A ∩ B) : A^B -> A.xor(B)
     */

    //집합 크기
    fun bitCount(s : Int) : Int {
        if (s == 0) return 0
        return s%2 + bitCount(s/2)
    }
    //부분집합 순회
    fun subsets() : Int{
        var subset = cur
        var cnt = 0
        while (subset != 0){
            println(Integer.toBinaryString(subset))
            subset = (subset-1).and(cur)
            cnt++
        }
        return cnt
    }
}