package example

data class TreeNode(val data: String, val left: TreeNode? = null, val right: TreeNode? = null)

fun main(args: Array<String>) {
    val tree = TreeNode("root",
            left = TreeNode("aaa",
                    left = TreeNode("ccc",
                            left = TreeNode("ggg")
                    ),
                    right = TreeNode("ddd", right = TreeNode("hhh"))
            ),
            right = TreeNode("bbb",
                    left = TreeNode("eee"),
                    right = TreeNode("fff", left = TreeNode("iii"))
            )
    )
    println(tree)
}
