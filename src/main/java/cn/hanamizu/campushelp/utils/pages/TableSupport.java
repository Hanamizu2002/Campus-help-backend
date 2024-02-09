package cn.hanamizu.campushelp.utils.pages;


import cn.hanamizu.campushelp.utils.http.ServletUtil;

public class TableSupport {
    /**
     * 当前记录起始索引
     */
    public static final String PAGE_NUM = "pageNum";

    /**
     * 每页显示记录数
     */
    public static final String PAGE_SIZE = "pageSize";

    /**
     * 排序列
     */
    public static final String ORDER_BY_COLUMN = "orderByColumn";

    /**
     * 排序的方向 "desc" 或者 "asc".
     */
    public static final String IS_ASC = "isAsc";

    /**
     * 分页参数合理化
     */
    public static final String REASONABLE = "reasonable";

    /**
     * 封装分页对象
     */
    public static PageDomain getPageDomain() {
        PageDomain pageDomain = new PageDomain();
        pageDomain.setPageNum(ServletUtil.getParameterToInt(PAGE_NUM));
        pageDomain.setPageSize(ServletUtil.getParameterToInt(PAGE_SIZE));
        pageDomain.setOrderByColumn(ServletUtil.getParameter(ORDER_BY_COLUMN));
        pageDomain.setIsAsc(ServletUtil.getParameter(IS_ASC));
        pageDomain.setReasonable(ServletUtil.getParameterToBool(REASONABLE));
        return pageDomain;
    }

    public static PageDomain buildPageRequest() {
        return getPageDomain();
    }
}

