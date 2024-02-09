package cn.hanamizu.campushelp.utils.pages;

import cn.hanamizu.campushelp.utils.tools.SQLUtil;
import cn.hanamizu.campushelp.utils.text.StringUtil;
import com.github.pagehelper.PageHelper;

public class PageUtil extends PageHelper {
    /**
     * 设置请求分页数据
     */
    public static void startPage() {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        if (StringUtil.isNotNull(pageNum) && StringUtil.isNotNull(pageSize)) {
            String orderBy = SQLUtil.escapeOrderBySql(pageDomain.getOrderBy());
            Boolean reasonable = pageDomain.getReasonable();
            PageHelper.startPage(pageNum, pageSize, orderBy).setReasonable(reasonable);
        }
    }

    /**
     * 清理分页的线程变量
     */
    public static void clearPage() {
        PageHelper.clearPage();
    }
}
