import java.security.MessageDigest
import java.security.PublicKey
import java.util.*
// Name : Saleem Atef Mater
// ID : 120190460
data class Block(
    var previousHash: String = "0",
    val transactions: MutableList<Transaction> = mutableListOf(),
    val merkle_root: String = "0",
    val timestamp: Double,
    val version: Int
) {
    private val difficulty = 2
    private val validPrefix = "0".repeat(difficulty)
    var nonce: Int = 0
    var hash =
        getHash("${merkle_root}+$timestamp+${transactions.forEach { it -> it.toAddress; it.fromAddress; it.amount }}+$previousHash+$nonce")

    private fun getHash(text: String): String {
        val bytes = MessageDigest.getInstance("SHA-256").digest(text.toByteArray())
        return toHex(bytes)
    }

    private fun toHex(byteArray: ByteArray): String {
        return byteArray.joinToString("") { "%02x".format(it) }
    }

    private fun isMined(block: Block): Boolean {
        return block.hash.startsWith(validPrefix)
    }

    fun mineBlock(block: Block) {
        while (!isMined(block)) {
            nonce++
            this.hash =
                getHash("${merkle_root}+$timestamp+${transactions.forEach { it -> it.toAddress; it.fromAddress; it.amount }}+$previousHash+$nonce")
        }
    }
}

class BlockChain() {
    val chain: MutableList<Block> = mutableListOf()

    init {
        chain.add(createGensisBlock())
    }

    fun createGensisBlock(): Block {
        return Block("0", mutableListOf(Transaction("", "", 0)), "", 1.0, 0)
    }

    fun getLatesBlock(): Block {
        return chain[chain.size - 1]
    }

    fun addBlock(block: Block) {
        block.previousHash = getLatesBlock().hash
        block.mineBlock(block)
        chain.add(block)
    }


    fun getBlock(hash: String) {
        chain.forEach { it ->
            if (it.hash == hash) {
                println(
                    "hash : ${it.hash}, previousHash : ${it.previousHash} , merkle_root  : ${it.merkle_root} , nonce : ${it.nonce} , version : ${it.version} , transactions : ${
                        it.transactions.forEach { e ->
                            println(
                                "fromAddress : ${e.fromAddress} + " +
                                        "toAddress : ${e.toAddress} + " +
                                        "amount : ${e.amount}"
                            )
                        }
                    }"
                )
            }

        }
    }

    fun getAllBlock() {
        chain.forEach { println("hash : ${it.hash} , previousHash: ${it.previousHash} , merkle_root : ${it.merkle_root} , timestamp : ${it.timestamp} , nonce : ${it.nonce}") }
    }
fun getAllBlockChain():MutableList<Block> {
    return chain
}
}

data class Transaction(
    val fromAddress: String,
    val toAddress: String,
    val amount: Int,
) {

}