/**
 * 标签
 * Created by yangtao on 2017/7/19.
 */
//relax, mint, sunset, metroui, semanticui, bootstrap-v3, bootstrap-v4
//top, topLeft, topCenter, topRight, center, centerLeft, centerRight, bottom, bottomLeft, bottomCenter, bottomRight
//alert, success, error, warning, info
$(function () {
    loadTree();
});

function loadTree() {
    $.get('/content/system/privilege/list', function (response) {
        $('#tree').treeview({
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

        $('#div_tree_add').treeview({
            data: privilegeTreeNode([], response.data, 1, true),
            checkedIcon: 'glyphicon glyphicon-check',
            color: "#428bca",
            showTags: true,
            showCheckbox: true,
            levels: 1,
            emptyIcon: '',
            expandIcon: 'glyphicon glyphicon-chevron-right',
            collapseIcon: 'glyphicon glyphicon-chevron-down',
            //节点check
            onNodeSelected: function (event, data) {
                $('#input_parent_id').val(data.id);
                $('#input_parent_name').val(data.name);
                log($('#div_tree_add').treeview("getParent", data).nodeId);
            },
            //节点取消选中
            onNodeUnselected: function (event, data) {
                $('#input_parent_id').val('');
                $('#input_parent_name').val('');
            }
        });
    });
}

/**
 * 选中节点
 * @param node 节点
 */
function checkNode(node) {
    $('#tree').treeview('checkNode', node.nodeId);
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
    $('#tree').treeview('uncheckNode', node.nodeId);
    if (notEmpty(node.nodes)) {
        for (var i in node.nodes)
            unCheckNode(node.nodes[i]);
    }
}

/**
 * 填充树节点
 * @param tree 树
 * @param privileges 权限
 * @param deep 深度
 */
function privilegeTreeNode(tree, privileges, deep, isSelect) {
    for (var i = 0; i < privileges.length; i++) {
        if (privileges[i].model === "admin" && isSelect) continue;
        var node = {
            id: privileges[i].id,
            name: privileges[i].name,
            text: privilegeCode(privileges[i], deep),
            selectable: !!isSelect && privileges[i].model !== "admin",
            state: {
                checked: true,
                disabled: false,
                expanded: false,
                selected: false
            }
        };
        if (privileges[i]['children'] !== null && privileges[i]['children'].length > 0) {
            node['tags'] = [privileges[i]['children'].length];
            node['nodes'] = [];
            privilegeTreeNode(node['nodes'], privileges[i]['children'], deep + 1, isSelect);
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
        base += '——';
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
 * 收起全部
 */
function collapseAll() {
    $('#tree').treeview('collapseAll', {silent: true});
}

/**
 * 展开全部
 */
function expandAll() {
    $('#tree').treeview('expandAll', {levels: 2, silent: true});
}

/**
 * 保存
 */
function save() {
}

function toAdd() {
    $('#modal_add').modal('show');
}

/**
 * 提交新增权限
 */
function commit() {
    var params = {};
    params['name'] = $('#input_name').val();
    if (isEmpty(params['name']))
        return note('权限名称不能为空！', 'warning', T_TIME, 'topRight');
    params['model'] = $('#input_model').val();
    if (params['parentId'] == '' && isEmpty(params['model']))
        return note('未选择父权限时，所属模块不能为空！', 'warning', T_TIME, 'topRight');
    params['page'] = $('#input_page').val();
    params['point'] = $('#input_point').val();
    params['parentId'] = $('#input_parent_id').val();
    $.post('/content/system/privilege/create', params, function (response) {
        if (response.success) {
            loadTree();
            $('#modal_add').modal('hide');
            note('添加成功！', 'success', T_TIME, 'topRight');
        } else {
            note('添加失败:' + response.message, 'error', F_TIME, 'topRight');
        }

    });
}