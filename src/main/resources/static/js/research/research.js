$(document).ready(function() {
	
	//打开新建news的modal
    $('#openModal').click(function() {
		$('#modalResearchInsert').addClass('show');
      	$('#modalResearchInsert').fadeIn('fast');      	
      	$('#seq').val(0);
      	$('.deleteSeq').val(0);
      	$('.content').val('');		
		var seqValue = $('#seq').val();
		if (seqValue === '0') {
			$('.deleteBtn').hide();
		} else {
			$('.deleteBtn').show();
		}
    });
    
    //打开修改news的modal
	$('.openModal').click(function() {	
		$('#modalResearchInsert').addClass('show');
      	$('#modalResearchInsert').fadeIn('fast');
      	$('#seq').val($(this).find('.seq-view').val());
      	$('.deleteSeq').val($(this).find('.seq-view').val());
      	$('.deleteBtn').val($(this).find('.seq-view').val());
      	$('.content').val($(this).find('.content-view').text());
		var seqValue = $('#seq').val();
		if (seqValue === '0') {
			$('.deleteBtn').hide();
		} else {
			$('.deleteBtn').show();
		}
    });
    
    $('.overBgClose').click(function() {
		$('#modalResearchInsert').removeClass('show');
      	$('#modalResearchInsert').fadeOut('fast');
    });
    
    $('.saveBtn').click(function(e) {
		e.preventDefault();
		$('#researchForm').submit();
	});
	
	 $('.deleteBtn').click(function(e) {
		e.preventDefault();	
		$('.deleteSeq').val($(this).val());
		if (confirm("确定要删除吗？")) {
		    $('#deleteResearchForm').submit();
		}	
	});
  });