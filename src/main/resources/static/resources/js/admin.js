function checkStatus(item) {
    if (item.status === '1') {
        return '<input type="checkbox" checked name="status" lay-skin="switch" lay-filter="switchTest" lay-text="ON|OFF" class="status-btn">';
    } else {
        return '<input type="checkbox" name="status" lay-skin="switch" lay-filter="switchTest" lay-text="ON|OFF" class="status-btn">';
    }
}
// 导入数据库中的菜品
function initMenu() {
    $.ajax({
        url: '/meal/findAll',
        type: 'get',
        async: false,
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        success: function(data) {
            let arr = data.data;
            $('.menus-wrap').html('');
            let template = '<div class="menus-item" myid="$id">' +
                '<form class="layui-form form-status">' +
                '<div class="layui-form-item">' +
                '<div class="layui-input-block">' +
                '$checkstatus' +
                '</div>' +
                '</div>' +
                '</form>' +
                '<img src="$img" class="menupic">' +
                '<div class="menuname">$name</div>' +
                '<p class="menudetail">$description</p>' +
                '<form class="layui-form checkdel">' +
                '<div class="layui-form-item">' +
                '<div class="layui-input-block">' +
                '<input type="checkbox" lay-filter="checkTest" title="Del" class="check">' +
                '</div>' +
                '</div>' +
                '</form>' +
                '<button type="button" class="layui-btn layui-btn-sm layui-btn-radius edmitmenu">编辑菜品</button>' +
                '</div>';

            arr.forEach(function(item, index) {
                let str = template.replace('$img', "/"+item.picture)
                    .replace('$name', item.name)
                    .replace('$description', item.description)
                    .replace('$id', item.id)
                    .replace('$checkstatus', checkStatus(item));
                $('.menus-wrap').append(str);
            })
        }
    });
    addClickEvent();
    layui.use('form', function() {
        var form = layui.form;
        layui.form.render();
        //监听提交
        form.on('submit(formDemo)', function(data) {
            layer.msg(JSON.stringify(data.field));
            return false;
        });
    });
}
function addClickEvent() {
    $(document).on('click','#Comedit', function() {
        $.post('/meal/update',{
            id:parseInt($('.form-edmitmenu').attr('myid')),
            name:document.getElementById('mealName').value,
            description:document.getElementById('mealDsc').value,
        },function (res) {
            if(res!=null){
                if(res.code=='0'){
                    layer.msg('更新成功');
                }
                else{
                    layer.msg('更新失败');
                }
            }
        })
    })
    $('.deletemenu').on('click', function() {
        var menuscheck = [];
        $('.menus-item .check').each(function(index, item) {
            $(item).on('click', function() {
                menuscheck[index] = !menuscheck[index];
            })
            if ($(item).attr('isdel') === 'true') {
                menuscheck.push(parseInt($(item).parents('.menus-item').attr('myid')));
            }
        })
        $.ajax({
            url:  '/meal/delete',
            type: 'get',
            async: false,
            traditional:true,
            data: {ids:menuscheck},
            dataType: 'json',
            success: function() {
                initMenu();
            }
        });
    })
    $('.edmitmenu').each(function(index, item) {
        $(item).on('click', function() {
            $('.form-edmitmenu').css('display', 'block');
            $('.form-edmitmenu').attr('myid', $(item).parents('.menus-item').attr('myid'));
            $('.theid').val($(item).parents('.menus-item').attr('myid'));
            // alert(parseInt($('.form-edmitmenu').attr('myid')))
            console.log(parseInt($('.form-edmitmenu').attr('myid'))+11);
        });
    });
    $('.close-btn span').on('click', function() {
        $('.form-edmitmenu').css('display', 'none');
        initMenu();
        $('.edmitmenu').each(function(index, item) {
            $(item).on('click', function() {
                $('.form-edmitmenu').css('display', 'block');
                $('.form-edmitmenu').attr('myid', $(item).parents('.menus-item').attr('myid'));
                $('.theid').val($(item).parents('.menus-item').attr('myid'));
                console.log($('.form-edmitmenu').val());
            });
        });
    })
}
$(function() {
    // 为按钮添加点击事件
    initMenu();
})