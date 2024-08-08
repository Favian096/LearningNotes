/**
* 霍夫曼编码
*/

#include<iostream>
#include<iomanip>
#include<vector>
#include <string>
#include<algorithm>
#include <utility>

using namespace std;

template<typename T>
struct BinaryTree {
public:
    T node;
    BinaryTree<T> *leftChild, *rightChild;

    BinaryTree(T value) : node(value), leftChild(nullptr), rightChild(nullptr) {}

    BinaryTree(T value, BinaryTree<T> *leftChild, BinaryTree<T> *rightChild)
            : node(value), leftChild(leftChild), rightChild(rightChild) {}
};


void binaryTreeTraverse(BinaryTree<std::pair<char, int>> *root, std::string code) {
    if (root->node.first == NULL) {
        binaryTreeTraverse(root->leftChild, code + "0");
        binaryTreeTraverse(root->rightChild, code + "1");
        return;
    }
    std::cout << root->node.first << ": " << code << std::endl;
}

/**
 * 构建哈夫曼树
 *
 * @param charSets 节点集
 */
void huffmanCode(std::vector<std::pair<char, int>> charSets) {
    // 初始化二叉树节点
    std::vector<BinaryTree<std::pair<char, int>>> nodes(1, BinaryTree<std::pair<char, int>>(charSets[0]));
    for (auto item : charSets) {
        nodes.emplace_back(BinaryTree<std::pair<char, int>>(item));
    }
    nodes.erase(nodes.begin());

    // 记录申请的内存
    std::vector<BinaryTree<std::pair<char, int>> *> nodesTmp;
    // 构建霍夫曼树
    while (nodes.size() != 1) {
        std::sort(nodes.begin(), nodes.end(),
                  [](BinaryTree<std::pair<char, int>> a, BinaryTree<std::pair<char, int>> b) -> bool {
                      return a.node.second < b.node.second;
                  });
        std::pair<char, int> parent(NULL, nodes[0].node.second + nodes[1].node.second);
        auto *leftChild = new BinaryTree<std::pair<char, int>>(nodes[0]),
                *rightChild = new BinaryTree<std::pair<char, int>>(nodes[1]);
        BinaryTree<std::pair<char, int>> parentNode(parent, leftChild, rightChild);
        nodesTmp.push_back(leftChild);
        nodesTmp.push_back(rightChild);
        nodes.erase(nodes.begin(), nodes.begin() + 2);
        nodes.insert(nodes.begin(), parentNode);
    }

    std::basic_string<char> tmp;
    binaryTreeTraverse(&nodes[0], tmp);
    //释放内存
    std::for_each(nodesTmp.begin(), nodesTmp.end(), [](auto item) { delete item; });
}

int main() {
    // 源数据集
    vector<pair<char, int>> charSets = {
            pair<char, int>('a', 45),
            pair<char, int>('b', 13),
            pair<char, int>('c', 12),
            pair<char, int>('d', 16),
            pair<char, int>('e', 9),
            pair<char, int>('f', 5)
    };

    huffmanCode(charSets);

    return 0;
}