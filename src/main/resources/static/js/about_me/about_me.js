$(document).ready(function() {
	//打开新建news的modal
    $('#openModal').click(function() {
		var seqValue = $('#seq').val();
		console.log(seqValue);
		if (seqValue === '0') {
			$('.deleteBtn').hide();
		} else {
			$('.deleteBtn').show();
		}
		$('#modalNewsInsert').addClass('show');
      	$('#modalNewsInsert').fadeIn('fast');
    });
    
    //打开修改news的modal
	$('.openModal').click(function() {
		$('#seq').val($(this).find('.seq').val());
      	$('.deleteSeq').val($(this).find('.seq').val());
      	$('.insDate').val($(this).find('.date').text());
		$('.news').val($(this).find('.newsView').text());
		$('.url').val($(this).find('.urlView').attr('href'));
		var seqValue = $('#seq').val();
		console.log(seqValue);
		if (seqValue === '0') {
			$('.deleteBtn').hide();
		} else {
			$('.deleteBtn').show();
		}
		$('#modalNewsInsert').addClass('show');
      	$('#modalNewsInsert').fadeIn('fast');
      	
    });
    
    $('.overBgClose').click(function() {
		$('#modalNewsInsert').removeClass('show');
      	$('#modalNewsInsert').fadeOut('fast');
    });
    
    $('.saveBtn').click(function(e) {
		e.preventDefault();
		$('#insDate').val($('.insDate').val());
		$('#news').val($('.news').val());
		$('#url').val($('.url').val());
		
		$('#newsForm').submit();
	});
	
	 $('.deleteBtn').click(function(e) {
		e.preventDefault();	
		if (confirm("确定要删除吗？")) {
		    $('#deleteNewsForm').submit();
		}	
	});
  });