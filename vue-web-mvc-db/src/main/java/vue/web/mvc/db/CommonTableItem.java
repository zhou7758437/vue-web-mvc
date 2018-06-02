package vue.web.mvc.db;

import vue.web.mvc.commom.util.DateFormatUtil;

import java.util.Date;

/**
 * Author     : zh_zhou
 * Create at  : 2017/12/17 10:08
 * Description:
 */
public class CommonTableItem {
    Long id;
    Date createTime;
    Date updateTime;


    public String getCreateTimeStr() {
        return DateFormatUtil.format(createTime,"yyyy-MM-dd HH:mm:ss");
    }

    public String getUpdateTimeStr() {
        return DateFormatUtil.format(updateTime,"yyyy-MM-dd HH:mm:ss");
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
