package nimbl3.com.sokies.Activity;

/**
 * Created by MY on 10/31/2017.
 */

public class order_list {
    public String getDate1() {
        return date1;
    }

    public void setDate1(String date1) {
        this.date1 = date1;
    }

    private String date1;

    public String getOrder_no() {
        return order_no;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getProject_manager() {
        return project_manager;
    }

    public void setProject_manager(String project_manager) {
        this.project_manager = project_manager;
    }

    private String project_manager;
    private String order_no;
    private String date;
}
