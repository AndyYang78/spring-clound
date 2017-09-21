/**
 * 标签
 * Created by yangtao on 2017/7/19.
 */
//relax, mint, sunset, metroui, semanticui, bootstrap-v3, bootstrap-v4
//top, topLeft, topCenter, topRight, center, centerLeft, centerRight, bottom, bottomLeft, bottomCenter, bottomRight
//alert, success, error, warning, info
$(function () {
    log(page);
    init();
});


function init() {
    $.get('/content/system/privilege/list', function (response) {
        $('#div_tree_add').treeview({
            data: privilegeTreeNode([], response.data, 1),
            checkedIcon: 'glyphicon glyphicon-check',
            color: "#428bca",
            showTags: true,
            showCheckbox: true,
            levels: 1,
            emptyIcon: '',
            expandIcon: 'glyphicon glyphicon-chevron-right',
            collapseIcon: 'glyphicon glyphicon-chevron-down',
            //节点check
            onNodeChecked: function (event, data) {
                checkNode(data);
            },
            //节点unCheck
            onNodeUnchecked: function (event, data) {
                unCheckNode(data);
            }
        });
    });
}

/**
 * 选中节点
 * @param node 节点
 * @param stop 是否停止递归
 */
function checkNode(node, stop) {
    //防止循环递归
    $('#div_tree_add').treeview('checkNode', [node.nodeId, {silent: true}]);
    if (null != node.parentId)
        checkNode($('#div_tree_add').treeview('getNode', node.parentId), true);
    if (stop) return;
    if (notEmpty(node.nodes)) {
        for (var i in node.nodes)
            checkNode(node.nodes[i]);
    }
}

/**
 * 取消选中节点
 * @param node 节点
 */
function unCheckNode(node) {
    $('#div_tree_add').treeview('uncheckNode', [node.nodeId, {silent: true}]);
    if (notEmpty(node.nodes)) {
        for (var i in node.nodes)
            unCheckNode(node.nodes[i]);
    }
}

/**
 * 填充树节点
 * @param tree 树
 * @param privileges 权限
 * @param deep
 */
function privilegeTreeNode(tree, privileges, deep) {
    for (var i = 0; i < privileges.length; i++) {
        var node = {
            id: privileges[i].id,
            text: privilegeCode(privileges[i], deep),
            selectable: false,
            state: {
                checked: false,
                disabled: false,
                expanded: false,
                selected: false
            }
        };
        if (privileges[i]['children'] !== null && privileges[i]['children'].length > 0) {
            node['tags'] = [privileges[i]['children'].length];
            node['nodes'] = [];
            privilegeTreeNode(node['nodes'], privileges[i]['children'], deep + 1);
        }
        tree.push(node);
    }
    return tree;
}

/**
 * 获取权限代码
 * @param privilege 权限
 * @param deep 深度
 * @returns {string} CODE
 */
function privilegeCode(privilege, deep) {
    var base = '<span style="display: inline-block; width: 150px;">' + privilege.name + '</span>';
    for (var i = 0; i < deep; i++)
        base += ' —';
    var code = [base + '  ROLE'];
    if (notEmpty(privilege.model)) {
        code.push(privilege.model);
    }
    if (notEmpty(privilege.page)) {
        code.push(privilege.page);
    }
    if (notEmpty(privilege.point)) {
        code.push(privilege.point);
    }
    return code.join('_').toUpperCase();
}

/**
 * 格式化
 * @param privileges
 * @returns {string}
 */
function rolePrivileges(privileges) {
    var html = [];
    for (var i in privileges)
        html.push(privileges[i].name);
    return html.join(',');
}

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

var flag = 'create';

//操作事件
window.operateEvents = {
    //编辑
    'click .edit': function (e, value, row) {
        flag = 'update';
        $('#div_tree_add').treeview('uncheckAll', {silent: true});
        $('#input_id').val(value);
        $('#input_name').val(row.name);
        //选中已有的权限
        var nodes = $('#div_tree_add').treeview('getUnchecked');
        for (var i in nodes)
            for (var j in row.privileges)
                if (nodes[i].id == row.privileges[j].id) {
                    $('#div_tree_add').treeview('checkNode', [nodes[i].nodeId, {silent: true}]);
                    break;
                }
        $('#div_tree_add').treeview('collapseAll', {silent: true});
        $('#modal_add').modal('show');
    },
    //删除
    'click .remove': function (e, value) {
        $.post('/content/system/role/remove', {id: value}, function (response) {
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
    flag = 'create';
    $('#div_tree_add').treeview('uncheckAll', {silent: true});
    $('#input_name').val('');
    $('#div_tree_add').treeview('collapseAll', {silent: true});
    $('#modal_add').modal('show');
}

/**
 * 提交添加
 */
function commit() {
    var name = $('#input_name').val();
    if (name === '')
        return note('名称不能为空！', 'warning', 1500, 'topRight');
    var privileges = $('#div_tree_add').treeview('getChecked');
    var ids = [];
    for (var i in privileges)
        ids.push(privileges[i].id);
    var param = {};
    param['id'] = $('#input_id').val();
    param['name'] = name;
    param['ids'] = JSON.stringify(ids);
    //开始提交
    $.post('/content/system/role/' + flag, param, function (response) {
        if (response.success) {
            $('#table_main').bootstrapTable('refresh');
            $('#modal_add').modal('hide');
            note('添加成功！', 'success', 1500, 'topRight');
        } else {
            note('添加失败:' + response.message, 'error', 5000, 'topRight');
        }
    });
}