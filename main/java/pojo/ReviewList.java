package pojo;

public class ReviewList {
    private String root;
    private String[] list;

    public ReviewList(String root, String[] list) {
        this.root = root;
        this.list = list;
    }

    public boolean isEmpty(){
        if (this.list!=null&&this.list.length!=0&&this.root!=null&&this.root.length()!=0)
            return false;
        else return true;
    }

    public String[] getList() {
        return list;
    }
}
