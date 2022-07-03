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
        $("#userInfo").val($("#info").text());
        $("#userSignature").val($("#signature").text());
        $("#userName").val($("#name").text());
    });

    $(".changeBtn").click(function(){
        
        $("#info").text($("#userInfo").val());
        $("#signature").text($("#userSignature").val());
        $("#name").text($("#userName").val());
    });
  });
  