import com.google.gson.Gson;

import java.io.File;
import java.util.ArrayList;

public class Test2 {
    public static void main(String[] args) {
        File file=new File("E:\\Spring案例代码\\Spring4\\");
        System.out.println(new Gson().toJson(file.list()));
       /* File[] tempList = file.listFiles();
        ArrayList<String> list=new ArrayList<>();
        try{
            for (File file1: tempList){
                list.add(file1.getName());
            }
        }catch (NullPointerException e){
            list=null;
        }*/
    }
}
