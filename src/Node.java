import java.util.ArrayList;

public class Node implements Comparable<Node>{

    private int x, y;
    private int g=0, h=0;
    private Node parent;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public ArrayList<Node> getChildren() {
        // x+1,y - right
        // x-1,y - left
        // x,y-1 - up
        // x,y+1 - down
        Node right = GameBoardManager.getNode(x + 1, y);
        Node left = GameBoardManager.getNode(x - 1, y);
        Node up = GameBoardManager.getNode(x, y - 1);
        Node down = GameBoardManager.getNode(x, y + 1);

        ArrayList<Node> children = new ArrayList<>();

        if (right != null) children.add(right);
        if (left != null) children.add(left);
        if (up != null) children.add(up);
        if (down != null) children.add(down);

        return children;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public int getG() {
        return g;
    }

    public int getH() {
        return h;
    }

    public void setG(int g) {
        this.g = g;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getF() {
        return g + h;
    }

    @Override
    public boolean equals(Object obj){
        return (obj instanceof Node) && x == ((Node)obj).x && y == ((Node)obj).y;

    }

    @Override
    public int compareTo(Node o) {
        if (o.getF() == getF()){
            return h - o.h;
        }
        else{
            return getF() - o.getF();
        }
    }
}
