import java.util.*;

//Структура дерева для задания иерархии разделов
public class EasyFileTree {

    private EasyFileTree parent = null;
    private ArrayDeque<EasyFileTree> childrens;
    private Text content;
    private int level;
    private String id;

    public void setId(String id){
        this.id = id;
    }

    public String getId(){
        return this.id;
    }
    public Text getContent(){
        return content;
    }


    public EasyFileTree(Text content){
        this.content = content;
        childrens = new ArrayDeque<EasyFileTree>();
    }


    public int getLevel(){
        return level;
    }
    public void setLevel(int level){
        this.level = level;
    }

    public EasyFileTree getParent(){
        return parent;
    }

    public void setParent(EasyFileTree tree){
        parent = tree;
    }

    public Text addChild(EasyFileTree tree, int level){         // Добавление нового элемента в дерево
        tree.setLevel(level);
        if (this.childrens.isEmpty()) {                         // Если у данного узла нет детей, то добавляем элемент в качестве дочернего
            tree.setParent(this);   tree.setId(this.id + ".1"); this.childrens.add(tree);
            tree.getContent().setId(tree.getId());
            return tree.getContent();
        }
        if (this.childrens.getLast().getLevel()==level){        // Если вводимый элемент одного уровня с детьми текущего элемента, то добавляем на тот же уровень
            tree.setParent(this);   this.childrens.add(tree); tree.setId(this.id+ "."+this.childrens.size());
            tree.getContent().setId(tree.getId());
            return tree.getContent();
        } else if (this.childrens.getLast().getLevel()<level){      // Если элемент более высшего уровня, чем текущие, то уходим выше по иерархии
            return this.childrens.getLast().addChild(tree, level);
        } else return this.childrens.getLast().getParent().addChild(tree, level); // В противном случае, уходим ниже, к корню дерева

    }
}


