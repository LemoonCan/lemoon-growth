package javabasic.container.collection.queue;

import java.util.PriorityQueue;

/**
 * @author lee
 * @since 2020-09-23
 */
public class ToDoList extends PriorityQueue<ToDoList.ToDoItem> {
    static class ToDoItem implements Comparable<ToDoItem>{
        private String item;
        private char primary;
        private int secondary;

        public ToDoItem(String item, char primary, int secondary) {
            this.item = item;
            this.primary = primary;
            this.secondary = secondary;
        }

        @Override
        public int compareTo(ToDoItem o) {
            if(primary>o.primary){
                return 1;
            }
            if(primary==o.primary){
                if(secondary>o.secondary){
                    return 1;
                }else if(secondary==o.secondary){
                    return 0;
                }
            }
            return -1;
        }

        @Override
        public String toString() {
            return Character.toString(primary) + secondary + ": " + item;
        }
    }

    void add(String td,char pri,int sec){
        super.add(new ToDoItem(td,pri,sec));
    }

    public static void main(String[] args) {
        ToDoList toDoList = new ToDoList();
        toDoList.add("Empty trash",'C',4);
        toDoList.add("Feed Dog",'A',2);
        toDoList.add("Feed Bird",'B',7);
        toDoList.add("Mow lawn",'C',3);
        toDoList.add("Water lawn",'A',1);
        toDoList.add("Feed Cat",'B',1);
        while (!toDoList.isEmpty()){
            System.out.println(toDoList.remove());
        }
    }
}
