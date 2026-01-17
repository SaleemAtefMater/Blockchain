import java.util.*
import java.io.File
import java.io.PrintWriter

// Name : Saleem Atef Mater
// ID : 120232916
fun main() {
    var block = BlockChain()
    block.addBlock(
        Block(
            "0", mutableListOf(Transaction("saleem", "ass", 0)), "s",
            6.0, 1
        )
    )
    block.addBlock(
        Block(
            "2", mutableListOf(Transaction("sale2em", "a3ss", 1)), "f",
            5.0, 1
        )
    )

    block.getAllBlock()
    println("----------------------------------------------------------------")
    block.getBlock("00ce9beb376fc16d8fb5c0374177f7cbb094dcc4d12c83568aa4648f0556cbda")


    val fileName = "D:\\BlockChain/BlockChain.txt"
    var fileObject = File(fileName)
    var fileExists = fileObject.exists()
    var lines = fileObject.readLines()
    if (fileExists) {
        print("$fileName file does exist.\n")
        block.getAllBlockChain().forEach {
            // read file
            for (i in lines) {
                println("${it.hash} +$i")
            }
            // write file
            if (!(lines.contains(it.hash))) {
                fileObject.appendText("hash : ${it.hash} previousHash: ${it.previousHash} , merkle_root : ${it.merkle_root} , timestamp : ${it.timestamp} , nonce : ${it.nonce} \n")
            }
        }

    } else {
        fileObject.createNewFile()
        print("$fileName file does not exist.")
    }

}
