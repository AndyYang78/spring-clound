/**
 * 标签
 * Created by yangtao on 2017/7/19.
 */
//relax, mint, sunset, metroui, semanticui, bootstrap-v3, bootstrap-v4
//top, topLeft, topCenter, topRight, center, centerLeft, centerRight, bottom, bottomLeft, bottomCenter, bottomRight
//alert, success, error, warning, info
$(function () {
    log(page);
    //日期范围选择
    $('#input_date_range').daterangepicker({
        autoApply: true, maxDate: '2019',
        locale: DATE_LOCAL_DAY
    });
});

/**
 * 数据获取
 */
function query() {
    $('.div_table_content').hide();
    $('#btn_export').fadeOut('slow');
    $('#table_main').bootstrapTable('refresh');
}

/**
 * 添加查询参数
 * @param params 参数
 * @returns {*} 参数
 */
function onParams(params) {
    var dateRange = $('#input_date_range').val().split(" - ");
    if (dateRange == null || dateRange.length != 2)
        return false;
    var device = $('#sel-device').val();
    params['start'] = dateRange[0];
    params['end'] = dateRange[1];
    return params;
}

/**
 * 服务端响应消息
 * @param result 消息
 */
function onResponse(result) {
    if (result.data == null || result.data.length == 0 || result.data[0] == null)
        result.data = [];
    $('.table_main').bootstrapTable('load', result);
    $('.div_table_content').slideDown();
    $('#btn_export').fadeIn();
    return result;
}

function colorFormat(value) {
    return '<div style="background-color: ' + value + '; color: white ;width: 100%;height: 100%; border:2px solid whitesmoke; border-radius:25px;">' + value + '</div>';
}

//操作栏
function operation(value, row) {
    if (row.state === -1)
        return '<a type="button" class="btn btn-default btn-xs back">回收</a>';
    var html = [];
    html.push('<a type="button" class="btn btn-primary btn-xs edit">编辑</a> ');
    if (row.state === 1)
        html.push('<a type="button" class="btn btn-warning btn-xs shutdown">关闭</a> ');
    else if (row.state === 0)
        html.push('<a type="button" class="btn btn-success btn-xs open">开启</a> ');
    html.push('<a type="button" class="btn btn-danger btn-xs remove">删除</a>');
    return html.join('');
}

//状态格式化显示
function typeState(value) {
    log(value);
    switch (value) {
        case 1:
            return "<span style='color: green'>启用</span>";
        case 0:
            return "<span style='color: red'>停用</span>";
        case -1:
            return "<span style='color: grey'>已删除</span>";
    }
    return '-';
}

//操作事件
window.operateEvents = {
    //编辑
    'click .edit': function (e, value, row) {
        $('#input_edit_id').val(value);
        $('#input_edit_name').val(row.name);
        $('#modal_edit').modal('show');
    },
    //删除
    'click .remove': function (e, value, row) {
        if (row.state === 1)
            return note('只能删除已停用的类型！', 'alert', 1500, 'topRight');
        $.post('/content/chatroom/report/type/remove', {id: value}, function (response) {
            if (response.success) {
                $('#table_main').bootstrapTable('refresh');
                note('删除成功！', 'success', 1500, 'topRight');
            } else {
                note('删除失败:' + response.message, 'error', 5000, 'topRight');
            }
        })
    },
    //打开
    'click .open': function (e, value) {
        //开始提交
        $.post('/content/chatroom/report/type/open', {id: value}, function (response) {
            if (response.success) {
                $('#table_main').bootstrapTable('refresh');
                note('开启成功！', 'info', 1500, 'topRight');
            } else {
                note('开启失败:' + response.message, 'error', 5000, 'topRight');
            }
        })
    },
    //关闭
    'click .shutdown': function (e, value) {
        $.post('/content/chatroom/report/type/close', {id: value}, function (response) {
            if (response.success) {
                $('#table_main').bootstrapTable('refresh');
                note('停用成功！', 'warning', 1500, 'topRight');
            } else {
                note('停用失败:' + response.message, 'error', 5000, 'topRight');
            }
        })
    },
    //回收
    'click .back': function () {
        note('需要回收请提需求...Sorry', 'info', 5000, 'topRight');
    }
};

/**
 * 提交类型添加
 */
function commitType() {
    var name = $('#input_name').val();
    if (name === '') {
        return note('名称不能为空！', 'warning', 1500, 'topRight');
    }
    var param = {};
    param['name'] = name;
    //开始提交
    $.post('/content/chatroom/report/type/create', param, function (response) {
        log(response);
        if (response.success) {
            $('#table_main').bootstrapTable('refresh');
            $('#modal_add').modal('hide');
            note('添加成功！', 'success', 1500, 'topRight');
        } else {
            note('添加失败:' + response.message, 'error', 5000, 'topRight');
        }
    })
}

function commitUpdate() {
    var name = $('#input_edit_name').val();
    if (name === '') {
        return note('名称不能为空！', 'warning', 1500, 'topRight');
    }
    var param = {};
    param['id'] = $('#input_edit_id').val();
    param['name'] = name;
    //开始提交
    $.post('/content/chatroom/report/type/update', param, function (response) {
        log(response);
        if (response.success) {
            $('#table_main').bootstrapTable('refresh');
            $('#modal_edit').modal('hide');
            note('添加成功！', 'success', 1500, 'topRight');
        } else {
            note('添加失败:' + response.message, 'error', 5000, 'topRight');
        }
    })
}