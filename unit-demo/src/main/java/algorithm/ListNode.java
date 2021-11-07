package algorithm;

/**
 * @author lee
 * @date 2021/10/25
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        if(this.next==null){
            return "[]";
        }

        StringBuilder sb = new StringBuilder("[");
        ListNode cur = this;
        while (cur != null) {
            sb.append(cur.val);
            sb.append(",");
            cur = cur.next;
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");
        return sb.toString();
    }
}
