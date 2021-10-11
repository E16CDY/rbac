package cn.wolfcode.rbac.utils;

import java.util.Collections;
import java.util.List;

public class PageResult<T> {
    //需要查询数据库
    private List data;   		//从数据库查出全部数据集
    private int totalCount; //从数据库查出总条数
    //用户输入
    private int pageSize;       //每页显示多少条数据
    private int currentPage;    //当前页

    //计算
    private int totalPage;  	//总页数(总条数/每页显示条数)
    private int prevPage;    	//上一页
    private int nextPage;   	//下一页

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPrevPage() {
        return prevPage;
    }

    public void setPrevPage(int prevPage) {
        this.prevPage = prevPage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    //提供构造器
    //有数据的情况下,调用此构造器
    public PageResult(List<T> data,int totalCount,int pageSize,int currentPage){
        this.data=data;
        this.totalCount=totalCount;
        this.pageSize=pageSize;
        this.currentPage=currentPage;

        //思考:如果查询出的总条数totalCount<=pageSize每页显示的条数,即一页即可显示完成的
        //问题:那么当前页还有上一页和下一页吗?显然没有
        //解决:做判断,如果是这种情况直接设置上一页和下一页都为1
        if (totalCount<=pageSize){
            this.currentPage=1;
            this.nextPage=1;
            this.prevPage=1;
            //直接结束,不再需要计算上一页和下一页
            return;
        }

        this.totalPage = totalCount % pageSize == 0
                ? totalCount / pageSize : totalCount / pageSize + 1;
        this.prevPage = currentPage - 1 >= 1 ? currentPage -1 : 1;
        this.nextPage = currentPage + 1 <= this.totalPage ? currentPage + 1 : this.totalPage;
    }

    //无数据的情况下调用,只传用户请求的:每页显示条数和当前页这两个参数,数据集和总条数赋予空值和0
    public PageResult(int pageSize,int currentPage){
        this(Collections.EMPTY_LIST,0,pageSize,currentPage);
    }
}