function setTheme(){
	if(localStorage.getItem("themeFlag")==null){
		localStorage.setItem("themeFlag","day");
	}else if(localStorage.getItem("themeFlag")=="day"){
		$("body").removeClass("dark");
	}else{
		$("body").addClass("dark");
	}
}
setTheme();
function changeTheme(){
	if(localStorage.getItem("themeFlag")=="day"){
		$("body").addClass("dark");
		localStorage.setItem("themeFlag","night");
	}else{
		$("body").removeClass("dark");
		localStorage.setItem("themeFlag","day");
	}
}


	$(function() {
		var header = $(".start-style");
		
		$(window).scroll(function() {    
			var scroll = $(window).scrollTop();
		
			if (scroll >= 10) {
				header.removeClass('start-style').addClass("scroll-on");
			} else {
				header.removeClass("scroll-on").addClass('start-style');
			}
		});
		$("#switch").click(function(){
			changeTheme();
		});
	});		
		
	//Animation
	

	//Menu On Hover
		
	$('body').on('mouseenter mouseleave','.nav-item',function(e){
			if ($(window).width() > 750) {
				var _d=$(e.target).closest('.nav-item');_d.addClass('show');
				setTimeout(function(){
				_d[_d.is(':hover')?'addClass':'removeClass']('show');
				},1);
			}
	});	
	
	//Switch light/dark
	
	
	 
