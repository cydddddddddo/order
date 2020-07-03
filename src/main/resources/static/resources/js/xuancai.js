// 为按钮添加点击事件
var id;
function addClickEvent() {

    $('.sure-menu').each(function(index, item) {
        var parent = $(item).parents('.menus-item');
        const obj = {};
        $(item).on('click', function() {
            obj.id = parent.attr('myid');
        })
        $.ajax({
            url:  '/updateDescription',
            type: 'get',
            async: false,
            dataType: 'json',
            data: obj,
            contentType: 'application/json; charset=utf-8',
            success: function(data) {
                console.log('success')
            }
        });
    })
}


// 导入数据库中的菜品
function initMenu() {

    $.ajax({
        url: '/selectMealTrue',
        type: 'get',
        async: false,
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        success: function(data) {
            var arr = data.data;
            $('.menus-wrap').html('');
            let template = '<div class="menus-item" myid="$id">' +
                '<img src="./images/banner1.jpg" class="menupic">' +
                '<div class="menuname">$menuname</div>' +
                '<p class="menudetail">$menudetail</p>' +
                '<button type="button" class="layui-btn layui-btn-sm layui-btn-radius sure-menu">确定菜品</button>' +
                '</div>';

            arr.forEach(function(item, index) {
                let str = template.replace('$menupic', htp + item.picture)
                    .replace('$id', item.id)
                    .replace('$menuname', item.name)
                    .replace('$menudetail', item.description);
                $('.menus-wrap').append(str);
            })
            addClickEvent();
        }
    });
}
$(function() {
     initMenu();
})