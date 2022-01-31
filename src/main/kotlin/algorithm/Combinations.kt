package algorithm

class Combinations {
    val comb = Array(1001) { IntArray(1001) }
    // nCr  -> comb[n][r]
    init {
        calc_combination()
    }
    private fun calc_combination() {
        comb[0][0] = 1
        for (i in 1..1000) {
            for (j in 0..i) {
                if (j == 0) {
                    comb[i][j] = 1
                } else {
                    comb[i][j] = (comb[i - 1][j - 1] + comb[i - 1][j]) % 1000007
                }
            }
        }
    }
}