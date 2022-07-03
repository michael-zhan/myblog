window.onload = function(){
	const signInBtn = document.getElementById("signIn");
	const signUpBtn = document.getElementById("signUp");
	const fistForm = document.getElementById("form1");
	const secondForm = document.getElementById("form2");
	const container = document.querySelector(".container");
	var pwdRegExp = /^(\w){6,20}$/;
	var telRegExp = /^((?!\.)[\w-_.]*[^.])(@\w+)(\.\w+(\.\w+)?[^.\W])$/;//修改为邮箱正则表达式
	var pwdElt1 = document.getElementById("pwd1");
	var telElt = document.getElementById("tel1");
	var pwdError = document.getElementById("pwdError");
	var telError = document.getElementById("telError");
	// 新增：获取了页面几个对象
	var signInBtn2 = document.getElementById("btn1");
	var userElt = document.getElementById("userName");

	signInBtn.addEventListener("click", () => {
		container.classList.remove("right-panel-active");
	});

	signUpBtn.addEventListener("click", () => {
		container.classList.add("right-panel-active");
	});

	// fistForm.addEventListener("submit", (e) => e.preventDefault());
	// secondForm.addEventListener("submit", (e) => e.preventDefault());
	telElt.onblur = function(){
		var tel = telElt.value;
		var ok1 = telRegExp.test(tel);
		if(!ok1){
			telError.innerText = "输入的邮箱格式不合法";//修改了文字提示
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
			pwdError.innerText = "密码格式不合法";
		}
	}
	pwdElt1.onfocus = function (){
		if(pwdError.innerText != ""){
			pwdElt1.value = "";
		}
		pwdError.innerText = "";
	}

	// 新增：点登录按钮验证格式
	signInBtn2.onclick = function(){
		if((pwdError.innerText == telError.innerText)&&(telElt.value!="")&&(pwdElt1.value!="")&&(userElt.value!="")){
			// 做登录成功操作
			alert("好好好");
		}else{
			alert("不合法");
		}
	}
}