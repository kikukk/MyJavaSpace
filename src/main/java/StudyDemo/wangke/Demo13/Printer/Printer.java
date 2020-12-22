package StudyDemo.wangke.Demo13.Printer;

public class Printer {
    private IInkBox inkbox = null;  //打印机中的墨盒
    private IPaper paper = null;    //打印机中的纸张

    /**
     * 打印机的使用方法-使用墨盒和纸张
     * 参数content 传递给打印机打印的文字
     * */
    public void prinnt(String content){
        if(null == inkbox || null == paper){
            System.out.println("墨盒或纸张出现错误！重试");
            return;
        }
        //打印过程


    }

    public IInkBox getInkbox() {
        return inkbox;
    }

    public void setInkbox(IInkBox inkbox) {
        this.inkbox = inkbox;
    }

    public IPaper getPaper() {
        return paper;
    }

    public void setPaper(IPaper paper) {
        this.paper = paper;
    }





}
