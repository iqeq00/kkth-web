layui.use(['config', 'lichee', 'jquery', 'layer', 'table', 'form', 'imageUpload'], function () {

    var config = layui.config;
    var lichee = layui.lichee;
    var $ = layui.jquery;
    var layer = layui.layer;
    var table = layui.table;
    var form = layui.form;
    var imageUpload = layui.imageUpload;

    var tableInfo = table.render({
        elem: '#table',
        toolbar: '#toolbar',
        defaultToolbar: [],
        url: '/biodiversity/page',
        title: '生物多样性',
        page: true,
        headers: {Authorization: config.getToken()},
        request: config.request,
        parseData: config.parseData,
        response: config.response,
        cols: [[
            {field: 'parkId', align: 'center', sort: true, title: '地质公园ID'},
            {field: 'protectionNumber', align: 'center', sort: true, title: '保护区编号'},
            {field: 'biologicalNumber', align: 'center', sort: true, title: '生物编号'},
            {field: 'chineseName', align: 'center', sort: true, title: '中文名称'},
            {field: 'latinName', align: 'center', sort: true, title: '拉丁名称'},
            {field: 'mainCategory', align: 'center', sort: true, title: '大类名称'},
            {field: 'biologicalCategory', align: 'center', sort: true, title: '生物分类'},
            {field: 'quantity', align: 'center', sort: true, title: '数量'},
            {field: 'protectionLevel', align: 'center', sort: true, title: '保护级别'},
            {fixed: 'right',align : 'center', title: '操作', toolbar: '#bar', width: 180}
        ]]
    });

    table.on('toolbar(tableFilter)', function (obj) {
        var checkStatus = table.checkStatus(obj.config.id);
        switch (obj.event) {
            case 'add':
                showEditModel();
                break;
            case 'getCheckLength':
                var data = checkStatus.data;
                layer.msg('选中了：' + data.length + ' 个');
                break;
            case 'isAll':
                layer.msg(checkStatus.isAll ? '全选' : '未全选');
                break;
        };
    });

    table.on('tool(tableFilter)', function (obj) {
        var data = obj.data;
        if (obj.event === 'del') {
            layer.confirm('确定要删除吗？', function (index) {
                layer.load(2);
                lichee.delete('/biodiversity/' + obj.data.id, {}, function () {
                    layer.closeAll('loading');
                    layer.msg('删除成功', {icon: 1});
                    obj.del();
                });
            });
        } else if (obj.event === 'edit') {
            showEditModel(data);
        }
    });

    var showEditModel = function (data) {
        layer.open({
            title: data ? '详情/修改' : '添加',
            type: 1,
            area: ['800px', '560px'],
            content: $('#form-model').html(),
            success: function () {
                $('#form')[0].reset();
                lichee.get('/park/list', {async: false}, function (data) {
                    $('#parkId').vm({parks: data.result});
                    form.render('select');
                });
                imageUpload.init("uploadBtn", "img", "biodiversity");
                if (data) {
                    form.val('formFilter', data);
                    imageUpload.initImageList(data.img);
                }
                $('#form .close').click(function () {
                    layer.closeAll('page');
                });
            }
        });
    };

    var form = layui.form;
    form.on('submit(formSubmit)', function (data) {
        layer.load(2);
        if (data.field.id) {
            lichee.put('/biodiversity/' + data.field.id, {data: data.field}, function (res) {
                callFunction(res);
            });
        } else {
            lichee.post('/biodiversity', {data: data.field}, function (res) {
                callFunction(res);
            });
        }
        return false;
    });

    var callFunction = function (res) {
        layer.closeAll();
        if (res.status == 200) {
            layer.msg("修改成功",{icon: 1});
            tableInfo.reload({where: lichee.getSearchForm()});
        } else if(res.status == 201)  {
            layer.msg("添加成功",{icon: 1});
            tableInfo.reload({where: lichee.getSearchForm()});
        }
    };

    $('#searchButton').click(function () {
        tableInfo.reload({where: lichee.getSearchForm()});
    });

});