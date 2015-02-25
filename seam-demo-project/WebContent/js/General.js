jQuery(function() {
	jQuery('._hv').hover(
			function() {
				jQuery(this).attr('src',
						jQuery(this).attr('src').replace('.png', '_hv.png'));
			},
			function() {
				jQuery(this).attr('src',
						jQuery(this).attr('src').replace('_hv.png', '.png'));
			});
});

_zindex = 1000;
function showModelPopup(containner,isDialog,hideOnOutsideClick,zIndex) {
	var z_index = 0;
	if (zIndex != undefined) {
		z_index = zIndex;
	} else {
		z_index = _zindex;
	}
		
	var windowHeight = jQuery(window).height();
	var windowWidth = jQuery(window).width();
	var newLeft = 0;
	var newTop = 0;
	var targetContainer = jQuery('.' + containner);
	var numberOfModels = jQuery('.faliaModel').length;
	targetContainer.addClass('_mdd' + containner);
	var model = "<div rel='_mdd"
			+ containner
			+ "' class=\"faliaModel _md"
			+ containner
			+ "\" style=\"display:none;width:100%;height:100%;background:#000;position:absolute;top:0px;left:0px;z-index:100;filter: alpha(opacity=10); -khtml-opacity: 0.1; -moz-opacity: 0.1; opacity: 0.1;\"></div>";
	if (jQuery("._md" + containner).length == 0) {
		jQuery('body').append(model);
	}
	if (isDialog == undefined) {
		isDialog = 0;
	}
	if (isDialog == 0) {
		jQuery("._md" + containner).fadeIn(500).height(jQuery(document).height() + 'px');
	}
	jQuery("._md" + containner).css('z-index', ++z_index);
	targetContainer.css('z-index', ++z_index);
	var windowPositionTop = jQuery(window).scrollTop();
	if (targetContainer.width() > windowWidth) {
		targetContainer.css('width', windowWidth - 100);
		newLeft = 50;
	} else if (targetContainer.width() < windowWidth) {
		newLeft = (windowWidth - targetContainer.width()) / 2;
	}
	if (targetContainer.height() > windowHeight) {
		targetContainer.css('height', windowHeight - 100);
		newTop = 50;
	} else if (targetContainer.height() < windowHeight) {
		newTop = (windowHeight - targetContainer.height()) / 2;
	}
	targetContainer.css('left', newLeft);
	targetContainer.css('top', windowPositionTop + newTop);
	targetContainer.fadeIn(200);
	targetContainer.find('.closePopup').click(function() {
		hideModel();
	});
	jQuery('._md' + containner).click(function() {
		if(hideOnOutsideClick== undefined || hideOnOutsideClick=="true")
			hideModel();
	});
	function hideModel() {
		jQuery('._md' + containner).fadeOut(500);
		jQuery('.' + jQuery('._md' + containner).attr('rel')).fadeOut(500);
		if (typeof closeOperation == 'function') {
			closeOperation();
		}
		if(containner==undefined){
			return;
		}
		if(containner.indexOf("saveloadingPanel")!=-1){
			return;
		}
		currentAlertPopup="";
		//	alert("general 1  :: " + containner +"::::::"+ currentPopUpTohide2 +" :: " +currentPopUpTohide1 );
		if(containner.indexOf("widgetsPopup")!=-1 || containner.indexOf("interactivity")!=-1  ){
			return;
		}
		if(containner.indexOf("validateFilePopupv1")!=-1){
			return;
		}
		//	alert("i am called 2" );
		if(containner.indexOf("WithHotspot")!=-1){
			currentActionOnEnter2="";
			currentPopUpTohide2="";
			return;
		}
		currentPopUpTohide2="";
		currentPopUpTohide1="";
		currentActionOnEnter1="";
		currentActionOnEnter2="";
	};
}

function hideModel(_container) {
	if(_container==undefined){
		return;
	}
	jQuery('._md' + _container).fadeOut(500);
	jQuery('.' + jQuery('._md' + _container).attr('rel')).fadeOut(250);
	if (typeof closeOperation == 'function') {
		closeOperation();
	}
	if(_container==undefined){
		return;
	}
	if(_container.indexOf("saveloadingPanel")!=-1){
		return;
	}
	currentAlertPopup="";
	//alert("general 1  :: " + _container+" ::: " + currentPopUpTohide2 +" :: " +currentPopUpTohide1 );
	if(_container.indexOf("widgetsPopup")!=-1 || _container.indexOf("interactivity")!=-1){
		return;
	}
	if(_container.indexOf("validateFilePopupv1")!=-1){
		return;
	}
	if(_container.indexOf("WithHotspot")!=-1){
		currentActionOnEnter2="";
		currentPopUpTohide2="";
		return;
	}
	currentPopUpTohide2="";
	currentPopUpTohide1="";
	currentActionOnEnter1="";
	currentActionOnEnter2="";
}

function showSlidingPopup(popupclass, hideOnBody, _zIndex){
	 if(jQuery("."+popupclass).hasClass("popup-slide-up")){
		 jQuery("."+popupclass).removeClass("popup-slide-up");
	   return;
	 }
	 
	 var z_index = 0;
	 if (_zIndex != undefined) {
		z_index = _zIndex;
	 } else {
		z_index = '1000';
	 }
	 
	 var windowHeight = jQuery(window).height();
	 var windowWidth = jQuery(window).width();
	 var newLeft = 0;
	 var newTop = 0;
	 var targetContainer = jQuery('.' + popupclass);
	 var windowPositionTop = jQuery(window).scrollTop();
	 if (targetContainer.width() > windowWidth) {
		targetContainer.css('width', windowWidth - 100);
		newLeft = 50;
	 } else if (targetContainer.width() < windowWidth) {
		newLeft = (windowWidth - targetContainer.width()) / 2;
	 }
	 if (targetContainer.height() > windowHeight) {
		targetContainer.css('height', windowHeight - 100);
		newTop = 50;
	 } else if (targetContainer.height() < windowHeight) {
		newTop = (windowHeight - targetContainer.height()) / 2;
	 }
	 targetContainer.css('left', newLeft);
	 targetContainer.css('top', windowPositionTop + newTop);
	 targetContainer.find('.closePopup').unbind('click').click(function() {
		 hideSlidingPopup(popupclass);
	 });
	 
	 jQuery("."+popupclass).css("z-index", z_index);
	 z_index--;
	 var model = "<div id=\"divBG\" rel='"
	   + popupclass
	   + "' class=\"_slide"
	   + popupclass
	   + "\" style=\"display:block;width:100%;height:100%;background:#000;position:fixed;top:0px;left:0px;z-index:" + z_index + ";opacity: 0.2;filter: alpha(opacity=20); -khtml-opacity: 0.2; -moz-opacity: 0.2; \"" 
	   + "slideClass='popup-slide-up'></div>";
	  
	 if (jQuery("._slide" + popupclass).length == 0) {
		 jQuery('body').append(model);
	 }
	 if(hideOnBody==true){
		 jQuery('._slide' + popupclass).unbind('click').click(function() {
			 hideSlidingPopup(popupclass);
			 slideUpPopUp(jQuery(this).attr("rel"),jQuery(this).attr("slideClass"));
		 });
	 }
	 if(popupclass.indexOf('meAppAnalyticsPopup')>-1)
		 jQuery("."+popupclass).css('display', 'block');
	 
	 if(popupclass.indexOf('meAppPushPopup')>-1 || popupclass.indexOf('meAppAnalyticsPopup')>-1)
		 jQuery("."+popupclass).addClass("pushpopup-slide-up");
	 else
		 jQuery("."+popupclass).addClass("popup-slide-up");
}

function hideSlidingPopup(popupclass){
	var targetContainer = jQuery('.' + popupclass);
	if(popupclass.indexOf('meAppPushPopup')>-1 || popupclass.indexOf('meAppAnalyticsPopup')>-1)
		jQuery("."+popupclass).removeClass("pushpopup-slide-up");
	else
		jQuery("."+popupclass).removeClass("popup-slide-up");
	targetContainer.css('top', '0');
	jQuery("#divBG").remove();
}