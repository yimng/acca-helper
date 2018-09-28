package com.thinkgem.jeesite.common.persistence;

import com.github.pagehelper.PageSerializable;

import java.util.Collection;
import java.util.List;
import com.github.pagehelper.Page;

/**
 * 对Page<E>结果进行包装
 * <p/>
 * 新增分页的多项属性，主要参考:http://bbs.csdn.net/topics/360010907
 *
 * @author liuzh/abel533/isea533
 * @version 3.3.0
 * @since 3.2.2
 * 项目地址 : http://git.oschina.net/free/Mybatis_PageHelper
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class PageInfo<T> extends PageSerializable<T> {
    //当前页
    private int pageNo;
    //每页的数量
    private int pageSize;
    //当前页的数量
    private int size;

    //由于startRow和endRow不常用，这里说个具体的用法
    //可以在页面中"显示startRow到endRow 共size条数据"

    //当前页面第一个元素在数据库中的行号
    private int startRow;
    //当前页面最后一个元素在数据库中的行号
    private int endRow;
    //总页数
    private int pages;

    //前一页
    private int prePage;
    //下一页
    private int nextPage;

    //是否为第一页
    private boolean isFirstPage = false;
    //是否为最后一页
    private boolean isLastPage = false;
    //是否有前一页
    private boolean hasPreviousPage = false;
    //是否有下一页
    private boolean hasNextPage = false;
    //导航页码数
    private int navigatePages;
    //所有导航页号
    private int[] navigatepageNos;
    //导航条上的第一页
    private int navigateFirstPage;
    //导航条上的最后一页
    private int navigateLastPage;

    private String funcName = "page";
    private String funcParam = "";
    private int slider = 1;// 前后显示页面长度

    public PageInfo() {
    }

    /**
     * 包装Page对象
     *
     * @param list
     */
    public PageInfo(List<T> list) {
        this(list, 8);
    }

    /**
     * 包装Page对象
     *
     * @param list          page结果
     * @param navigatePages 页码数量
     */
    public PageInfo(List<T> list, int navigatePages) {
        super(list);
        if (list instanceof Page) {
            Page page = (Page) list;
            this.pageNo = page.getPageNum();
            this.pageSize = page.getPageSize();

            this.pages = page.getPages();
            this.size = page.size();
            //由于结果是>startRow的，所以实际的需要+1
            if (this.size == 0) {
                this.startRow = 0;
                this.endRow = 0;
            } else {
                this.startRow = page.getStartRow() + 1;
                //计算实际的endRow（最后一页的时候特殊）
                this.endRow = this.startRow - 1 + this.size;
            }
        } else if (list instanceof Collection) {
            this.pageNo = 1;
            this.pageSize = list.size();

            this.pages = this.pageSize > 0 ? 1 : 0;
            this.size = list.size();
            this.startRow = 0;
            this.endRow = list.size() > 0 ? list.size() - 1 : 0;
        }
        if (list instanceof Collection) {
            this.navigatePages = navigatePages;
            //计算导航页
            calcNavigatepageNos();
            //计算前后页，第一页，最后一页
            calcPage();
            //判断页面边界
            judgePageBoudary();
        }
    }

    public static <T> PageInfo<T> of(List<T> list) {
        return new PageInfo<T>(list);
    }

    public static <T> PageInfo<T> of(List<T> list, int navigatePages) {
        return new PageInfo<T>(list, navigatePages);
    }

    /**
     * 计算导航页
     */
    private void calcNavigatepageNos() {
        //当总页数小于或等于导航页码数时
        if (pages <= navigatePages) {
            navigatepageNos = new int[pages];
            for (int i = 0; i < pages; i++) {
                navigatepageNos[i] = i + 1;
            }
        } else { //当总页数大于导航页码数时
            navigatepageNos = new int[navigatePages];
            int startNum = pageNo - navigatePages / 2;
            int endNum = pageNo + navigatePages / 2;

            if (startNum < 1) {
                startNum = 1;
                //(最前navigatePages页
                for (int i = 0; i < navigatePages; i++) {
                    navigatepageNos[i] = startNum++;
                }
            } else if (endNum > pages) {
                endNum = pages;
                //最后navigatePages页
                for (int i = navigatePages - 1; i >= 0; i--) {
                    navigatepageNos[i] = endNum--;
                }
            } else {
                //所有中间页
                for (int i = 0; i < navigatePages; i++) {
                    navigatepageNos[i] = startNum++;
                }
            }
        }
    }

    /**
     * 计算前后页，第一页，最后一页
     */
    private void calcPage() {
        if (navigatepageNos != null && navigatepageNos.length > 0) {
            navigateFirstPage = navigatepageNos[0];
            navigateLastPage = navigatepageNos[navigatepageNos.length - 1];
            if (pageNo > 1) {
                prePage = pageNo - 1;
            }
            if (pageNo < pages) {
                nextPage = pageNo + 1;
            }
        }
    }

    /**
     * 判定页面边界
     */
    private void judgePageBoudary() {
        isFirstPage = pageNo == 1;
        isLastPage = pageNo == pages || pages == 0;
        hasPreviousPage = pageNo > 1;
        hasNextPage = pageNo < pages;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getEndRow() {
        return endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getPrePage() {
        return prePage;
    }

    public void setPrePage(int prePage) {
        this.prePage = prePage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public boolean isIsFirstPage() {
        return isFirstPage;
    }

    public void setIsFirstPage(boolean isFirstPage) {
        this.isFirstPage = isFirstPage;
    }

    public boolean isIsLastPage() {
        return isLastPage;
    }

    public void setIsLastPage(boolean isLastPage) {
        this.isLastPage = isLastPage;
    }

    public boolean isHasPreviousPage() {
        return hasPreviousPage;
    }

    public void setHasPreviousPage(boolean hasPreviousPage) {
        this.hasPreviousPage = hasPreviousPage;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public int getNavigatePages() {
        return navigatePages;
    }

    public void setNavigatePages(int navigatePages) {
        this.navigatePages = navigatePages;
    }

    public int[] getNavigatepageNos() {
        return navigatepageNos;
    }

    public void setNavigatepageNos(int[] navigatepageNos) {
        this.navigatepageNos = navigatepageNos;
    }

    public int getNavigateFirstPage() {
        return navigateFirstPage;
    }

    public int getNavigateLastPage() {
        return navigateLastPage;
    }

    public void setNavigateFirstPage(int navigateFirstPage) {
        this.navigateFirstPage = navigateFirstPage;
    }

    public void setNavigateLastPage(int navigateLastPage) {
        this.navigateLastPage = navigateLastPage;
    }

//    @Override
//    public String toString() {
//        final StringBuilder sb = new StringBuilder("PageInfo{");
//        sb.append("pageNo=").append(pageNo);
//        sb.append(", pageSize=").append(pageSize);
//        sb.append(", size=").append(size);
//        sb.append(", startRow=").append(startRow);
//        sb.append(", endRow=").append(endRow);
//        sb.append(", total=").append(total);
//        sb.append(", pages=").append(pages);
//        sb.append(", list=").append(list);
//        sb.append(", prePage=").append(prePage);
//        sb.append(", nextPage=").append(nextPage);
//        sb.append(", isFirstPage=").append(isFirstPage);
//        sb.append(", isLastPage=").append(isLastPage);
//        sb.append(", hasPreviousPage=").append(hasPreviousPage);
//        sb.append(", hasNextPage=").append(hasNextPage);
//        sb.append(", navigatePages=").append(navigatePages);
//        sb.append(", navigateFirstPage=").append(navigateFirstPage);
//        sb.append(", navigateLastPage=").append(navigateLastPage);
//        sb.append(", navigatepageNos=");
//        if (navigatepageNos == null) {
//            sb.append("null");
//        } else {
//            sb.append('[');
//            for (int i = 0; i < navigatepageNos.length; ++i) {
//                sb.append(i == 0 ? "" : ", ").append(navigatepageNos[i]);
//            }
//            sb.append(']');
//        }
//        sb.append('}');
//        return sb.toString();
//    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        if (pageNo == 1) {// 如果是首页
            sb.append("<li class=\"disabled\"><a href=\"javascript:\">&#171; 上一页</a></li>\n");
        } else {
            sb.append("<li><a href=\"javascript:\" onclick=\""+funcName+"("+prePage+","+pageSize+",'"+funcParam+"');\">&#171; 上一页</a></li>\n");
        }

        int begin = pageNo - (8 / 2);

        if (begin < 1) {
            begin = 1;
        }

        int end = begin + 8 - 1;

        if (end >= pages) {
            end = pages;
            begin = end - 8 + 1;
            if (begin < 1) {
                begin = 1;
            }
        }

        if (begin > 1) {
            int i = 0;
            for (i = 1; i < 1 + slider && i < begin; i++) {
                sb.append("<li><a href=\"javascript:\" onclick=\""+funcName+"("+i+","+pageSize+",'"+funcParam+"');\">"
                        + (i + 1 - 1) + "</a></li>\n");
            }
            if (i < begin) {
                sb.append("<li class=\"disabled\"><a href=\"javascript:\">...</a></li>\n");
            }
        }

        for (int i = begin; i <= end; i++) {
            if (i == pageNo) {
                sb.append("<li class=\"active\"><a href=\"javascript:\">" + (i + 1 - 1)
                        + "</a></li>\n");
            } else {
                sb.append("<li><a href=\"javascript:\" onclick=\""+funcName+"("+i+","+pageSize+",'"+funcParam+"');\">"
                        + (i + 1 - 1) + "</a></li>\n");
            }
        }

        if (pages - end > slider) {
            sb.append("<li class=\"disabled\"><a href=\"javascript:\">...</a></li>\n");
            end = pages - slider;
        }

        for (int i = end + 1; i <= pages; i++) {
            sb.append("<li><a href=\"javascript:\" onclick=\""+funcName+"("+i+","+pageSize+",'"+funcParam+"');\">"
                    + (i + 1 - 1) + "</a></li>\n");
        }

        if (pageNo == pages) {
            sb.append("<li class=\"disabled\"><a href=\"javascript:\">下一页 &#187;</a></li>\n");
        } else {
            sb.append("<li><a href=\"javascript:\" onclick=\""+funcName+"("+nextPage+","+pageSize+",'"+funcParam+"');\">"
                    + "下一页 &#187;</a></li>\n");
        }

        sb.append("<li class=\"disabled controls\"><a href=\"javascript:\">当前 ");
        sb.append("<input type=\"text\" value=\""+pageNo+"\" onkeypress=\"var e=window.event||event;var c=e.keyCode||e.which;if(c==13)");
        sb.append(funcName+"(this.value,"+pageSize+",'"+funcParam+"');\" onclick=\"this.select();\"/> / ");
        sb.append("<input type=\"text\" value=\""+pageSize+"\" onkeypress=\"var e=window.event||event;var c=e.keyCode||e.which;if(c==13)");
        sb.append(funcName+"("+pageNo+",this.value,'"+funcParam+"');\" onclick=\"this.select();\"/> 条，");
        sb.append("共 " + total + " 条"+"</a></li>\n");

        sb.insert(0,"<ul>\n").append("</ul>\n");

        sb.append("<div style=\"clear:both;\"></div>");

//		sb.insert(0,"<div class=\"page\">\n").append("</div>\n");

        return sb.toString();
    }
}