function change() {
    $('#kaptchaImage').click(function () {
        $(this).attr('src', '${pageContext.request.contextPath}/getVerifyCode?' + Math.floor(Math.random() * 100));
    })
}
