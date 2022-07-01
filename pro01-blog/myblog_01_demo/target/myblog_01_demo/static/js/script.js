
$(document).ready(function(){
    var flag = 0;
    $(".like").click(function(){
      if(flag == 0){
        $(".like img").attr("src","../img/like-active.png");
        flag = 1;
      }else{
        $(".like img").attr("src","../img/like.png");
        flag = 0;
      }
    });
  });
  