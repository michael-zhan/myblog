const signInBtn = document.getElementById("signIn");
const signUpBtn = document.getElementById("signUp");
const fistForm = document.getElementById("form1");
const secondForm = document.getElementById("form2");
const container = document.querySelector(".container");

signInBtn.addEventListener("click", () => {
	container.classList.remove("right-panel-active");
});

signUpBtn.addEventListener("click", () => {
	container.classList.add("right-panel-active");
});

fistForm.addEventListener("submit", (e) => e.preventDefault());
secondForm.addEventListener("submit", (e) => e.preventDefault());
// ^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$

document.getElementById("btn1").onclick = function(){
	var pwd = document.getElementById("pwd").value;
	var pwdRegExp = /^(\w){6,20}$/;
	var tel = document.getElementById("tel").value;
	var telExp = /^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\d{8}$/;
	var ok1 = pwdRegExp.test(pwd);
	var ok2 = telExp.test(tel);
	if(!ok1){
		document.getElementById("pwdError").innerText = "密码只能由6-20个字母、数字、下划线组成";
	}if(!ok2){
		document.getElementById("telError").innerText = "输入的号码不存在";
	}
}

document.getElementById("btn2").onclick = function(){
	var pwd = document.getElementById("pwd").value;
	if(pwd!=123){
		document.getElementById("pwdWrong").innerText = "密码错误";
	}

}

