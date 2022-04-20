package kcoin

import kcoin.*

fun main() {

    val blockChain = BlockChain()
    val wallet1 = Wallet.createWallet(blockChain)
    val wallet2 = Wallet.createWallet(blockChain)

    println("Wallet 1 balance: ${wallet1.balance}")
    println("Wallet 2 balance: ${wallet2.balance}")

    val tx1 = Transaction.createTransaction(fromAddress = wallet1.publicKey, toAddress = wallet1.publicKey, amount = 100)
    tx1.outputs.add(TransactionOutput(toAddress = wallet1.publicKey, amount = 100, merkle_root = tx1.hash))
    tx1.sign(wallet1.privateKey)


    var genesisBlock = Block(previousHash = "0")
    genesisBlock.addTransaction(tx1)
    genesisBlock = blockChain.addBlock(genesisBlock)

    println("Wallet 1 balance: ${wallet1.balance}")
    println("Wallet 2 balance: ${wallet2.balance}")

    val tx2 = wallet1.sendFundsTo(recipient = wallet2.publicKey, amountToSend = 33)
    val secondBlock = blockChain.addBlock(Block(genesisBlock.hash).addTransaction(tx2))
    println("Wallet 1 balance: ${wallet1.balance}")
    println("Wallet 2 balance: ${wallet2.balance}")

}