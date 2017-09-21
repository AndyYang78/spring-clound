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
                    log('spin');
                },
                'UploadProgress': function (up, file) {
                },
                'UploadComplete': function () {
                },
                'FileUploaded': function (up, file, info) {
                    spinner.spin();
                    note('文件上传成功！', 'success', 500, 'topRight');
                    $('#input_img_url').text(file.name);
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
                    file.name = 'app/apk/' + realName.replace(/\//g, '-').replace(/<\/?[^>]*>/g, "").replace(/\s/g, "");
                    return file.name;
                }
            }
        });
    });
});

function query() {
    $('#table_main').bootstrapTable('refresh');
}

//操作栏
function operation(value, row) {
    var html = [];
    html.push('<a type="button" class="btn btn-primary btn-xs edit">修改</a> ');
    return html.join('');
}

window.operateEvents = {
    'click .edit': function (e, id, row) {
        $('#input_id').val(id);
        $('#input_short_name').val(row.shortName);
        $('#input_api_version').val(row.apiVersion);
        $('#input_name').val(row.name);
        $('#input_path').val(row.path);
        $('#modal').modal('show');
    }
};

//String name, String downloadUrl, String description, String descriptionUrl, Boolean force
function commit() {
    var param = {};
    if ((param['name'] = $('#input_name').val()) == '')
        return note('版本名称不能为空！', 'error', 1500, 'topRight');
    if ((param['downloadUrl'] = pre_url + $('#input_img_url').text()) == '')
        return note('下载地址不能为空！', 'error', 1500, 'topRight');
    param['description'] = $('#input_description').val();
    param['versionNumber'] = $('#input_version_number').val();
    param['descriptionUrl'] = $('#input_description_url').val();
    param['force'] = $('input[name="input_force"]:checked').val();
    //开始提交
    $.post('/content/app/android/version/create', param, function (response) {
        if (response.success) {
            $('#table_main').bootstrapTable('refresh');
            $('#modal').modal('hide');
            note('操作成功！', 'success', 1500, 'topRight');
            $('#modal input').val('');
            $('#input_img_url').text('点我上传');
        } else {
            note('操作失败:' + response.message, 'error', 5000, 'topRight');
        }
    })
}

function formatBool(value) {
    return value ? '<div style="background-color: green; color: white ;width: 100%;height: 100%; border:2px solid whitesmoke; border-radius:25px; text-align: center">是</div>' : '<div style="background-color: #848187; color: white ;width: 100%;height: 100%; border:2px solid whitesmoke; border-radius:25px; text-align: center">否</div>';
}