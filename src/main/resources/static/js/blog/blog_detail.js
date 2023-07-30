function uploadFile() {
    const fileInput = document.getElementById('fileInput');
    const file = fileInput.files[0];

    if (file) {
        const formData = new FormData();
        formData.append('file', file);

        fetch('/upload', {
            method: 'POST',
            body: formData
        })
        .then(response => response.text())
        .then(result => {
            alert('文件上传成功');
            fileInput.value = ''; // 清空文件选择
        })
        .catch(error => {
            console.error('文件上传失败:', error);
        });
    } else {
        alert('请选择要上传的文件');
    }
}

$(function() {
    const $form = $("#form");
  
    /**
     * 登録ボタン, 更新ボタンを押した時の処理
     * - 二重押し防止のため、ボタンを無効化する
     */
    $("#btnInsert, #btnUpdate").on("click", function() {
		const $btn = $(this);
  		$btn.prop("disabled", true);
		
		$("#date").val($(".date").val());
		$("#title").val($(".title").val());
		$("#content").val($(".content").val());

  		const messages = [];
  		const date = $("#date").val();
  		if (isEmpty(date)) {
    		messages.push("请输入日期。");
  		}
  		const title = $("#title").val();
  		if (isEmpty(title)) {
    	messages.push("请输入标题。");
  		}
  		const content = $("#content").val();
  		if (isEmpty(content)) {
    		messages.push("请输入内容。");
  		}
  		if (messages.length > 0) {
    		messages.forEach((message, i) => {
      			homepage_custom_alert_add("danger", message, i === 0);
    		});
   			$btn.prop("disabled", false);
    		return;
      	}
  
      	const seq = $("#seq").val();
      	const confirmationMessage = Number(seq) ? "确认更新吗？" : "确认新建吗?";
      	if (confirm(confirmationMessage)) {

        	const input = $("<input>").attr("type", "hidden").attr("name", "regist").val("");
        	$form.append(input);
        	$form.submit();
        	input.remove();
      	}
  
      	$btn.prop("disabled", false);
      	return false;
	});
  
    /**
     * 削除ボタンを押した時の処理
     * - 二重押し防止のため、ボタンを無効化する
     */
    $("#btnDelete").on("click", function() {
      const $btn = $(this);
      $btn.prop("disabled", true);
  
      if (confirm("确认要删除吗?")) {
        // フォーム送信
        const input = $("<input>").attr("type", "hidden").attr("name", "delete").val("");
        $form.append(input);
        $form.submit();
        input.remove();
      }
  
      // ボタン有効化
      $btn.prop("disabled", false);
      return false;
    });
	//戻るボタン
	$("#btnBack").click(function() {
		var seq = $("#seq").val();
		localStorage.setItem("seq", seq);
		window.location.href = "/blog?fromDetailPage=true"; 
		setScrollAndBlink; 
	});
});


function isEmpty(value) {
  if (value === null || value === undefined) {
    return true;
  }
  
  if (typeof value === 'string' && value.trim() === '') {
    return true;
  }
  
  if (Array.isArray(value) && value.length === 0) {
    return true;
  }
  
  if (typeof value === 'object' && Object.keys(value).length === 0) {
    return true;
  }
  
  return false;
}

function homepage_custom_alert_add(type, message, isFirst) {
  // 显示警告框
  alert(message);
}
