/**
 * 标签
 * Created by yangtao on 2017/7/19.
 */
//relax, mint, sunset, metroui, semanticui, bootstrap-v3, bootstrap-v4
//top, topLeft, topCenter, topRight, center, centerLeft, centerRight, bottom, bottomLeft, bottomCenter, bottomRight
//alert, success, error, warning, info
$(function () {
    // 以下是右键菜单
    // $('#table_main').bootstrapTable({
    //     contextMenu: '#context-menu',
    //     contextMenuButton: '.example4-button',
    //     contextMenuAutoClickRow: true,
    //     onClickRow: function (row, e) {
    //         $('#table_main').find('.info').removeClass('info');
    //         // $('#table_main').bootstrapTable('uncheckAll');
    //         // $('#table_main').bootstrapTable('check', $(e).find('input').eq(0).data('index'));
    //         e.addClass('info');
    //     },
    //     onContextMenuItem: function (row, e) {
    //         if (e.data("item") == "close") {
    //             alert("close: " + row.itemid + ' ' + row.name + ' ' + row.price);
    //             $.post('/content/chatroom/close', {chatRoomId: row.id}, function (response) {
    //                 if (response.success) {
    //                     $('#table_main').bootstrapTable('refresh');
    //                     note('删除成功！', 'success', 1500, 'topRight');
    //                 } else {
    //                     note('删除失败:' + response.message, 'error', 5000, 'topRight');
    //                 }
    //             })
    //         } else if (e.data("item") == "warn") {
    //             alert("warn: " + row.itemid + ' ' + row.name + ' ' + row.price);
    //         }
    //     },
    //     onUncheck: function () {
    //         $('#toolbar .toggle').fadeOut();
    //     },
    //     onCheck: function () {
    //         $('#toolbar .toggle').fadeIn();
    //     }
    // });

    $.post('/content/chatroom/type/list', function (response) {
        if (response.success) {
            for (var i in response.data)
                $('#input_filter_room_type').append('<option value="' + response.data[i].id + '">' + response.data[i].name + '</option>');
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

function colorFormat(type) {
    return '<div style="background-color: ' + type.color + '; color: white ;width: 100%;height: 100%; border:2px solid whitesmoke; border-radius:25px;">' + type.name + '</div>';
}

function img(url) {
    return '<img src="' + url + '" style="height: 50px; width: auto; margin: -8px;">';
}

//操作栏
function operation(value, row) {
    var html = [];
    html.push('<a type="button" class="btn btn-danger btn-xs shutdown">关闭</a> ');
    html.push('<a type="button" class="btn btn-primary btn-xs warn">警告</a>');
    return html.join('');
}

//操作事件
window.operateEvents = {
    'click .warn': function (e, value, row) {
        $('#input_creator').val(row.creator);
        $('#input_name').val(row.name);
        $('#input_content').val('');
        $('#modal').modal('show');
    },
    'click .shutdown': function (e, value, row) {
        $.post('/content/chatroom/close', {chatRoomId: value}, function (response) {
            if (response.success) {
                $('#table_main').bootstrapTable('refresh');
                note('操作成功！', 'success', 1500, 'topRight');
            } else {
                note('操作失败:' + response.message, 'error', 5000, 'topRight');
            }
        })
    }
};

function commit() {
    var params = {};
    params['creator'] = $('#input_creator').val();
    params['content'] = $('#input_content').val();
    $.post('/content/chatroom/warn', params, function (response) {
        if (response.success) {
            note('操作成功！', 'success', 1500, 'topRight');
            $('#modal').modal('hide');
        } else {
            note('操作失败:' + response.message, 'error', 5000, 'topRight');
        }
    })
}