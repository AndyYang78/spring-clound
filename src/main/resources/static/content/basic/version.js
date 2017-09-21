/**
 * 标签
 * Created by yangtao on 2017/7/19.
 */
//relax, mint, sunset, metroui, semanticui, bootstrap-v3, bootstrap-v4
//top, topLeft, topCenter, topRight, center, centerLeft, centerRight, bottom, bottomLeft, bottomCenter, bottomRight
//alert, success, error, warning, info
$(function () {
    log(page);
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

function commit() {
    var name = $('#input_name').val();
    if (name === '') {
        return note('字段描述不能为空！', 'error', 1500, 'topRight');
    }
    var apiVersion = $('#input_api_version').val();
    if (apiVersion === '') {
        return note('版本号不能为空！', 'error', 1500, 'topRight');
    }
    var param = {};
    param['id'] = $('#input_id').val();
    param['name'] = name;
    param['apiVersion'] = apiVersion;
    //开始提交
    $.post('/content/version/update', param, function (response) {
        log(response);
        if (response.success) {
            $('#table_main').bootstrapTable('refresh');
            $('#modal').modal('hide');
            note('操作成功！', 'success', 1500, 'topRight');
        } else {
            note('操作失败:' + response.message, 'error', 5000, 'topRight');
        }
    })
}