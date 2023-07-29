/*
 * Copyright (c) 2019 Marketify
 * Author: Marketify
 * This file is made for CURRENT TEMPLATE
 更多精品模板：http://www.bootstrapmb.com
*/


jQuery(document).ready(function(){

	"use strict";
	
	// -----------------------------------------------------
	// ----------    RESUMIFY MENU ANIMATION    ------------
	// -----------------------------------------------------

	function resumify_tm_menu_animation(){

		setTimeout(function(){
			jQuery('.resumify_fn_wrapper_all').addClass('ready');
		  }, 2500);
		  var leftPart = jQuery('.resumify_tm_leftpart'), leftPartWidth = leftPart.width()/2;
		  var vcard = jQuery('.resumify_tm_leftpart .bottom_parts');
		  var bg = jQuery('.resumify_tm_leftpart .bottom_parts .fff');
		  var menuList = jQuery('.resumify_tm_leftpart .menu_list > ul > li');
		  var vcardIn = jQuery('.resumify_tm_leftpart .details_wrap');
		  var image = jQuery('.resumify_tm_leftpart .author_image .image_inner');
		  var spanA = jQuery('.resumify_tm_leftpart .bottom_parts .wing');
		  
		  // 动画效果注释
		  TweenMax.staggerTo(menuList, 0.5, {left: "15px", opacity: 0.05, delay: 2}, 0.15); // 菜单链接的动画效果
		  TweenMax.staggerTo(menuList, 0.5, {left: "0px", opacity: 1, delay: 2.5}, 0.15); // 菜单链接的动画效果
		  TweenLite.to(leftPart, 1.5, {transform: "translateX(-"+leftPartWidth+"px)", opacity: 1, ease: Circ.easeOut}); // 菜单的出现和扩大动画效果
		  TweenLite.to(leftPart, 1, {transform: "translateX(0) translateY(0)", delay: 2}); // 菜单向左移动的动画效果
		  TweenLite.to(image, 0.5, {top: "10px", left: "10px", right: "10px", bottom: "10px", opacity: 1, delay: 2.5}); // 图像的动画效果
		  TweenLite.to(spanA, 0.5, {top: "0px", opacity: 1, delay: 2.5}); // 翅膀的动画效果
		  setTimeout(function(){
			vcardIn.slideDown(800); // 底部菜单的出现动画效果
		  }, 2500);
		  TweenLite.to(bg, 1, {top: "0", delay: 1}); // 底部菜单背景的出现动画效果
		  TweenLite.to(vcard, 1, {opacity: 1, transform: "translateY(0)", delay: 1});
		  TweenLite.to(leftPart, 1, {left: "30px", delay: 2, ease: Circ.easeOut}); // 菜单向左移动的动画效果
		  
		setTimeout(function(){
			jQuery('.resumify_tm_rightpart .rightpart_inner').addClass('right_ready');
			jQuery('.resumify_tm_portfolio_list').addClass('opened');
			jQuery('.resumify_tm_rightpart .resume_wrap').addClass('opened');
			jQuery('.resumify_tm_news_list').addClass('opened');
			jQuery('.resumify_tm_rightpart .contact_wrap').addClass('opened');
			jQuery('.resumify_tm_news_single').addClass('opened');
			jQuery('.resumify_tm_rightpart .home_hero').addClass('appear');
		},3100);

	}
	
	function resumify_tm_menu() {
		jQuery('.resumify_fn_wrapper_all').addClass('ready');
	  
		var leftPart = jQuery('.resumify_tm_leftpart');
		var leftPartWidth = leftPart.width() / 2;
		var vcard = jQuery('.resumify_tm_leftpart .bottom_parts');
		var bg = jQuery('.resumify_tm_leftpart .bottom_parts .fff');
		var menuList = jQuery('.resumify_tm_leftpart .menu_list > ul > li');
		var vcardIn = jQuery('.resumify_tm_leftpart .details_wrap');
		var image = jQuery('.resumify_tm_leftpart .author_image .image_inner');
		var spanA = jQuery('.resumify_tm_leftpart .bottom_parts .wing');
	  
		// 删除动画效果
		menuList.css({ left: '15px', opacity: 1 });
		leftPart.css({ transform: 'translateX(-' + leftPartWidth + 'px)', opacity: 1 });
		image.css({ top: '10px', left: '10px', right: '10px', bottom: '10px', opacity: 1 });
		spanA.css({ top: '0px', opacity: 1 });
		vcardIn.show();
		bg.css({ top: '0' });
		vcard.css({ opacity: 1, transform: 'translateY(0)' });
		leftPart.css({ left: '200px' });
	  
		jQuery('.resumify_tm_rightpart .rightpart_inner').addClass('right_ready');
		jQuery('.resumify_tm_portfolio_list').addClass('opened');
		jQuery('.resumify_tm_rightpart .resume_wrap').addClass('opened');
		jQuery('.resumify_tm_news_list').addClass('opened');
		jQuery('.resumify_tm_rightpart .contact_wrap').addClass('opened');
		jQuery('.resumify_tm_news_single').addClass('opened');
		jQuery('.resumify_tm_rightpart .home_hero').addClass('appear');
	  }
	  
	// 检查是否是第一次访问页面
	if (!sessionStorage.getItem('hasVisited')) {
	// 第一次访问时运行
	resumify_tm_menu_animation();
  
	// 将标记设置为已访问
	sessionStorage.setItem('hasVisited', true);
  	} else {
		resumify_tm_menu();
	}  
	

	// -----------------------------------------------------
	// -------    RESUMIFY SUBMENU ANIMATION    ------------
	// -----------------------------------------------------

	function resumify_tm_animate_submenu(){

		var menuBox				= jQuery('.resumify_tm_submenu_box');
		var list				= jQuery('.resumify_tm_leftpart .menu_list > ul > li');
		var rightpart			= jQuery('.resumify_tm_rightpart');

		list.on('mouseenter',function(){
			var element		= jQuery(this);
			var subLength	= element.find('.submenu').length;
			var subContent	= element.find('.submenu').html();
			var liOffset	= element.offset().top;
			var winScroll	= jQuery(window).scrollTop();
			menuBox.css({top: liOffset-winScroll+30});

			if(subLength === 1){
				menuBox.addClass('opened').html('').html(subContent);
				resumify_tm_page_load();
			}else{
				menuBox.html('').removeClass('opened');
			}
		});
		rightpart.on('mouseenter',function(){
			menuBox.html('').removeClass('opened');
		});
	}
	resumify_tm_animate_submenu();
	
	// -------------------------------------------------
	// ---------   RESUMIFY SCROLLABLE  ----------------
	// -------------------------------------------------

	function resumify_tm_scrollable(){

		var H				= jQuery(window).height();
		var scrollable		= jQuery('.resumify_tm_leftpart .scrollable');
		var verMenu			= jQuery('.resumify_tm_leftpart .menu_list');
		var bottomPart		= jQuery('.resumify_tm_leftpart .bottom_parts').outerHeight()+180;

		verMenu.css({height:H-bottomPart});

		scrollable.each(function(){
			var element		= jQuery(this);

			element.css({height: H-bottomPart}).niceScroll({
				touchbehavior:false,
				cursorwidth:0,
				autohidemode:true,
				cursorborder:"0px solid #eee"
			});
		});
	}
	resumify_tm_scrollable();
	
	// -----------------------------------------------------
	// ---------------  HAMBURGER  -------------------------
	// -----------------------------------------------------

	function resumify_tm_hamburger(){

		var hamburger 		= jQuery('.hamburger');
		var mobileMenu		= jQuery('.resumify_tm_mobile_menu_wrap');

		hamburger.on('click',function(){
			var element 	= jQuery(this);

			if(element.hasClass('is-active')){
				element.removeClass('is-active');
				mobileMenu.slideUp();
			}else{
				element.addClass('is-active');
				mobileMenu.slideDown();
			}
			return false;
		});
	}
	resumify_tm_hamburger();
	
	// -------------------------------------------------
	// --------------   MOBILE MENU  -------------------
	// -------------------------------------------------

	function resumify_mobile(){

		jQuery('.resumify_tm_mobile_menu_wrap .mob_menu > ul > li a').off().on('click',function(e){
			e.stopPropagation();
			var element = jQuery(this);
			var url			= element.attr('href');
			if(url !== '#' && url.charAt(0) === '#'){
				jQuery('html, body').animate({
					scrollTop: jQuery(url).offset().top-100
				}, 1000);
			}else if(element.siblings('ul').length){
				var submenu	= element.siblings('ul');
				var shape	= jQuery('.resumify_tm_mobile_menu_wrap .mob_menu > ul > li.shape');
				if(submenu.hasClass('opened')){
					submenu.removeClass('opened');
					submenu.slideUp();
					shape.removeClass('animate');
				}else{
					submenu.addClass('opened');
					submenu.slideDown();
					shape.addClass('animate');
				}

			}else{
				window.location = url; 
			}
			return false;
		});

	}
	resumify_mobile();
	
	// -----------------------------------------------------
	// -----------------    PROGRESS BAR    ----------------
	// -----------------------------------------------------

	// function tdProgress(container){

	// 	container.find('.resumify_tm_progress').each(function(i) {
	// 		var progress 		= jQuery(this);
	// 		var pValue 			= parseInt(progress.data('value'), 10);
	// 		var pColor			= progress.data('color');
	// 		var pBarWrap 		= progress.find('.resumify_tm_bar_wrap');
	// 		var pBar 			= progress.find('.resumify_tm_bar');
	// 		pBar.css({width:pValue+'%', backgroundColor:pColor});
	// 		setTimeout(function(){pBarWrap.addClass('open');},(i*500));
	// 	});
	// }

	// function muzaffarFunction(){
	// 	var isSmallScreen = jQuery(window).width() < 820;

	// 	if (isSmallScreen) {
	// 		jQuery(window).on('scroll', function() {
	// 			jQuery('.resumify_tm_progress_wrap').each(function() {
	// 				var pWrap 			= jQuery(this);
	// 				pWrap.waypoint({handler: function(){tdProgress(pWrap);},offset:'90%'});	
	// 			});
	// 		});
	// 	} else {
	// 		jQuery('.resumify_tm_progress_wrap').each(function() {
	// 			var pWrap 			= jQuery(this);
	// 			pWrap.waypoint({handler: function(){tdProgress(pWrap);},offset:'90%'});	
	// 		});
	// 	}
	// }
	// muzaffarFunction();

	
	
	// -----------------------------------------------------
	// ---------------   DATA IMAGES    --------------------
	// -----------------------------------------------------

	function resumify_tm_data_images(){

		var data			= jQuery('*[data-img-url]');

		data.each(function(){
			var element			= jQuery(this);
			var url				= element.data('img-url');
			element.css({backgroundImage: 'url('+url+')'});
		});
	}
	resumify_tm_data_images();
	
	// -----------------------------------------------------
	// -----------   RESUMIFY PAGE LOAD    -----------------
	// -----------------------------------------------------

	// function resumify_tm_page_load(){

	// 	var preloader = jQuery('.wrapper_all');
	// 	jQuery('.resumify_tm_leftpart .menu_list > ul > li a,.resumify_tm_submenu_box a').on('click',function(){
	// 		var href	= jQuery(this).attr('href');
	// 		if(href === "#"){return false;}
	// 		preloader.removeClass('hide');
	// 		jQuery.ajax({
	// 			type: "GET",
	// 			url: href,
	// 			data: {},
	// 			success: function(data){
	// 				var html	= jQuery(data).find('.resumify_tm_rightpart_changable_section').html();
	// 				jQuery('.resumify_tm_all_wrap .resumify_tm_rightpart_changable_section').html(html);
	// 				resumify_tm_data_images();
	// 				resumify_tm_popup();
	// 				resumify_tm_contact_form();
	// 				resumify_tm_load_more();
	// 				setTimeout(function(){
	// 					preloader = jQuery('.wrapper_all');
	// 					preloader.addClass('hide');
	// 					jQuery('.resumify_tm_rightpart .rightpart_inner').addClass('right_ready');
	// 					jQuery('.resumify_tm_portfolio_list').addClass('opened');
	// 					jQuery('.resumify_tm_rightpart .resume_wrap').addClass('opened');
	// 					jQuery('.resumify_tm_news_single').addClass('opened');
	// 					jQuery('.resumify_tm_news_list').addClass('opened');
	// 					jQuery('.resumify_tm_rightpart .contact_wrap').addClass('opened');
	// 					jQuery('.resumify_tm_rightpart .home_hero').addClass('appear');
	// 					muzaffarFunction();
	// 				},3100);

	// 			}
	// 		});
	// 		return false;
	// 	});

	// }
	// resumify_tm_page_load();
	
	// -----------------------------------------------------
	// ----------------    CONTACT FORM    -----------------
	// -----------------------------------------------------

	function resumify_tm_contact_form(){

		jQuery(".contact_form #send_message").on('click', function(){

			var name 		= jQuery(".contact_form #name").val();
			var email 		= jQuery(".contact_form #email").val();
			var message 	= jQuery(".contact_form #message").val();
			var subject 	= jQuery(".contact_form #subject").val();
			var success     = jQuery(".contact_form .returnmessage").data('success');

			jQuery(".contact_form .returnmessage").empty(); //To empty previous error/success message.
			//checking for blank fields	
			if(name===''||email===''||message===''){

				jQuery('div.empty_notice').slideDown(500).delay(2000).slideUp(500);
			}
			else{
				// Returns successful data submission message when the entered information is stored in database.
				jQuery.post("modal/contact.php",{ ajax_name: name, ajax_email: email, ajax_message:message, ajax_subject: subject}, function(data) {

					jQuery(".contact_form .returnmessage").append(data);//Append returned message to message paragraph


					if(jQuery(".contact_form .returnmessage span.contact_error").length){
						jQuery(".contact_form .returnmessage").slideDown(500).delay(2000).slideUp(500);		
					}else{
						jQuery(".contact_form .returnmessage").append("<span class='contact_success'>"+ success +"</span>");
						jQuery(".contact_form .returnmessage").slideDown(500).delay(4000).slideUp(500);
					}

					if(data===""){
						jQuery("#contact_form")[0].reset();//To reset form fields on success
					}

				});
			}
			return false; 
		});
	}
	resumify_tm_contact_form();
	
	// -----------------------------------------------------
	// -----------------------    POPUP    -----------------
	// -----------------------------------------------------

	function resumify_tm_popup(){

		jQuery('.gallery_zoom').each(function() { // the containers for all your galleries
			jQuery(this).magnificPopup({
				delegate: 'a.zoom', // the selector for gallery item
				type: 'image',
				gallery: {
				  enabled:true
				},
				removalDelay: 300,
				mainClass: 'mfp-fade'
			});

		});
	}
	resumify_tm_popup();
	
	// -----------------------------------------------------
	// --------------    AJAX CUSTOM    --------------------
	// -----------------------------------------------------

	function resumify_tm_load_more(){

		"use strict";

		jQuery('.resumify_tm_load_more a').on('click', function(){
			console.log('true');

			var element 	= jQuery(this);
			var shape		= jQuery('.resumify_tm_load_more .svg');
			var span		= element.find('span');

			if(!element.hasClass('opened')){
				element.addClass('opened');
				shape.addClass('animate');
				span.addClass('change');
				jQuery.ajax({
					type: 'POST',
					url: 'js/portfolio.json',
					dataType: 'json',
					success: function(data) {

						setTimeout(function(){

							for(var i = 0; i<data.portfolio.length; i++){

							var list = '<li><div class="inner"><div class="image_wrap"><img src="img/portfolio/390x390.jpg" alt="" /><div class="image" style="background-image: url('+data.portfolio[i].imgUrl+')"></div></div><div class="details"><h3><a href="#">'+data.portfolio[i].title+'</a></h3><span>'+data.portfolio[i].subTitle+'</span></div><a class="full_link zoom" href="'+data.portfolio[i].imgUrl+'"></a></div></li>';

							jQuery('.resumify_tm_portfolio_list ul').append(list);
							shape.removeClass('animate');
							span.removeClass('change');
							element.hide();
						}

						},1500);

					},
					error: function(a,b,c){

					}

				});
			}
			return false;
		});
	}
	resumify_tm_load_more;
	
	//jQuery(".youtube-bg").mb_YTPlayer();
	
	
	jQuery(window).on('resize',function(){
		resumify_tm_scrollable();
	});
});