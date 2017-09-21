/**
 * 标签
 * Created by yangtao on 2017/7/19.
 */
//relax, mint, sunset, metroui, semanticui, bootstrap-v3, bootstrap-v4
//top, topLeft, topCenter, topRight, center, centerLeft, centerRight, bottom, bottomLeft, bottomCenter, bottomRight
//alert, success, error, warning, info
$(function () {
});

var defaultd;

function query() {
    $('#table_main').bootstrapTable('refresh');
}

//String name, String downloadUrl, String description, String descriptionUrl, Boolean force
function commit() {
    var param = {};
    if ((param['name'] = $('#input_name').val()) == '')
        return note('版本名称不能为空！', 'error', 1500, 'topRight');
    param['description'] = $('#input_description').val();
    param['versionNumber'] = $('#input_version_number').val();
    param['os'] = $('#input_os').val();
    param['versionState'] = $('#input_version_state').val();
    param['force'] = $('input[name="input_force"]:checked').val();
    //开始提交
    $.post('/content/app/version/create', param, function (response) {
        if (response.success) {
            $('#table_main').bootstrapTable('refresh');
            $('#modal').modal('hide');
            note('操作成功！', 'success', 1500, 'topRight');
            $('#modal input').val('');
        } else {
            note('操作失败:' + response.message, 'error', 5000, 'topRight');
        }
    })
}
/**
 * 修改版本
 *
 */
function updateModel() {
    var batchSelect = $('#table_main').bootstrapTable('getSelections');
    if (batchSelect.length == 0) {
        note('请选择相应版本列表!', 'warning', 5000, 'topRight');
        return
    }
    var data=batchSelect[0];
    $('#update_name').val(data.name);
    $('#update_version_number').val(data.versionNumber);
    $('#update_os').val('IOS');
    $('#update_version_state').val(data.versionState);
    $('#update_description').val(data.description);
    defaultd = data.id;
    data.forceUpdate==false?$('#true_checked').iCheck('check'):$('#false_checked').iCheck('check');
    $('#modal_update').modal('show');
}
/**
 * 更新版本号
 * @param value
 * @returns {string}
 */
function update(){
    var param = {};
    if ((param['name'] = $('#update_name').val()) == '')
        return note('版本名称不能为空！', 'error', 1500, 'topRight');
    param['description'] = $('#update_description').val();
    param['versionNumber'] = $('#update_version_number').val();
    param['os'] = $('#update_os').val();
    param['id'] = defaultd;
    param['versionState'] = $('#update_version_state').val();
    param['forceUpdate'] = $('input[name="input_forces"]:checked').val();
    //开始提交
    $.post('/content/app/version/update/app', param, function (response) {
        if (response.success) {
            $('#table_main').bootstrapTable('refresh');
            $('#modal_update').modal('hide');
            note('操作成功！', 'success', 1500, 'topRight');
            $('#modal input').val('');
        } else {
            note('操作失败:' + response.message, 'error', 5000, 'topRight');
        }
    })

}

function formatBool(value) {
    return value ? '<div style="background-color: green; color: white ;width: 100%;height: 100%; border:2px solid whitesmoke; border-radius:25px; text-align: center">是</div>' : '<div style="background-color: #848187; color: white ;width: 100%;height: 100%; border:2px solid whitesmoke; border-radius:25px; text-align: center">否</div>';
}

/**
 * 版本状态 格式化
 * @param value
 * @returns {*}
 */
function appVersionStatus(value) {
    switch (value){
        case "TEST":
            return "<span style='color: blue'>测试</span>";
            break;
        case "INREVIEW":
            return "<span style='color: green'>提审</span>";
            break;
        case "PRODUCT":
            return "<span style='color: lightcoral'>生产</span>";
            break;
        case "OVERDUE":
            return "<span style='color: red'>过期</span>";
            break;
    }
}