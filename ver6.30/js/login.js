window.onload = function(){
	const signInBtn = document.getElementById("signIn");
	const signUpBtn = document.getElementById("signUp");
	const fistForm = document.getElementById("form1");
	const secondForm = document.getElementById("form2");
	const container = document.querySelector(".container");
	var pwdRegExp = /^(\w){6,20}$/;
	var telRegExp = /^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\d{8}$/;
	var pwdElt1 = document.getElementById("pwd1");
	var telElt = document.getElementById("tel1");
	var pwdError = document.getElementById("pwdError");
	var telError = document.getElementById("telError");

	signInBtn.addEventListener("click", () => {
		container.classList.remove("right-panel-active");
	});

	signUpBtn.addEventListener("click", () => {
		container.classList.add("right-panel-active");
	});

	fistForm.addEventListener("submit", (e) => e.preventDefault());
	secondForm.addEventListener("submit", (e) => e.preventDefault());
	telElt.onblur = function(){
		var tel = telElt.value;
		var ok1 = telRegExp.test(tel);
		if(!ok1){
			telError.innerText = "输入的号码不存在";
		}
	}
	telElt.onfocus = function (){
		if(telError.innerText != ""){
			telElt.value = "";
		}
		telError.innerText = "";
	}


	pwdElt1.onblur = function(){
		var pwd = pwdElt1.value;
		var ok2 = pwdRegExp.test(pwd);
		if(!ok2){
			pwdError.innerText = "密码不合法";
		}
	}
	pwdElt1.onfocus = function (){
		if(pwdError.innerText != ""){
			pwdElt1.value = "";
		}
		pwdError.innerText = "";
	}



}