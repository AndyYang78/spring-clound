/**
 * 标签
 * Created by yangtao on 2017/7/19.
 */
//relax, mint, sunset, metroui, semanticui, bootstrap-v3, bootstrap-v4
//top, topLeft, topCenter, topRight, center, centerLeft, centerRight, bottom, bottomLeft, bottomCenter, bottomRight
//alert, success, error, warning, info
$(function () {
    log(page);
    // $(".js-example-basic-single").select2({placeholder: '选择用户的角色信息', allowClear: true});
    // $.get('/content/system/role/list', function (response) {
    //     if (response.success) {
    //         for (var i in response.data) {
    //             $('#input_roles').append('<option value=' + response.data[i].id + '>' + response.data[i].name + '</option>');
    //         }
    //     } else {
    //         note('获取货主信息失败:' + response.message, 'error', F_TIME, 'topRight');
    //     }
    // });
});

/**
 * 数据获取
 */
function query() {
    $('.div_table_content').hide();
    $('#btn_export').fadeOut('slow');
    $('#table_main').bootstrapTable('refresh');
}

//操作栏
function operation(value, row) {
    var html = [];
    html.push('<a type="button" class="btn btn-primary btn-xs edit">编辑</a> ');
    html.push('<a type="button" class="btn btn-danger btn-xs remove">删除</a>');
    return html.join('');
}

var event_flag = 'create';

//操作事件
window.operateEvents = {
    //编辑
    'click .edit': function (e, value, row) {
        event_flag = 'update';
        $('#input_id').val(value);
        $('#input_name').val(row.name);
        $('#input_password').val('');
        $("#input_password").attr("title", "为空表示不修改密码！");
        $("#input_password").attr("placeholder", "不填表示不修改密码！");
        var roles = [];
        for (var i in row.roles)
            roles.push(row.roles[i].id);
        $('#input_roles').val(roles);
        $('#input_roles').trigger('change');
        $('#modal_add').modal('show');
    },
    //删除
    'click .remove': function (e, value) {
        $.post('/content/express/company/remove', {id: value}, function (response) {
            if (response.success) {
                $('#table_main').bootstrapTable('refresh');
                note('删除成功！', 'success', T_TIME, 'topRight');
            } else {
                note('删除失败:' + response.message, 'error', F_TIME, 'topRight');
            }
        });
    }
};

function toAdd() {
    event_flag = 'create';
    $('#input_name').val('');
    $('#input_password').val('');
    $("#input_password").attr("title", "");
    $("#input_password").attr("placeholder", "请输入密码！");
    $('#input_roles').val('');
    $('#input_roles').trigger('change');
    $('#modal_add').modal('show');
}

/**
 * 提交类型添加
 */
function commit() {
    var name = $('#input_name').val();
    if (name === '') {
        return note('名称不能为空！', 'warning', 1500, 'topRight');
    }
    var password = $('#input_password').val();
    if (password === '' && event_flag === 'create') {
        return note('密码不能为空！', 'warning', 1500, 'topRight');
    }
    var param = {};
    param['id'] = $('#input_id').val();
    param['name'] = name;
    param['password'] = password;
    param['roles'] = JSON.stringify($('#input_roles').val());
    //开始提交
    $.post('/content/system/user/' + event_flag, param, function (response) {
        log(response);
        if (response.success) {
            $('#table_main').bootstrapTable('refresh');
            $('#modal_add').modal('hide');
            note('添加成功！', 'success', 1500, 'topRight');
        } else {
            note('添加失败:' + response.message, 'error', 5000, 'topRight');
        }
    });
}

function userRoles(roles) {
    var html = [];
    for(var i in roles)
        html.push(roles[i].name);
    return html.join(',');
}