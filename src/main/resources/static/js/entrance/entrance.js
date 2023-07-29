$(function () {
  document.querySelector('.appear').classList.toggle('inview');
});

$(function() {
  $('.submit').click(function() {
	  console.log("clicked");
    $('.loginForm').submit();
  });
});
  