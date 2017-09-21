/**
 * Created by marco on 2017/2/13.
 */
// jquery.dataTables.js 组件默认配置
// $.extend(true, $.fn.dataTable.defaults, {
//     "processing": true,
//     "serverSide": true,
//     "searching": false,
//     "ordering": false,
//     "language": {
//         "sLengthMenu": "每页显示_MENU_条",
//         "info": "显示第_PAGE_至_PAGES_页, 共_TOTAL_页",
//         "sProcessing": "数据处理中...",
//         "sLoadingRecords": "数据加载中...",
//         "search": "",
//         "paginate": {
//             "previous": "上一页",
//             "next": "下一页"
//         }
//     }
// });

$(function () {
    var url = getUrlRelativePath();
    var item = $('li a[href="' + url + '"]');
    item.parent().addClass('active');
    item.parent().addClass('active');
    item.parent().parent().addClass('menu-open');
    item.parent().parent().parent().addClass('active');
});

function getUrlRelativePath() {
    var arrUrl = document.location.toString().split("//");
    var start = arrUrl[1].indexOf("/");
    var relUrl = arrUrl[1].substring(start);
    if (relUrl.indexOf("?") != -1) {
        relUrl = relUrl.split("?")[0];
    }
    return relUrl;
}

function notEmpty(element) {
    return element != null && element != '' && element.length > 0;
}

function isEmpty(element) {
    return !(notEmpty(element));
}

$.extend(true, $.fn.bootstrapTable.defaults, {
    'dataField': 'data'
});

//日期范围选择框汉化
var DATE_LOCAL = {
    "format": "YYYY-MM",
    "daysOfWeek": ["日", "一", "二", "三", "四", "五", "六"],
    "monthNames": ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
    "firstDay": 1
};

//日期范围选择框汉化
var DATE_LOCAL_DAY = {
    "format": "YYYY-MM-DD",
    "daysOfWeek": ["日", "一", "二", "三", "四", "五", "六"],
    "monthNames": ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
    "firstDay": 1
};

function log(text) {
    console && console.log(text);
}

var YPP = {

    formatter: {

        AMOUNT_NUMBER_DECIMALS: 1,

        // 整形数字格式化
        integer: function (value, row, index) {
            if(value==null){
                return '-';
            }

            return $.number(value, 0);
        },

        // 加百分比
        percent: function (value, row, index) {
            return value + '%';
        },

        // 加百分比
        percentTwo: function (value, row, index) {
            return value == null ? '-' : value.toFixed(2) + '%';
        },

        // 格式化1位小数
        percentOne: function (value, row, index) {
            return value == null ? '-' : value.toFixed(1);
        },

        // 整形数字格式化
        footerInteger: function (data) {
            var total = 0;
            var field = this.field;
            $(data).each(function (index, item) {
                total += data[index][field];
            });
            return $.number(total, 0);
        },

        // 金额数字格式化
        amount: function (value, row, index) {
            if (value == 0 || value == 0.0) {
                return '-'
            }

            return $.number(value, YPP.formatter.AMOUNT_NUMBER_DECIMALS);
        },
        // 金额数字格式化
        persent: function (value, row, index) {
            if (value == 0) {
                return '-'

            }
            if (value != null || value != '') {
                return value.toFixed(1) + '%';
            }
            return value + '%';

        },

        // 金额数字格式化
        footerAmount: function (data) {
            var total = 0;

            var field = this.field;
            $(data).each(function (index, item) {
                total += data[index][field];
            });
            if (total == 0 || total == 0.0) {
                return '-'
            }
            return $.number(total, YPP.formatter.AMOUNT_NUMBER_DECIMALS);
        },
        // 流水总计变化金额数字格式化
        footerAmountCash: function (data) {
            var total = 0;
            var field = this.field;
            $(data).each(function (index, item) {
                var reg = /[^\d]/g;
                data[index][field] = parseInt(data[index][field].toString().replace(reg, ""))
                total += data[index][field];
            });
            return $.number(total, YPP.formatter.AMOUNT_NUMBER_DECIMALS);
        },
        // 数字百分比输出
        footerPer: function (data) {
            var total = 0;
            var field = this.field;
            if (data == null || data.length == 0) {
                return 0;
            }

            $(data).each(function (index, item) {
                total += data[index][field];
            });
            if (total == 0) {
                return '-';

            }
            return (total / data.length).toFixed(1) + '%';
        },
        // 数字平均输出输出
        footerPered: function (data) {
            var total = 0;
            var field = this.field;
            $(data).each(function (index, item) {
                total += data[index][field];
            });
            return (total / data.length).toFixed(1);
        },
        //arpu 求均值
        footerPerArpu: function (data) {
            var total = 0;


            var field = this.field;
            $(data).each(function (index, item) {
                total += data[index][field];
            });
            return (total / data.length).toFixed(1);
        },

        // 整形数字平均输出输出
        footerPerInteger: function (data) {
            var total = 0;
            var field = this.field;
            $(data).each(function (index, item) {
                total += data[index][field];
            });
            return $.number(total / data.length);
        },
        // 金额数字平均输出输出
        footerPerIntegers: function (data) {
            var total = 0;
            var field = this.field;
            $(data).each(function (index, item) {
                total += data[index][field];
            });
            return $.number(total / data.length).concat('.0');
        },

        // 用户状态
        userStatus: function (value, row, index) {
            return value == 1 ? "<span style='color: green'>已激活</span>" : (value == 0 ? "<span style='color: yellow'>未激活</span>" : "<span style='color: red'>冻结</span>");
        },
        // 是否设置密码
        hasPassword: function (value, row, index) {
            return value == 1 ? '否' : (value == 0 ? '是' : '未知');
        },

        // 用户类型
        userType: function (value, row, index) {
            return value == 1 ? '普通用户' : '大神';
        },

        // 用户状态
        gender: function (value, row, index) {
            return value == 1 ? '男' : (value == 0 ? '女' : '未知');
        },
        // 用户头像
        avatar: function (value, row, index) {
            if (value == null || value == '') {
                return '-'
            }
            //看是否包含http
            if (value.indexOf("http") == 0) {
                return '<img class="choose_on" src=' + value + ' style="height: 50px;width: 45px;">';
            }
            return '<img class="choose_on" src=http://photo.eryufm.cn/' + value + ' style="height: 50px;width: 45px;">';
        }
    }
};


    var T_TIME = 1500;
    var W_TIME = 3500;
    var F_TIME = 5000;
    function note(text, type, timeout, layout, theme) {
        //relax, mint, sunset, metroui, semanticui, bootstrap-v3, bootstrap-v4
        if (!theme)
            theme = 'metroui';
        var mojsShow = function (promise) {
            var n = this
            var Timeline = new mojs.Timeline()
            var body = new mojs.Html({
                el: n.barDom,
                x: {500: 0, delay: 0, duration: 500, easing: 'elastic.out'},
                isForce3d: true,
                onComplete: function () {
                    promise(function (resolve) {
                        resolve()
                    })
                }
            })

            var parent = new mojs.Shape({
                parent: n.barDom,
                width: 200,
                height: n.barDom.getBoundingClientRect().height,
                radius: 0,
                x: {[150]: -150},
                duration: 1.2 * 500,
                isShowStart: true
            })

            n.barDom.style['overflow'] = 'visible'
            parent.el.style['overflow'] = 'hidden'

            var burst = new mojs.Burst({
                parent: parent.el,
                count: 10,
                top: n.barDom.getBoundingClientRect().height + 75,
                degree: 90,
                radius: 75,
                angle: {[-90]: 40},
                children: {
                    fill: '#EBD761',
                    delay: 'stagger(500, -50)',
                    radius: 'rand(8, 25)',
                    direction: -1,
                    isSwirl: true
                }
            })

            const fadeBurst = new mojs.Burst({
                parent: parent.el,
                count: 2,
                degree: 0,
                angle: 75,
                radius: {0: 100},
                top: '90%',
                children: {
                    fill: '#EBD761',
                    pathScale: [.65, 1],
                    radius: 'rand(12, 15)',
                    direction: [-1, 1],
                    delay: .8 * 500,
                    isSwirl: true
                }
            })

            Timeline.add(body, burst, fadeBurst, parent)
            Timeline.play()
        };
        var mojsClose = function (promise) {
            var n = this
            new mojs.Html({
                el: n.barDom,
                x: {0: 500, delay: 10, duration: 500, easing: 'cubic.out'},
                isForce3d: true,
                onComplete: function () {
                    promise(function (resolve) {
                        resolve()
                    })
                }
            }).play()
        };
        new Noty({
            type: type,
            layout: layout,
            theme: theme,
            text: text,
            timeout: timeout,
            progressBar: true,
            closeWith: ['click', 'button'],
            id: false,
            force: false,
            killer: false,
            queue: 'global',
            container: false,
            buttons: [],
            sounds: {
                sources: [],
                volume: 1,
                conditions: []
            },
            titleCount: {
                conditions: []
            },
            modal: false,
            animation: {
                open: mojsShow,
                close: mojsClose
            }
        }).show();

    }

    //消息提示全局配置
    toastr.options = {
        "closeButton": false,//是否配置关闭按钮
        "debug": false,//是否开启debug模式
        "newestOnTop": false,//新消息是否排在最上层
        "progressBar": true,//是否显示进度条
        "positionClass": "toast-top-right",//消息框的显示位置
        "preventDuplicates": false,//是否阻止弹出多个消息框
        "onclick": null,//点击回调函数
        "showDuration": "3000",
        "hideDuration": "1000",
        "timeOut": "1500",//1.5s后关闭消息框
        "extendedTimeOut": "1000",
        "showEasing": "swing",
        "hideEasing": "linear",
        "showMethod": "fadeIn",
        "hideMethod": "fadeOut"
    }
