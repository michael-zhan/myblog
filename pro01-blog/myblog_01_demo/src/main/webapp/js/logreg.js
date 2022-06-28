layui.use(['layer', 'form'], function () {
  var form = layui.form;
  var layer = layui.layer; //弹层
  var $ = layui.$;
  $("#login-btn").on("click",function(){
    layer.open({
	title:"欢迎回来",
	btn:false,type:2,
	offset:"100px",
	resize:false,
	skin:"layui-layer-admin",
	area:["360px","336px"],
	content:"/member/login.php"
     });

 });
      
      /**
         * 点击退出系统
         */
        $("#loginout").click(function () {
            layer.confirm('是否确认退出系统',
                {
                    skin: 'layui-layer-admin',
                    title: '提示信息',
                    icon: 3,
                    shade: 0.1
                }, function (index) {
                    layer.close(index);
                    //退出系统
                    layer.load(2);
                    $.get('/member/index_do.php?fmdo=login&dopost=exit#', {}, function (res) {
                        layer.closeAll('loading');
                        if (res.code == 200) {
                            layer.msg("您已退出系统", {icon: 1, time: 2000}, function () {
                                //刷新页面
                                window.location.reload();
                                //返回到列表页
                                // window.location.replace("/article");
                            });
                        }
                    }, 'JSON');
                });
        });

   /**
         * 搜索
         */
   form.on("submit(searchForm)", function (data) {
      var keyword = $("#keywords").val();
        if (keyword == null || keyword == "") {
            layer.msg("请输入要搜索的关键字");
            return false
        }
    });
 

    
});

