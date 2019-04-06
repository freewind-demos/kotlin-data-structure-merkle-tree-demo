package example

import java.nio.charset.Charset
import java.security.MessageDigest
import java.util.*


sealed class TreeNode(open val hash: String)
data class TreeBranch(override val hash: String, val left: TreeNode, val right: TreeNode) : TreeNode(hash)
data class TreeLeaf(override val hash: String) : TreeNode(hash)

val data = listOf("aaa", "bbb", "ccc", "ddd", "eee")

fun <T> makeEven(items: List<T>): List<T> {
    return if (items.size % 2 == 0) items else (items + items.last())
}

fun createMerkleTree(data: List<String>): TreeNode {
    val leaves = makeEven(data).map { TreeLeaf(it) }

    tailrec fun createBranches(nodes: List<TreeNode>): List<TreeNode> {
        return if (nodes.size == 1) {
            nodes
        } else {
            val branches = makeEven(nodes).chunked(2).map { (a, b) ->
                TreeBranch(hash256(a.hash + b.hash), a, b)
            }
            createBranches(branches)
        }
    }

    return createBranches(leaves).first()
}

fun hash256(text: String): String {
    val sha256 = MessageDigest.getInstance("SHA-256").digest(text.toByteArray())
    val base64 = Base64.getEncoder().encode(sha256).toString(Charset.defaultCharset())
    return base64
}

fun main(args: Array<String>) {
    val tree = createMerkleTree(data)
    println(tree)
}
