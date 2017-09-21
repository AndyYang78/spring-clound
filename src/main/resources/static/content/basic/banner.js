/**
 * 标签
 * Created by yangtao on 2017/7/19.
 */
//relax, mint, sunset, metroui, semanticui, bootstrap-v3, bootstrap-v4
//top, topLeft, topCenter, topRight, center, centerLeft, centerRight, bottom, bottomLeft, bottomCenter, bottomRight
//alert, success, error, warning, info
var pre_url = 'http://photo.eryufm.cn/';

var spinner = new Spinner({
    lines: 13, // 花瓣数目
    length: 20, // 花瓣长度
    width: 10, // 花瓣宽度
    radius: 20, // 花瓣距中心半径
    corners: 1, // 花瓣圆滑度 (0-1)
    rotate: 0, // 花瓣旋转角度
    direction: 1, // 花瓣旋转方向 1: 顺时针, -1: 逆时针
    color: '#2B6FD5', // 花瓣颜色
    speed: 2.2, // 花瓣旋转速度
    trail: 60, // 花瓣旋转时的拖影(百分比)
    shadow: false, // 花瓣是否显示阴影
    hwaccel: false, //spinner 是否启用硬件加速及高速旋转
    className: 'spinner', // spinner css 样式名称
    zIndex: 2e9, // spinner的z轴 (默认是2000000000)
    top: '200px', // spinner 相对父容器Top定位 单位 px
    left: 'auto',// spinner 相对父容器Left定位 单位 px
    position: 'absolute'
});

$(function () {
    //获取上传token
    $.get('/content/banner/upload/token/qiniu', function (uploadToken) {
        Qiniu.uploader({
            runtimes: 'html5,html4',
            browse_button: 'div_img',
            container: 'form_main',
            drop_element: 'form_main',
            max_file_size: '100mb',
            dragdrop: true,
            max_retries: 3,
            chunk_size: '4mb',
            uptoken: uploadToken,
            domain: 'photo',
            auto_start: true,
            init: {
                'FilesAdded': function (up, files) {
                },
                'BeforeUpload': function (up, file) {
                    spinner.spin($('body').get(0));
                },
                'UploadProgress': function (up, file) {
                },
                'UploadComplete': function () {
                },
                'FileUploaded': function (up, file, info) {
                    log(file);
                    spinner.spin();
                    note('文件上传成功！', 'success', 500, 'topRight');
                    $('#input_img_url').text(file.name);
                    $('#img_img').attr("src", pre_url + file.name);
                },
                'Error': function (up, err, errTip) {
                    spinner.spin();
                    note('文件上传失败：' + errTip, 'error', 3000, 'topRight');
                    $('#input_img_url').text('');
                },
                'Key': function (up, file) {
                    var realName;
                    var nameItems = file.name.split('.');
                    if (nameItems.length > 1) {
                        var suffix = nameItems[nameItems.length - 1];
                        nameItems[nameItems.length - 1] = moment().format("_YYYYMMDDHHmmssSSS.");
                        nameItems.push(suffix);
                        realName = nameItems.join('');
                    } else {
                        realName = file.name + moment().format("_YYYYMMDDHHmmssSSS.unknown");
                    }
                    //去除斜杠、html标签符号、空格
                    file.name = 'banner/' + realName.replace(/\//g, '-').replace(/<\/?[^>]*>/g, "").replace(/\s/g, "");
                    return file.name;
                }
            }
        });
    });
    query();
});

function query() {
    $('#table_main').bootstrapTable('refresh');
}

function onQueryParam(param) {
    param['position'] = $('#select_position').val();
    param['usable'] = $('#select_state').val();
    return param;
}

//操作栏
function operation(value, row) {
    var html = [];
    html.push('<a type="button" class="btn btn-primary btn-xs e_edit">修改</a> ');
    html.push(!!row.usable ? '<a type="button" class="btn btn-warning btn-xs e_stop">停用</a> ' : '<a type="button" class="btn btn-success btn-xs e_open">启用</a> ');
    html.push('<a type="button" class="btn btn-danger btn-xs e_delete">删除</a> ');
    return html.join('');
}

window.operateEvents = {
    'click .e_edit': function (e, id, row) {
        $('#input_id').val(id);
        $('#input_name').val(row.name);
        $('#input_img_url').text(row.imgUrl.replace(pre_url, ''));
        $('#input_target_url').val(row.targetUrl);
        $('#input_position').val(row.position);
        $('#input_weight').val(row.weight);
        $('#img_img').attr("src", row.imgUrl);
        $('#modal').modal('show');
    },
    'click .e_stop': function (e, id) {
        $.post('/content/banner/close', {id: id}, function (response) {
            if (response.success) {
                $('#table_main').bootstrapTable('refresh');
                note('操作成功！', 'success', 1500, 'topRight');
            } else {
                note('操作失败:' + response.message, 'error', 5000, 'topRight');
            }
        });
    },
    'click .e_open': function (e, id) {
        $.post('/content/banner/open', {id: id}, function (response) {
            if (response.success) {
                $('#table_main').bootstrapTable('refresh');
                note('操作成功！', 'success', 1500, 'topRight');
            } else {
                note('操作失败:' + response.message, 'error', 5000, 'topRight');
            }
        });
    },
    'click .e_delete': function (e, id) {
        $.post('/content/banner/remove', {id: id}, function (response) {
            if (response.success) {
                $('#table_main').bootstrapTable('refresh');
                note('操作成功！', 'success', 1500, 'topRight');
            } else {
                note('操作失败:' + response.message, 'error', 5000, 'topRight');
            }
        });
    }
};

function toAdd() {
    $('#input_id').val('');
    $('#input_name').val('');
    $('#input_img_url').text('');
    $('#img_img').attr("src", "");
    $('#modal').modal('show');
}

function commit() {
    var imgUrl = $('#input_img_url').text();
    if (imgUrl === '') {
        return note('banner图片不能为空！', 'error', 1500, 'topRight');
    }
    var param = {};
    param['id'] = $('#input_id').val();
    param['name'] = $('#input_name').val();
    param['weight'] = $('#input_weight').val();
    param['position'] = $('#input_position').val();
    param['targetUrl'] = $('#input_target_url').val();
    param['imgUrl'] = pre_url + imgUrl;
    //开始提交
    $.post('/content/banner/' + (param['id'] == '' ? 'create' : 'update'), param, function (response) {
        if (response.success) {
            $('#table_main').bootstrapTable('refresh');
            $('#modal').modal('hide');
            note('操作成功！', 'success', 1500, 'topRight');
        } else {
            note('操作失败:' + response.message, 'error', 5000, 'topRight');
        }
    });
}

function formatBool(val) {
    return !!val ? '<div style="background-color: green; color: white ;width: 100%;height: 100%; border:2px solid whitesmoke; border-radius:25px; text-align: center">启用</div>' : '<div style="background-color: red; color: white ;width: 100%;height: 100%; border:2px solid whitesmoke; border-radius:25px; text-align: center">停用</div>';
}

function formatImg(url) {
    return '<img src="' + url + '" style="height: 80px; width: auto">';
}