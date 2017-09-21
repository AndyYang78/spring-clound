/**
 * 标签
 * Created by yangtao on 2017/7/19.
 */
//relax, mint, sunset, metroui, semanticui, bootstrap-v3, bootstrap-v4
//top, topLeft, topCenter, topRight, center, centerLeft, centerRight, bottom, bottomLeft, bottomCenter, bottomRight
//alert, success, error, warning, info
$(function () {
    $.post('/content/chatroom/type/list', function (response) {
        if (response.success) {
            for (var i in response.data) {
                $('#input_filter_room_type').append('<option value="' + response.data[i].id + '">' + response.data[i].name + '</option>');
                $('#input_room_type').append('<option value="' + response.data[i].id + '">' + response.data[i].name + '</option>');
            }
        } else {
            note('房间模板列表获取失败:' + response.message, 'error', 5000, 'topRight');
        }
    });
});

function onParams(param) {
    param = {};
    param['creator'] = $('#input_filter_creator').val();
    param['creatorName'] = $('#input_filter_creator_name').val();
    param['roomName'] = $('#input_filter_room_name').val();
    param['typeId'] = $('#input_filter_room_type').val();
    return param;
}

function query() {
    $('#table_main').bootstrapTable('refresh');
}

function onResponse(response) {
    log(response);
    if (!response.success) {
        note('获取失败:' + response.message, 'error', 5000, 'topRight');
        return false;
    }
    return response;
}

//房间模板背景
function colorFormat(type) {
    return '<div style="background-color: ' + type.color + '; color: white ;width: 100%;height: 100%; border:2px solid whitesmoke; border-radius:25px;">' + type.name + '</div>';
}

//房间是否打开
function roomValid(valid) {
    return '<div style="background-color: ' + (!!valid ? 'green' : 'red') + '; color: white ;width: 100%;height: 100%; border:2px solid whitesmoke; border-radius:25px;">' + (!!valid ? '开启' : '关闭') + '</div>';
}

//房间是否置顶
function roomTop(top) {
    return '<div style="background-color: ' + (!!top ? '#ff4600' : '#494949') + '; color: white ;width: 100%;height: 100%; border:2px solid whitesmoke; border-radius:25px;">' + (!!top ? '是' : '否') + '</div>';
}

//图片
function img(url) {
    return '<img src="' + url + '" style="height: 50px; width: auto; margin: -8px;">';
}

//操作栏
function operation(value, row) {
    var html = [];
    html.push(!row.top ? '<a type="button" class="btn btn-primary btn-xs op_top">置顶房间</a> ' : '<a type="button" class="btn btn-warning btn-xs op_top_cancel">取消置顶</a> ');
    html.push(!row.valid ? '<a type="button" class="btn btn-success btn-xs op_open">打开</a> ' : '<a type="button" class="btn btn-danger btn-xs op_close">关闭</a> ');
    html.push('<a type="button" class="btn btn-info btn-xs op_edit">修改</a> ');
    return html.join('');
}

//操作事件
window.operateEvents = {
    'click .op_edit': function (e, value, row) {
        $('#input_id').val(row.id);
        $('#input_name').val(row.name);
        $('#input_room_type').val(row.type.id);
        $('#modal').modal('show');
    },
    //置顶
    'click .op_top': function (e, value) {
        $.post('/content/chatroom/top/add', {chatRoomId: value}, function (response) {
            if (response.success) {
                query();
                note('操作成功！', 'success', 1500, 'topRight');
            } else {
                note('操作失败:' + response.message, 'error', 5000, 'topRight');
            }
        })
    },
    //取消置顶
    'click .op_top_cancel': function (e, value) {
        $.post('/content/chatroom/top/remove', {chatRoomId: value}, function (response) {
            if (response.success) {
                query();
                note('操作成功！', 'success', 1500, 'topRight');
            } else {
                note('操作失败:' + response.message, 'error', 5000, 'topRight');
            }
        })
    },
    'click .op_open': function (e, value) {
        $.post('/content/chatroom/open', {chatRoomId: value}, function (response) {
            if (response.success) {
                query();
                note('操作成功！', 'success', 1500, 'topRight');
            } else {
                note('操作失败:' + response.message, 'error', 5000, 'topRight');
            }
        })
    },
    'click .op_close': function (e, value) {
        $.post('/content/chatroom/close', {chatRoomId: value}, function (response) {
            if (response.success) {
                query();
                note('操作成功！', 'success', 1500, 'topRight');
            } else {
                note('操作失败:' + response.message, 'error', 5000, 'topRight');
            }
        })
    }
};

function commit() {
    var params = {};
    params['chatRoomId'] = $('#input_id').val();
    params['name'] = $('#input_name').val();
    params['type'] = $('#input_room_type').val();
    $.post('/content/chatroom/update', params, function (response) {
        if (response.success) {
            note('操作成功！', 'success', 1500, 'topRight');
            query();
            $('#modal').modal('hide');
        } else {
            note('操作失败:' + response.message, 'error', 5000, 'topRight');
        }
    })
}