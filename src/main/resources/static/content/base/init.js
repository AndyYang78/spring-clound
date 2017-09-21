
// 初始化loading
$(function () {

    var token = $('meta[name="_csrf"]').attr("content");
    var header = $('meta[name="_csrf_hader"]').attr("content");
    $(document).ajaxSend(function (e, xhr, opt) {
        xhr.setRequestHeader(header, token);
    });

    var spinner = new Spinner({
        lines: 13, // 花瓣数目
        length: 20, // 花瓣长度
        width: 10, // 花瓣宽度
        radius: 20, // 花瓣距中心半径
        corners: 1, // 花瓣圆滑度 (0-1)
        rotate: 0, // 花瓣旋转角度
        direction: 1, // 花瓣旋转方向 1: 顺时针, -1: 逆时针
        color: '#2B6FD5', // 花瓣颜色
        speed: 1, // 花瓣旋转速度
        trail: 60, // 花瓣旋转时的拖影(百分比)
        shadow: false, // 花瓣是否显示阴影
        hwaccel: false, //spinner 是否启用硬件加速及高速旋转
        className: 'spinner', // spinner css 样式名称
        zIndex: 2e9, // spinner的z轴 (默认是2000000000)
        top: '200px', // spinner 相对父容器Top定位 单位 px
        left: 'auto',// spinner 相对父容器Left定位 单位 px
        position: 'absolute'
    });

    var reqCount = 0;
    var loading = false;

    $.ajaxSetup({
        beforeSend: function (req) {
            reqCount++;
            activeLoading();
        },
        complete: function (req) {
            reqCount--;
            activeLoading();
            switch (req.status){
                case(500):
                    note('服务器异常', 'error', 5000, 'topRight');
                    break;
                case(401):
                    note('未登录', 'error', 5000, 'topRight');
                    break;
                case(404):
                    note('服务器异常', 'error', 5000, 'topRight');
                    break;
                case(408):
                    note('请求超时', 'error', 5000, 'topRight');
                    break;
            }

        },
        statusCode: {
            404: function() {
                note('数据获取/输入失败，没有此服务。404', 'error', 5000, 'topRight');
            },
            500: function() {
                note('服务器异常', 'error', 5000, 'topRight');
            }
        }
    });


    // 监测loading动画
    function activeLoading() {
        if (reqCount > 0 && !loading) {
            spinner.spin($('body').get(0));
            loading = true;
        }
        else if (reqCount == 0) {
            spinner.spin();
            loading = false;
        }
    }

});