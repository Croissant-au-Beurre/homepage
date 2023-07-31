$(function () {
  document.querySelector('.appear').classList.toggle('inview');
});

$(function() {
  $('.submit').click(function() {
	  console.log("clicked");
    $('.loginForm').submit();
  });
});

/* 获取URL中的参数 */
function getUrlParam(paramName) {
    var urlParams = new URLSearchParams(window.location.search);
    return urlParams.get(paramName);
}

/* 在页面加载时检查是否有错误参数，如果有，则显示错误提示框 */
$(document).ready(function() {
    var errorParam = getUrlParam('error');
    var blankParam = getUrlParam('blank');
    if (errorParam) {
        // 显示错误提示框
    	alert("账号或密码错误！");
    } 
    if(blankParam) {
    	alert("账号或密码为空！");
    }
});