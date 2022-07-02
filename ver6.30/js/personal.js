var bioActive = false;
$(".copy-block a").hide();
function toggleBio() {
    if (bioActive == false) {
        firstClass = 'expand-width';
        secondClass = 'expand-height';
        bioActive = true;
    } else {
        firstClass = 'expand-height';
        secondClass = 'expand-width';
        bioActive = false;
    }

    $(".wrap-center").toggleClass("bio-active").toggleClass(firstClass).delay(500).queue(function () {
        $(this).toggleClass(secondClass).dequeue();
    });
}
$(".btn-about, .close-about, .toggle-about").on("click", toggleBio);


$(document).ready(function(){
    $(".about").hover(function(){
        $(".copy-block a").show();
    },function(){
        $(".copy-block a").hide();
    });

    $(".copy-block a").click(function(){
        $("#userInfo").attr("value",$("#info").text());
        $("#userSignature").attr("value",$("#signature").text());
    });

    $(".changeBtn").click(function(){
        
        $("#info").text($("#userInfo").attr("value"));
        alert($("#userInfo").attr("value"));
        $("#signature").text($("#userSignature").attr("value"));
    });
  });
  