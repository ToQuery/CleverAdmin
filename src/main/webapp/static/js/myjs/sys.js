var locat = (window.location+'').split('/'); 
$(function(){if('head'== locat[3]){locat =  locat[0]+'//'+locat[2];}else{locat =  locat[0]+'//'+locat[2]+'/'+locat[3];};});
$(top.hangge());

//=======================================配置NO3
function save3(){
	if($("#Token").val()==""){
		$("#Token").tips({
			side:3,
            msg:'Token(令牌)',
            bg:'#AE81FF',
            time:3
        });
		$("#Token").focus();
		return false;
	}
	$("#Form3").submit();
	$("#zhongxin").hide();
	$("#zhongxin2").show();
}

//设置站内信声音提示类型
function setFHsmsSoundType(type){
	$("#FHsmsSound").val(type);
	if('0' != type){
		$("#fhsmsobjsys").html('<audio style="display: none;" id="fhsmstsy" src="static/sound/'+type+'.mp3" autoplay controls></audio>');
	}
}


//=======================================配置NO2
//清除空格
String.prototype.trim=function(){
     return this.replace(/(^\s*)|(\s*$)/g,'');
};

//====================上传二维码=================
$(document).ready(function(){
	$("#uploadify1").uploadify({
		'buttonImg'	: 	locat+"/plugins/uploadify/uploadp.png",
		'uploader'	:	locat+"/plugins/uploadify/uploadify.swf",
		'script'    :	locat+"/plugins/uploadify/uploadFile.jsp;jsessionid="+jsessionid,
		'cancelImg' :	locat+"/plugins/uploadify/cancel.png",
		'folder'	:	locat+"/uploadFiles/uploadImgs",//上传文件存放的路径,请保持与uploadFile.jsp中PATH的值相同
		'queueId'	:	"fileQueue",
		'queueSizeLimit'	:	1,//限制上传文件的数量
		//'fileExt'	:	"*.rar,*.zip",
		//'fileDesc'	:	"RAR *.rar",//限制文件类型
		'fileExt'     : '*.jpg;*.gif;*.png',
		'fileDesc'    : 'Please choose(.JPG, .GIF, .PNG)',
		'auto'		:	false,
		'multi'		:	true,//是否允许多文件上传
		'simUploadLimit':	1,//同时运行上传的进程数量
		'buttonText':	"files",
		'scriptData':	{'uploadPath':'/uploadFiles/uploadImgs/','fileNmae':'watermark.png'},//这个参数用于传递用户自己的参数，此时'method' 必须设置为GET, 后台可以用request.getParameter('name')获取名字的值
		'method'	:	"GET",
		'onComplete':function(event,queueId,fileObj,response,data){
			$("#imgUrl").val(response.trim());
		},
		'onAllComplete' : function(event,data) {
			$("#Form2").submit();
			$("#zhongxin").hide();
			$("#zhongxin2").show();
    	},
    	'onSelect' : function(event, queueId, fileObj){
    		$("#hasTp1").val("ok");
    	}
	});
			
});
//====================上传图片=================

function save2(){
	if($("#fcontent").val()==""){
		$("#fcontent").tips({
			side:3,
            msg:'输入水印文字内容',
            bg:'#AE81FF',
            time:3
        });
		$("#fcontent").focus();
		return false;
	}
	if($("#fontSize").val()==""){
		$("#fontSize").tips({
			side:3,
            msg:'输入字号',
            bg:'#AE81FF',
            time:3
        });
		$("#fontSize").focus();
		return false;
	}
	if($("#fontX").val()==""){
		$("#fontX").tips({
			side:3,
            msg:'输入X坐标',
            bg:'#AE81FF',
            time:3
        });
		$("#fontX").focus();
		return false;
	}
	if($("#fontY").val()==""){
		$("#fontY").tips({
			side:3,
            msg:'输入Y坐标',
            bg:'#AE81FF',
            time:3
        });
		$("#fontY").focus();
		return false;
	}
	if($("#imgX").val()==""){
		$("#imgX").tips({
			side:3,
            msg:'输入X坐标',
            bg:'#AE81FF',
            time:3
        });
		$("#imgX").focus();
		return false;
	}
	if($("#imgY").val()==""){
		$("#imgY").tips({
			side:3,
            msg:'输入Y坐标',
            bg:'#AE81FF',
            time:3
        });
		$("#imgY").focus();
		return false;
	}
	if("ok" == $("#hasTp1").val()){
		$('#uploadify1').uploadifyUpload();
	}else{
		$("#Form2").submit();
		$("#zhongxin").hide();
		$("#zhongxin2").show();
	}
}


function openThis1(){
	if(document.getElementsByName('fcheckbox')[0].checked){
		$("#isCheck1").val('yes');
	}else{
		$("#isCheck1").val('no');
	}
}
function openThis2(){
	if(document.getElementsByName('fcheckbox')[1].checked){
		$("#isCheck2").val('yes');
	}else{
		$("#isCheck2").val('no');
	}
}


//=======================================配置NO1
//保存
function save(){
	
	if($("#YSYNAME").val()==""){
		$("#YSYNAME").tips({
			side:3,
            msg:'输入系统名称',
            bg:'#AE81FF',
            time:3
        });
		$("#YSYNAME").focus();
		return false;
	}

	if($("#COUNTPAGE").val()==""){
		$("#COUNTPAGE").tips({
			side:3,
            msg:'输入每页条数',
            bg:'#AE81FF',
            time:3
        });
		$("#COUNTPAGE").focus();
		return false;
	}
	
	if($("#SMTP").val()==""){
		$("#SMTP").tips({
			side:1,
            msg:'输入SMTP',
            bg:'#AE81FF',
            time:3
        });
		$("#SMTP").focus();
		return false;
	}
	
	if($("#PORT").val()==""){
		$("#PORT").tips({
			side:1,
            msg:'输入端口',
            bg:'#AE81FF',
            time:3
        });
		$("#PORT").focus();
		return false;
	}
	
	if($("#EMAIL").val()==""){
		
		$("#EMAIL").tips({
			side:3,
            msg:'输入邮箱',
            bg:'#AE81FF',
            time:3
        });
		$("#EMAIL").focus();
		return false;
	}else if(!ismail($("#EMAIL").val())){
		$("#EMAIL").tips({
			side:3,
            msg:'邮箱格式不正确',
            bg:'#AE81FF',
            time:3
        });
		$("#EMAIL").focus();
		return false;
	}

	if($("#PAW").val()==""){
		$("#PAW").tips({
			side:1,
            msg:'输入密码',
            bg:'#AE81FF',
            time:3
        });
		$("#PAW").focus();
		return false;
	}
	
	$("#Form").submit();
	$("#zhongxin").hide();
	$("#zhongxin2").show();
}

function ismail(mail){
	return(new RegExp(/^(?:[a-zA-Z0-9]+[_\-\+\.]?)*[a-zA-Z0-9]+@(?:([a-zA-Z0-9]+[_\-]?)*[a-zA-Z0-9]+\.)+([a-zA-Z]{2,})+$/).test(mail));
	}