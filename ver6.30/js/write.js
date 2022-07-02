function bs_input_file() {
	$(".input-file").before(
		function() {
			if ( ! $(this).prev().hasClass('input-ghost') ) {
				var element = $("<input type='file' class='input-ghost' style='visibility:hidden; height:0'>");
				element.attr("name",$(this).attr("name"));
				element.change(function(){
					element.next(element).find('input').val((element.val()).split('\\').pop());
				});
				$(this).find("button.btn-choose").click(function(){
					element.click();
				});
				$(this).find("button.btn-reset").click(function(){
					element.val(null);
					$(this).parents(".input-file").find('input').val('');
				});
				$(this).find('input').css("cursor","pointer");
				$(this).find('input').mousedown(function() {
					$(this).parents('.input-file').prev().click();
					return false;
				});
				return element;
			}
		}
	);
}

$(function(){
    $('.chosen').chosen({
          no_results_text: "没有找到结果！",//搜索无结果时显示的提示  
          search_contains:true,   //关键字模糊搜索。设置为true，只要选项包含搜索词就会显示；设置为false，则要求从选项开头开始匹配
          allow_single_deselect:false, //单选下拉框是否允许取消选择。如果允许，选中选项会有一个x号可以删除选项
          disable_search: false, //禁用搜索。设置为true，则无法搜索选项。
          disable_search_threshold: 0, //当选项少等于于指定个数时禁用搜索。
          inherit_select_classes: true, //是否继承原下拉框的样式类，此处设为继承
          placeholder_text_single: '选择分类', //单选选择框的默认提示信息，当选项为空时会显示。如果原下拉框设置了data-placeholder，会覆盖这里的值。
        //   width: '50%', //设置chosen下拉框的宽度。即使原下拉框本身设置了宽度，也会被width覆盖。
          outerHeight: '50px',
          max_shown_results: 1000, //下拉框最大显示选项数量
          display_disabled_options: false,
          single_backstroke_delete: false, //false表示按两次删除键才能删除选项，true表示按一次删除键即可删除
          case_sensitive_search: false, //搜索大小写敏感。此处设为不敏感
          group_search: false, //选项组是否可搜。此处搜索不可搜
          include_group_label_in_selected: true //选中选项是否显示选项分组。false不显示，true显示。默认false。
     });
     bs_input_file();
  });