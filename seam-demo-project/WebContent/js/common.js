function readCookie(c_name) {
    var i, x, y, ARRcookies = document.cookie.split(";");
    for (i = 0; i < ARRcookies.length; i++) {
        x = ARRcookies[i].substr(0, ARRcookies[i].indexOf("="));
        y = ARRcookies[i].substr(ARRcookies[i].indexOf("=") + 1);
        x = x.replace(/^\s+|\s+$/g, "");
        if (x == c_name) {
            return unescape(y);
        }
    }
}

function escapeHtml(unsafe) {
    return unsafe
         //.replace(/&/g, "&amp;")	// This is commented to avoid recursion
         .replace(/</g, "&lt;")
         .replace(/>/g, "&gt;")
         .replace(/"/g, "&quot;")
         .replace(/'/g, "&#039;");
}
function unescapeHtml(unsafe) {
    return unsafe
         .replace(/&amp;/g, "&")
         .replace(/&lt;/g, "<")
         .replace(/&gt;/g, ">")
         .replace(/&quot;/g, "\"")
         .replace(/&#039;/g, "'");
}

function debug(log_txt) {
	if (window.console != undefined) {
		console.log(log_txt);
	}
}

function getValueOfRadioButton(radioObj) {
	if (!radioObj)
		return "";
	var radioLength = radioObj.length;
	if (radioLength == undefined)
		if (radioObj.checked)
			return radioObj.value;
		else
			return "";
	for ( var i = 0; i < radioLength; i++) {
		if (radioObj[i].checked) {
			return radioObj[i].value;
		}
	}
	return "";
}

function setValueOfRadioButton(radioObj, newValue) {
	if (!radioObj)
		return;
	var radioLength = radioObj.length;
	if (radioLength == undefined) {
		radioObj.checked = (radioObj.value == newValue.toString());
		return;
	}
	for ( var i = 0; i < radioLength; i++) {
		radioObj[i].checked = false;
		if (radioObj[i].value == newValue.toString()) {
			radioObj[i].checked = true;
		}
	}
}
function replaceAll(txt, replace, with_this) {
	return txt.replace(new RegExp(replace, 'g'), with_this);
}

function submitRequest(buttonId) {
	if (document.getElementById(buttonId) == null
			|| document.getElementById(buttonId) == undefined) {
		return;
	}
	if (document.getElementById(buttonId).dispatchEvent) {
		var e = document.createEvent("MouseEvents");
		e.initEvent("click", true, true);
		document.getElementById(buttonId).dispatchEvent(e);
	} else {
		document.getElementById(buttonId).click();
	}
}

function parseURLParams(url) {
	var queryStart = url.indexOf("?") + 1;
	var queryEnd = url.indexOf("#") + 1 || url.length + 1;
	var query = url.slice(queryStart, queryEnd - 1);

	if (query === url || query === "")
		return;

	var params = {};
	var nvPairs = query.replace(/\+/g, " ").split("&");

	for ( var i = 0; i < nvPairs.length; i++) {
		var nv = nvPairs[i].split("=");
		var n = decodeURIComponent(nv[0]);
		var v = decodeURIComponent(nv[1]);
		if (!(n in params)) {
			params[n] = [];
		}
		params[n].push(nv.length === 2 ? v : null);
	}
	return params;
}

function renewCeJqueryUploadImages(arrObj) {
	if(!arrObj){
		arrObj = obj;
	}
	var ctr = 1;
	jQuery(".uploadPic").each(
		function(index, domEle) {
			src = document.getElementById('general:imgorgsrc' + ctr).value;
			if (src != undefined && src != "") {
				src = "../" + src;
				jQuery(this).find('img:first').attr('src', src+'?'+Math.random());
			}
			ctr++;
		}
	);
}

function loadCeJqueryUploadImages(arrObj) {
	if(!arrObj){
		arrObj = obj;
	}
	var ctr = 1;
	var fullRequired = false;
	var fileName="";
	jQuery(".uploadPic").each(
		function(index, domEle) {
			try {
				fullRequired = false;
				fileName="";
				src = document.getElementById('general:imgorgsrc' + ctr).value;
				if (src != undefined && src != "") {
					src = "../" + src;
					jQuery(this).find('img:first').attr('src', src);
				}
				height = jQuery(this).find('img:first').css('height');
				width = jQuery(this).find('img:first').css("width");
				if(jQuery(this).find('img:first').attr("fullRequired") != undefined)
					fullRequired = true;
				if(jQuery(this).find('img:first').attr("fileName") != undefined)
					fileName=jQuery(this).find('img:first').attr("fileName");
				
				arrObj[ctr - 1] = jQuery('#imageUploadHandler' + ctr).initializeFileUpload(getCeJqueryUploadSettings(ctr,height.replace("px", ""), width.replace("px", ""), fullRequired,fileName));
				jQuery('#mainImageUpload' + ctr).unbind("click").bind('click', function (e) {  
					jQuery('#imageUploadHandler' + jQuery(this).attr('id').replace('mainImageUpload','')).focus().trigger('click');
		        });
			} catch(e) {}
			ctr++;
		}
	);	
}

function getCeJqueryUploadSettings(id, height, width, fullImage,fileName) {
	if (fullImage == undefined)
		fullImage = false;
	var settings = {
		upload_url : "../upload",
		maxNumberOfFiles : 10,
		maxFileSize : 10000000, //10MB
		fileSelection : /(\.|\/)(gif|jpe?g|png|bmp)$/i,  //Select File Extensions
		fileExtensions : 'gif, jpg, jpeg, png, bmp',
		fileType : 'Image',
		progressBarId : 'fsUploadProgress' + id,
		postParams : {
			"height" : height,
			"width" : width,
			"fullImage" : fullImage,
			"customFileName" :fileName
		},
		pathHolderId : 'general:imgorgsrc' + id			
		//a4jLinkId : "careerForm:image1link"
	};
	return settings;
}

function renewSwfUploadImages(arrObj) {
	if(!arrObj){
		arrObj = obj;
	}
	var ctr = 1;
	jQuery(".uploadPic").each(
		function(index, domEle) {
			src = "../"+ document.getElementById('imgsrc' + ctr).value;
			document.getElementById('general:imgorgsrc' + ctr).value = document.getElementById('imgsrc' + ctr).value;
			// obj[ctr-1].settings.button_image_url
			// ="/MCT/pages/"+src;
			try {
				arrObj[ctr - 1].setButtonImageURL(src);
			} catch (ex) {}
			
			jQuery(this).find('div:first').css('background-image', 'url(' + src + ')');
			ctr++;
		}
	);
}

function loadSwfUploadImages(arrObj) {
	if(!arrObj){
		arrObj = obj;
	}
	var ctr = 1;
	var fullRequired = false;
	var fileName="";
	jQuery(".uploadPic").each(
		function(index, domEle) {
			try {
				fullRequired = false;
				fileName="";
				src = document
						.getElementById('general:imgorgsrc' + ctr).value;
				if (src == "") {
					src = document.getElementById('imgsrc' + ctr).value;
				} else {
					document.getElementById('imgsrc' + ctr).value = src;
				}
				src = "../" + src;
				height = jQuery(this).find('img:first').attr('height');
				width = jQuery(this).find('img:first').attr("width");
				if(jQuery(this).find('img:first').attr("fullRequired") != undefined)
					fullRequired = true;
				
				if(jQuery(this).find('img:first').attr("fileName") != undefined)
					fileName=jQuery(this).find('img:first').attr("fileName");
				jQuery(this).find('img:first').attr('src', src);
				jQuery(this).find('div:first').css('background-image', 'url(' + src + ')');
				arrObj[ctr - 1] = new SWFUpload(getSwfSettings(ctr,
						'imgsrc', height.replace("px", ""), width
								.replace("px", ""), fullRequired,fileName));
			} catch(e) {}
			ctr++;
		}
	);	
}

function renewSwfUploadImagesWithForm(arrObj) {
	if(!arrObj){
		arrObj = obj;
	}
	var ctr = 0;
	var oldFormName = "";
	jQuery(".uploadPic").each(
	function(index, domEle) {
		var formName = jQuery(this).closest("form").attr("id");
		if (ctr == 0) {
			oldFormName = formName;
			ctr++;
		} else if (formName == oldFormName) {
			ctr++;
		} else if (formName != oldFormName) {
			oldFormName = formName;
			;
			ctr = 1;
		}
		src = "../" + document.getElementById('imgsrc' + ctr).value;
		var formValue = "" + formName + ":imgorgsrc" + ctr;
		document.getElementById(formValue).value = document
				.getElementById('imgsrc' + ctr).value;
		// arrObj[ctr-1].settings.button_image_url ="/MCT/pages/"+src;
		try {
			arrObj[ctr - 1].setButtonImageURL(src);
		} catch (ex) {

		}
		jQuery(this).find('div:first').css('background-image',
				'url(' + src + ')');
	});
}

function loadSwfUploadImagesWithForm(formName,arrObj) {
	if(!arrObj){
		arrObj = obj;
	}
	var ctr = 0;
	var oldFormName = "";
	var fileName="";
	var fullRequired = false;
	jQuery(".uploadPic")
			.each(
					function(index, domEle) {
						fullRequired = false;
						fileName="";
						var formName = jQuery(this).closest("form").attr("id");
						if (ctr == 0) {
							oldFormName = formName;
							ctr++;
						} else if (formName == oldFormName) {
							ctr++;
						} else if (formName != oldFormName) {
							oldFormName = formName;
							;
							ctr = 1;
						}
						var formValue = "" + formName + ":imgorgsrc" + ctr;
						src = document.getElementById(formValue).value;
						if (src == "") {
							src = document.getElementById('imgsrc' + ctr).value;
						} else {
							document.getElementById('imgsrc' + ctr).value = src;
						}
						src = "../" + src;
						// alert(src);
						height = jQuery(this).find('img:first').attr('height');
						width = jQuery(this).find('img:first').attr("width");
						if (jQuery(this).find('img:first')
								.attr("fullRequired") != undefined)
							fullRequired = true;
						
						if(jQuery(this).find('img:first').attr("fileName") != undefined)
							fileName=jQuery(this).find('img:first').attr("fileName");

						jQuery(this).find('img:first').attr('src', src);
						jQuery(this).find('div:first').css('background-image',
								'url(' + src + ')');
						arrObj[ctr - 1] = new SWFUpload(getSwfSettings(ctr,
								'imgsrc', height.replace("px", ""), width
										.replace("px", ""), fullRequired,fileName));
					});
}

function getSwfSettings(id, imagePathHolder, height, width, fullImage,fileName) {
	if (fullImage == undefined)
		fullImage = false;
	var imgPath = "../" + document.getElementById(imagePathHolder + id).value;
	var settings = {
		flash_url : "../swf/swfupload.swf",
		upload_url : "../upload",
		post_params : {
			"height" : height,
			"width" : width,
			"fullImage" : fullImage,
			"customFileName" :fileName
		},
		file_size_limit : "100 MB",
		file_types : '*.jpg;*.png;*.jpeg;*.bmp;*.gif',
		file_types_description : "All Files",
		file_upload_limit : 100,
		file_queue_limit : 0,

		debug : false,
		// Button settings
		button_action : SWFUpload.BUTTON_ACTION.SELECT_FILE,
		button_cursor : SWFUpload.CURSOR.HAND,
		button_height : height,
		button_image_url : imgPath,
		button_placeholder_id : 'mainImageUpload' + id,
		button_width : width,
		button_window_mode : SWFUpload.WINDOW_MODE.TRANSPARENT,

		// The event handler functions are defined in
		// templateImagehandlers.js
		file_dialog_complete_handler : customTemplateFileDialogComplete,
		upload_start_handler : customTemplateUploadStart,
		upload_progress_handler : customTemplateProgressEventHandler,
		upload_error_handler : customTemplateUploadError,
		upload_success_handler : customTemplateSuccessEventHandler,
		upload_complete_handler : customTemplateUploadComplete,
		queue_complete_handler : customTemplateQueueComplete,// Queue
		// plugin
		// event

		custom_settings : {
			messageHolderId : "fsLoaderProgress" + id,
			pathHolderId : imagePathHolder + id,
			fileType : "Image",
			loaderImage : "../images/loadingsmall.gif"
		}
	};
	return settings;
}

function callGeneralDelete(varid, name) {
	invokeCustomConfirmationPopup("Do you really want to delete " + name + "?",
			callBackGeneralDelete, varid);
	//return confirmed;
}

function callConfirmPopUp(varid, name,cancelButton,okbutton){
	invokeConfirmationPopupNew( name ,
			callBackGeneralDelete, varid,cancelButton,okbutton);
	return confirmed;
}


//This method is used to show confirmation
function invokeConfirmationPopupNew(message, _callBackMethod, varId,cancelButton,okbutton) {
	openConfirmPopUpNew(message, _callBackMethod, varId,cancelButton,okbutton);
	
}

//This method is used to show confirmation
function invokeCustomConfirmationPopup(message, _callBackMethod, varId) {
	openCustomConfirmationWindow(message, _callBackMethod, varId);
}

function callBackGeneralDelete(confirmed, varid) {
	revokeCustomConfirmationPopup();
	if (confirmed && varid != "") {
		invokeWaitingGIF();
		submitRequest(varid);
	}
}

function customeValidateSearchField(searchField) {
	var iChars = "!#$%^&*()+=-[]\\\';/{}|\":<>?";
	for ( var i = 0; i < searchField.length; i++) {
		if (iChars.indexOf(searchField.charAt(i)) != -1) {
			return false;
		}
	}
	return true;
}

function customValidateEmail(email) {
	var ck_email = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
	return ck_email.test(email);
}

function customValidateWebsite(url) {
	var v = new RegExp();
	// v.compile("^[A-Za-z]+://[A-Za-z0-9-_]+\\.[A-Za-z0-9-_%&\?\/.=]+$");
	v.compile("^[A-Za-z0-9-_]+\\.[A-Za-z0-9-_%&\?\/.=]+$");
	if (!v.test(url)) {
		return false;
	}
	return true;
}

function customValidatePhone(field) {
	var valid = "0123456789-+";
	for ( var i = 0; i < field.length; i++) {
		var temp = "" + field.substring(i, i + 1);
		if (valid.indexOf(temp) == "-1") {
			// alert("Invalid characters in your zip code. Please try again.");
			return false;
		}
	}
	return true;
}

function customValidateZIP(field) {
	var valid = "0123456789-";
	var hyphencount = 0;

	// if (field.length!=5 && field.length!=10)
	// {
	// //alert("Please enter your 5 digit or 5 digit+4 zip code.");
	// return false;
	// }
	for ( var i = 0; i < field.length; i++) {
		var temp = "" + field.substring(i, i + 1);
		if (temp == "-")
			hyphencount++;
		if (valid.indexOf(temp) == "-1") {
			// alert("Invalid characters in your zip code. Please try again.");
			return false;
		}
		if ((hyphencount > 1)
				|| ((field.length == 10) && "" + field.charAt(5) != "-")) {
			// alert("The hyphen character should be used with a properly
			// formatted 5 digit+four zip code, like '12345-6789'. Please try
			// again.");
			return false;
		}
	}
	return true;
}

/** methods used for deleting * */
function callDelete(actionComponentId, type) {
	invokeCustomConfirmationPopup("Do you really want to delete this " + type
			+ " ?", callBackDelete, actionComponentId);
}
function callBackDelete(confirmed, actionComponentId) {
	revokeCustomConfirmationPopup();
	if (confirmed) {
		submitRequest(actionComponentId);
		invokeWaitingGIF();
	}
}
/** end of method for deleting * */
// This method is used to hide panel
function closePanel(panelName) {
	enableHotKeySearch();
	Richfaces.hideModalPanel(panelName);
}

// This method is used to show alert
function invokeCustomAlertPopup(message, _callBackMethod) {
	openCustomAlertWindow(message, _callBackMethod);
	Richfaces.showModalPanel('alertPanel');
}

// This method is used to revoke alert
function revokeCustomAlertPopup() {
	Richfaces.hideModalPanel('alertPanel');
}

function checkIfElementEmpty(elementId, val) {
	if (val == undefined)
		val = "";
	return (document.getElementById(elementId).value == null || document
			.getElementById(elementId).value == val);
}

function alertCustomized(messageText) {
	invokeCustomAlertPopup(messageText, revokeCustomAlertPopup);
}


// This method is used to revoke confirmation
function revokeCustomConfirmationPopup() {
	hideModel('confermPopUp');
}

// This method is used to show textArea popup
function invokeCustomTextAreaPopup(oldValue, _callBackMethod, varId) {
	openCustomTextAreaWindow(oldValue, _callBackMethod, varId);
	Richfaces.showModalPanel('customTextAreaPanel');
}

// This method is used to revoke textAreaPopup
function revokeCustomTextAreaPopup() {
	Richfaces.hideModalPanel('customTextAreaPanel');
}

// This method is used to show textEditor popup
function invokeCustomtextEditorPopup(oldValue, _callBackMethod, varId) {
	openCustomtextEditorPopUpWindow(oldValue, _callBackMethod, varId);
	// Richfaces.showModalPanel('customtextEditorPanel');
}

// This method is used to revoke textEditorPopup
function revokeCustomtextEditorPopup() {
	hideModel('editorPopUp');
	// Richfaces.hideModalPanel('customtextEditorPanel');
}

// This method is used to show tinyMCETextEditor popup
function invokeCustomTinyMceTextEditorPopup(oldValue, _callBackMethod, varId) {
	openCustomTinyMceTextEditorPopUpWindow(oldValue, _callBackMethod, varId);
}

// This method is used to revoke tinyMCETextEditor
function revokeCustomTinyMceTextEditorPopup() {
	hideModel('editorPopUp');
}

// This method is used to show FileUpload popup
function invokeCustomFileUploadPopup(message, _callBackMethod, varId, fileType,
		fileExtension, imagePath) {
	Richfaces.showModalPanel('customFileUploadPanel');
	openCustomFileUploadWindow(message, _callBackMethod, varId, fileType,
			fileExtension, imagePath);
}

// This method is used to revoke textAreaPopup
function revokeCustomFileUploadPopup() {
	Richfaces.hideModalPanel('customFileUploadPanel');
}

function invokeAlert(popUpId, Msg, spanId) {
	var alertMsgHolder = document.getElementById(spanId);
	alertMsgHolder.innerHTML = Msg;
	showModelPopup(popUpId);

}

// This method is used to hide panel
function closePanelModify(panelName) {
	enableHotKeyModify();
	Richfaces.hideModalPanel(panelName);
}

// This method is used to disable hotkey on search screen
function disablehotKey() {
	new Richfaces.hotKey('form_recherche:mykey', 'return', '', {
		timing : 'immediate'
	}, function(event) {
	});
}

// This method is used to enable hot key on search screen
function enableHotKeySearch() {
	new Richfaces.hotKey('form_recherche:mykey', 'return', '', {
		timing : 'immediate'
	}, function(event) {
		enterEvent();
	});
}

// This method is used to enable hot key on create
function enableHotKeyModify() {
	new Richfaces.hotKey('form_recherche:mykey', 'return', '', {
		timing : 'immediate'
	}, function(event) {
		modifyEnterEvent();
	});
}

// This method is used to show waiting gif and change cursor pointer
function invokeWaitingGIF(msg) {
//	document.body.style.cursor = 'wait';
//	Richfaces.showModalPanel('loadingPanel');
	if(msg)
		invokeWaitingGIfForSave(msg);
	else
		invokeWaitingGIfForSave("Loading...");
}
// This method is used to revoke waiting gif
function revokeWaitingGIF() {
//	Richfaces.hideModalPanel('loadingPanel');
//	document.body.style.cursor = 'default';
	revokeWaitingGIfForSave();
}


// This method is used to show waiting gif and change cursor pointer
function invokeWaitingScreen() {
//	document.body.style.cursor = 'wait';
//	Richfaces.showModalPanel('loadingPanel');
	invokeWaitingGIfForSave("Loading...");
}
// This method is used to revoke waiting gif
function revokeWaitingScreen() {
//	Richfaces.hideModalPanel('loadingPanel');
//	document.body.style.cursor = 'default';
	revokeWaitingGIfForSave();
}
function changeSideMenuColor(menuID) {
	/*
	 * document.getElementById(menuID).style.backgroundColor = "#559CC8";
	 * jQuery("#"+menuID).find('a::eq(0)').css('color', '#ffffff');
	 * jQuery("#"+menuID).find('img::eq(0)').attr('src',
	 * '../images/'+menuID+'IconWhite.png');
	 */
}

function applyWaterMark(defaultText, id) {
	if (document.getElementById(id) == undefined)
		return;
	var txt = document.getElementById(id).value;
	if (txt == "" || txt == defaultText) {
		document.getElementById(id).value = defaultText;
	}
	// jQuery("#"+id).css("color","grey");
}

function removeWaterMark(defaultText, id) {
	if (document.getElementById(id) == undefined)
		return;
	var txt = document.getElementById(id).value;
	if (txt == defaultText) {
		document.getElementById(id).value = "";
	}
	// jQuery("#"+id).css("color","#000000");
}

function openSmallFilemanager(type, left, top, ele, id, issueOrTitletype,
		issueOrTitleid) {
	if (issueOrTitletype.indexOf("issue") >= 0) {
		showtypeFilemanger(type, left, top, ele, id, issueOrTitleid);
	}
	if (issueOrTitletype.indexOf("title") >= 0) {
		showtypeFilemangerByTitle(type, left, top, ele, id, issueOrTitleid);
	}
}

function showErrorSuccessMessage(val1, val2) {
	document.getElementById('errorClass').innerHTML = "";
	document.getElementById('sucessClass').innerHTML = "";
	var error = document.getElementById(val1).value;
	if (error == "")
		return;
	var sucess = document.getElementById(val2).value;
	if (sucess.indexOf("false") != -1) {
		showMsgDiv();
		document.getElementById('errorClass').innerHTML = error;
		jQuery('.msgDiv').delay(3000).hide("slide", {
			direction : "up"
		}, 1500);
		return;
	}
	if (sucess.indexOf("true") != -1) {
		showMsgDiv();
		document.getElementById('sucessClass').innerHTML = error;
		jQuery('.msgDiv').delay(3000).hide("slide", {
			direction : "up"
		}, 1500);
		return;
	}

	function showMsgDiv() {
		var msgdivWidth = (jQuery('.msgDiv').width()) / 2;
		var newWidth = ((jQuery(window).width()) / 2) - msgdivWidth;
		jQuery('.msgDiv').css('left', newWidth + 'px');
		jQuery('.msgDiv').show("slide", {
			direction : "up"
		}, 1500);
	}
	function hideMsgDiv() {
		document.getElementById('errorClass').innerHTML = "";
		document.getElementById('sucessClass').innerHTML = "";
		jQuery('.msgDiv').hide("slide", {
			direction : "up"
		}, 1500);
	}

}

function scaleSize(maxW, maxH, currW, currH) {

	var ratio = currH / currW;

	if (currW >= maxW && ratio <= 1) {
		currW = maxW;
		currH = currW * ratio;
	} else if (currH >= maxH) {
		currH = maxH;
		currW = currH / ratio;
	}

	return [ currW, currH ];
}

function ajaxCall(url){
	var xmlHttp=GetXmlHttpObject();
	 if (xmlHttp==null) {
		 alert ("Your browser does not support AJAX!");
		 return;
	 } 
	 xmlHttp.onreadystatechange=stateChangedGenric;
	 xmlHttp.open("GET",url,true);
	 xmlHttp.send(null);
}

function GetXmlHttpObject()
{
 var xmlHttp=null;
 try
 {
   // Firefox, Opera 8.0+, Safari
   xmlHttp=new XMLHttpRequest();
 }
 catch (e)
 {
   // Internet Explorer
   try
   {
     xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
   }
   catch (e)
   {
     xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
   }
 }
 return xmlHttp;
}

function stateChangedGenric() 
{ 
 if (xmlHttp.readyState==4)
 { 
	return true;
 }
}