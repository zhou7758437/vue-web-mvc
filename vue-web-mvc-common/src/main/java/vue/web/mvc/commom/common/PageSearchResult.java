package vue.web.mvc.commom.common;

import java.util.List;

/**
 * Author     : zh_zhou
 * Create at  : 2018/1/5 15:58
 * Description:
 */
public class PageSearchResult<T> {
    List<T> data;
    int total;
    int current;
    int limit;
    boolean finish;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public boolean isFinish() {
        return finish;
    }

    public void setFinish(boolean finish) {
        this.finish = finish;
    }
}
