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
        autoApply: true, maxDate: moment().format('YYYY-MM-DD'),
        startDate: moment().subtract(7, 'days').format('YYYY-MM-DD'),
        locale: DATE_LOCAL_DAY
    });
    $.post('/content/chatroom/report/type/list', function (response) {
        if (response.success) {
            for (var i in response.data)
                $('#select_report_type').append('<option value="' + response.data[i].id + '">' + response.data[i].name + '</option>');
        } else {
            note('举报类型获取失败:' + response.message, 'error', 5000, 'topRight');
        }
        query();
    });
});

function query() {
    $('#table_main').bootstrapTable('refresh', {url: '/content/user/report/list'});
}

function onParams(params) {
    var dateRange = $('#input_date_range').val().split(" - ");
    if (dateRange !== null && dateRange.length === 2) {
        params['start'] = dateRange[0];
        params['end'] = dateRange[1];
    }
    params['userId'] = $('#input_report_user_id').val();
    params['reportType'] = $('#select_report_type').val();
    params['state'] = $('#select_state').val();
    return params;
}

//操作栏
function operation(value, row) {
    if (row.state === -1)
        return "-";
    var html = [];
    if (row.state === 999)
        html.push('<a type="button" class="btn btn-success btn-xs complete" data-toggle="tooltip" data-placement="top" title="即确认该条记录已完成处理">完成处理</a> ');
    html.push('<a type="button" class="btn btn-danger btn-xs remove">删除</a> ');
    html.push('<a type="button" class="btn btn-primary btn-xs detail" data-toggle="tooltip" data-placement="top" title="查看举报详细信息">详情</a>');
    return html.join('');
}

//状态格式化显示
function typeState(value) {
    switch (value) {
        case 999:
            return "<span style='color: lightskyblue'>未处理</span>";
        case 1:
            return "<span style='color: green'>已处理</span>";
        case -1:
            return "<span style='color: red'>已删除</span>";
    }
    return '-';
}

function length10(value) {
    if (value.length > 10)
        value = value.substring(0, 9) + "...";
    return value;
}

function detailInfo(row) {
    var html = [];
    html.push('<div class="alert alert-warning alert-dismissible">');
    html.push('<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>');
    html.push('<h4><i class="icon fa fa-ban"></i>&nbsp;举报详情</h4>');
    html.push('<p><b>举报人</b><br/>' + row.reportUserId + '</p>');
    html.push('<p><b>举报类型</b><br/>' + row.typeName + '</p>');
    html.push('<p style="width: 100%; word-wrap:break-word; word-break:normal;"><b>被举报房间ID</b><br/>' + row.chatRoomId + '</p>');
    html.push('<p style="width: 100%; word-wrap:break-word; word-break:normal;"><b>被举报房间名称</b><br/>' + row.chatRoomName + '</p>');
    html.push('<p style="width: 100%; word-wrap:break-word; word-break:normal;"><b>举报时间</b><br/>' + row.createTime + '</p>');
    html.push('<p style="width: 100%; word-wrap:break-word; word-break:normal;"><b>举报内容</b><br/>' + row.info + '</p>');
    html.push('</div>');
    return html.join('');
}

//操作事件
window.operateEvents = {
    // 完成处理
    'click .complete': function (e, value, row) {
        $.post('/content/user/report/complete', {id: value}, function (response) {
            if (response.success) {
                query();
                note('处理成功！', 'success', 1500, 'topRight');
            } else {
                note('处理失败:' + response.message, 'error', 5000, 'topRight');
            }
        });
    },
    // 删除
    'click .remove': function (e, value, row) {
        $.post('/content/user/report/remove', {id: value}, function (response) {
            if (response.success) {
                query();
                note('删除成功！', 'success', 1500, 'topRight');
            } else {
                note('删除失败:' + response.message, 'error', 5000, 'topRight');
            }
        });
    },
    // 详情
    'click .detail': function (e, value, row) {
        $('#div_detail').html(detailInfo(row));
        $('#modal_detail').modal('show');
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
    var color = $('#input_color input').val();
    if (color === '') {
        return note('颜色值不能为空！', 'warning', 1500, 'topRight');
    }
    var param = {};
    param['name'] = name;
    param['color'] = color;
    //开始提交
    $.post('/content/user/type/create', param, function (response) {
        if (response.success) {
            query();
            $('#modal_add').modal('hide');
            note('添加成功！', 'success', 1500, 'topRight');
        } else {
            note('添加失败:' + response.message, 'error', 5000, 'topRight');
        }
    })
}