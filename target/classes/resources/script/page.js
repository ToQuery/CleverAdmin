/**
 * 创建分页html数据
 */
function createHtml(pageData) {
        var pageHtml = "<ul class='pagination pull-right no-margin'>";
        pageHtml += "<li><a>共<font color=red>" + pageData.total + "</font>条</a></li>";
        pageHtml += " <li><input type='number' value='' id='toGoPage' style='width:50px;text-align:center;float:left' placeholder='页码'/></li>";
        pageHtml += "<li style='cursor:pointer;'><a onclick='toTZ();' class='btn btn-mini btn-success'>跳转</a></li>";
        if ( pageData.pageNum <= 1 ){
            pageHtml += "<li style='cursor:not-allowed;'><a >首页</a></li>";
            pageHtml += "<li style='cursor:not-allowed;'><a >上页</a></li>";
        }else{
            pageHtml += "<li style='cursor:pointer;'><a onclick='nextPage(1)'>首页</a></li>";
            pageHtml += "<li style='cursor:pointer;'><a onclick='nextPage(" + pageData.pageNum + " )'>上页</a></li>";
        }
        var showTag = 5;//分页标签显示数量
        var startTag = 1;
        if( pageData.pages > showTag ){
            startTag = pageData.pages - 1;
        }
        var endTag = startTag + showTag - 1;
        for(var i = startTag;  i <= endTag; i++){
            if ( pageData.pageNum == i ) {
                // 当前页码
                pageHtml += "<li class='active'><a><font color='white'>" + i + "</font></a></li>";
            } else if ( i > pageData.pages ){
                //数据数量小于分页标签显示数量（5）
                pageHtml += "<li style='cursor: not-allowed;'><a>" + i + "</a></li>";
            } else {
                pageHtml += "<li style='cursor:pointer;'><a onclick='nextPage("+i+")'>" + i + "</a></li>";
            }
        }
        // todo bug 已经到了最后一页，还能继续下一页
        if ( pageData.pages <= 1 ){
            pageHtml += "<li style='cursor:not-allowed;'><a >下页</a></li>";
            pageHtml += "<li style='cursor:not-allowed;'><a >尾页</a></li>";
        } else {
            pageHtml += "<li style='cursor:pointer;'><a onclick='nextPage(" + pageData.pageNum + 1 + ")'>下页</a></li>";
            pageHtml += "<li style='cursor:pointer;'><a onclick='nextPage(" + pageData.pages + ")'>尾页</a></li>";
        }

        pageHtml += "<li><a>共" + pageData.pages + "页</a></li>";
        pageHtml += "<li><select title='显示条数' style='width:55px;float:left;margin-top:1px;' onchange='changeCount(this.value)'>";
        pageHtml += "<option value='" + pageData.pageSize + "'>" + pageData.pageSize + "</option>";
        pageHtml += "<option value='10'>10</option><option value='20'>20</option>";
        pageHtml += "</select></li></ul>";
        return pageHtml;
    }



function nextPage(page) {
    top.jzts();
    var url = document.location + '';
    if (url.indexOf('?') > -1) {
        if (url.indexOf('pageNum') > -1) {
            var reg = /pageNum=\d*/g;
            url = url.replace(reg, 'pageNum=');
        } else {
            url += "&pageNum=";
        }
    } else {
        url += "?pageNum=";
    }
    url = url + page + "&pageSize=" + pageHelper.pageSize;
    document.location = url;
}

function changeCount(value) {
    top.jzts();
    var url = document.location + '';
    if (url.indexOf('?') > -1) {
        if (url.indexOf('pageNum') > -1) {
            var reg = /pageNum=\d*/g;
            url = url.replace(reg, 'pageNum=');
        } else {
            url += "1&pageNum=";
        }
    } else {
        url += "?pageNum=";
    }
    url = url + "1&pageSize=" + value;
    console.info(url);
    document.location = url;
}

function toTZ() {
    var toPaggeVlue = document.getElementById("toGoPage").value;
    if (toPaggeVlue == '') {
        document.getElementById("toGoPage").value = 1;
        return;
    }
    if (isNaN(Number(toPaggeVlue))) {
        document.getElementById("toGoPage").value = 1;
        return;
    }
    nextPage(toPaggeVlue);
}
