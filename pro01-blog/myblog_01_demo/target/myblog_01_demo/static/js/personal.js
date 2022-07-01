var bioActive = false;

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