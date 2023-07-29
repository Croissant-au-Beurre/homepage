$(document).ready(function() {
  $('.auteurs-text').each(function() {
    var pElement = $(this);
    var pText = pElement.text();

    if (pText.includes('Wei Dai')) {
      var modifiedText = pText.replace('Wei Dai', '<span class="auteur">Wei Dai</span>');
      pElement.html(modifiedText);
    }
  });
  
  setScrollAndBlink()
});


$(document).ready(function() {
	
	//打开新建news的modal
    $('#openModal').click(function() {
		$('#modalPublicationInsert').addClass('show');
      	$('#modalPublicationInsert').fadeIn('fast');      	
      	$('#seq').val(0);
      	$('.deleteSeq').val(0);
      	$('.title').val('');
		$('.auteurs').val('');
		$('.doi').val('');
		$('.url').val('');
		var seqValue = $('#seq').val();
		if (seqValue === '0') {
			$('.deleteBtn').hide();
		} else {
			$('.deleteBtn').show();
		}
    });
    
    //打开修改news的modal
	$('.openModal').click(function() {	
		$('#modalPublicationInsert').addClass('show');
      	$('#modalPublicationInsert').fadeIn('fast');
      	$('#seq').val($(this).find('.seq-view').val());
      	$('.deleteSeq').val($(this).find('.seq-view').val());
      	$('.deleteBtn').val($(this).find('.seq-view').val());
      	$('.title').val($(this).find('.title-view').text());
		$('.auteurs').val($(this).find('.auteurs-text').text());
		$('.doi').val($(this).find('.doi-view').text());
		$('.url').val($(this).find('.publication-list-a').attr('href'));
		var seqValue = $('#seq').val();
		if (seqValue === '0') {
			$('.deleteBtn').hide();
		} else {
			$('.deleteBtn').show();
		}
    });
    
    $('.overBgClose').click(function() {
		$('#fromDetailFlg').val($('#seq').val());
		$('#modalPublicationInsert').removeClass('show');
      	$('#modalPublicationInsert').fadeOut('fast');
      	setScrollAndBlink()
    });
    
    $('.saveBtn').click(function(e) {
		e.preventDefault();
		$('#publicationForm').submit();
		setScrollAndBlink()
	});
	
	 $('.deleteBtn').click(function(e) {
		e.preventDefault();	
		$('.deleteSeq').val($(this).val());
		if (confirm("确定要删除吗？")) {
		    $('#deletePublicationForm').submit();
		}	
	});
  });
  
//スクロールとブリンクの設定
function setScrollAndBlink() {

	//対象車輌番号
	var publicationNo = $('#fromDetailFlg').val();
	if (publicationNo == '' || publicationNo == null || publicationNo == undefined) {
		//対象車輌番号はない場合
		return;
	}
	
	//対象車輌行のインデックス
	var index;
	
	//車輌一覧行ループ
	$('.publication-list .item').each(function(i) {
		if ($(this).data('publication-no') == publicationNo) {
		//行の車輌番号 = 対象車輌番号の場合
		
		//対象車輌行のインデックス
		index = i;
		}
	});
	
	//枠
	var windowSize = window.innerWidth;
	
	if (windowSize >992) {
		var w = $('.publication-list').parent();
	}else {
		var w = $('.rightpart_inner');
	}
	console.log(index);
	//行
	var row = $('.publication-list').find('.item').eq(index);
	
	if (row.length){
	
		//行のオフセット(上)
		var rowOffsetTop = row.offset().top;
		
		if (windowSize >992) {
			//スクロールの設定
			w.scrollTop(rowOffsetTop - 190);
		}else {
			w.scrollTop(rowOffsetTop - 70);
		}
	
	}
	
	//行のセルループ
	$(row).find('div').each(function() {
	
		//ブリンククラスの追加
		$(this).addClass('blink');
	});
	
	//点滅の停止
	setTimeout(function(){
	$(row).find('div').each(function() {
	
		//ブリンククラスの追加
		$(this).removeClass('blink');
		});
	}, 5000);
}