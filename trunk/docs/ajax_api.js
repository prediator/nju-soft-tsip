/**
 * 客户端与服务器交互api文件，使用前请注意：
 * 1 客户端发送的都是post请求，其他请求一律不会接收
 * 2 客户端发送的post请求head参数：
 *   Host	localhost:8080
 *   User-Agent	Mozilla/5.0 (X11; U; Linux i686; zh-CN; rv:1.9.2.15) Gecko/20110303 Ubuntu/10.10 (maverick) Firefox/3.6.15
 *   Accept	application/json, text/javascript, *（/）*
 *   Accept-Language	en-us,zh-cn;q=0.7,zh;q=0.3
 *   Accept-Encoding	gzip,deflate
 *   Accept-Charset	GB2312,utf-8;q=0.7,*;q=0.7
 *   Keep-Alive	115
 *   Connection	keep-alive
 *   Content-Type	application/json; charset=UTF-8
 *   X-Requested-With	XMLHttpRequest
 *   Referer	http://localhost:8080/ajaxapi
 *   Content-Length	54
 *   Cookie	JSESSIONID=1739b0tl55wp7cop8o1747e4l
 *   Pragma	no-cache
 *   Cache-Control	no-cache
 * 在这里值得注意的是 “Accept	application/json, text/javascript, *（/）*“这一行，这一为着服务器直接收json类型的application
 * 3 服务器的错误返回类型其实已经有http协议制定号，所以我们并不会从新制定，可以从返回的http的状态中得知错误类型（如404 Not Found）
 * 当然服务器还是可能会返回一些错误标志，如{error:"用户名不能为空"},但并不能保证所有错误都会有自定义提示
 * 400 Bad request（错误请求）
　　401.1 Logon failed（登录失败）
　　401.2 Logon failed due to server configuration（由于服务器配置，登录失败）
　　401.3 Unauthorized due to ACL on resource（由于资源上的 ACL，未授权）
　　401.4 Authorization failed by filter（由于筛选器，授权失败）
　　401.5 Authorization failed by ISAPI/CGI application （由于 ISAPI/CGI 应用程序，授权失败）
　　403.1 Execute access forbidden（执行访问被禁止）
　　403.2 Read access forbidden（读取访问被禁止）
　　403.3 Write access forbidden（写入访问被禁止）
　　403.4 SSL required（要求 SSL ）
　　403.5 SSL 128 required（要求 SSL 128）
　　403.6 IP address rejected （IP 地址被拒绝）
　　403.7 Client certificate required（要求客户证书）
　　403.8 Site access denied（站点访问被拒绝）
　　403.9 Too many users（用户太多）
　　403.10 Invalid configuration（无效的配置）
　　403.11 Password change（密码更改）
　　403.12 Mapper denied access（映射程序拒绝访问）
　　403.13 Client certificate revoked（客户证书被取消）
　　403.14 Directory listing denied（目录列表被拒绝）
　　403.15 Client Access Licenses exceeded（超出客户访问许可证）
　　403.16 Client certificate untrusted or invalid（客户证书不受信任或无效）
　　403.17 Client certificate has expired or is not yet valid（客户证书已过期或无效）
　　404 Not found（没有找到）
　　404.1 Site not found（站点没有找到）
　　405 Method not allowed（不允许使用该方法）
　　406 Not acceptable（不接受）
　　407 Proxy authentication required（要求代理身份验证）
　　412 Precondition Failed（前提条件不正确）
　　414 Request-URL too long（请求的 URL 太长）
　　500 Internal server error（内部服务器错误）
　　500.12 Application restarting（应用程序重新启动）
　　500.13 Server too busy（服务器太忙）
　　500.15 Requests for Global.asa not allowed（不允许请求 Global.asa）
　　500-100.asp ASP 错误
　　501 Not implemented（没有实施）
　　502 Bad gateway（错误网关）

   更多请参照：http://baike.baidu.com/view/3734067.htm#sub3734067
 * 
 * 4 凡是客户端发出的请求都会以client开头，否则将遭到过滤
 * 5 服务器基本地址 http://xxx.xx.xx.xx/ (加上client头之后就是 http://xxx.xx.xx.xx/client)
 * 6 该api随时更新，请以网络版为准，网络版所在地址：
 * http://code.google.com/p/nju-soft-tsip/source/browse/trunk/docs/ajax_api.js
 * 7 一旦登录，每次请求都要使用sessionid，使用方法是：在url中增加;jsessionid=1s8yil3wcyek81vdp7ggpuaw3q，也就是
 *   http://xxx.xx.xx.xx/xxx/xxx/xxxx;jsessionid=1s8yil3wcyek81vdp7ggpuaw3q
 * 附录 更新内容如下（有更新将会记在这里，方便索引和查找）
 * -------------------------------------------
 * update6：
 * 整个文件格式定义 和 login api的添加
 * 曾加发微博的api
 * 注意中 增加第7条
 * 增加 聊天接口
 * 聊天api进行较多改变，对写完的api补上了url
 * 为可评论的东西增加评论功能，包括状态，日志，相册
 * -------------------------------------------
 * update7:
 * 增加获取文章api
 * 获取用户列表api
 * ------------------------------------------
 * @author leung jianjun
 */
 
//==========================================================
//======基本操作=============================================
 /**
  * 登录
  * 注意：角色列表：
  *      student,teacher,admin,leader,counsellor
  * url:client/user/login
  * @type 
  */
 var login = {
 	loginName:"ljj09",
 	password:"123",
 	loginPlace:"xianlin"
 }
 
 var result = {
 	status:"true",
 	sessionId:"dsafdsafdsafdsafd",
 	realName:"ljj",
 	role:"student"
 	
 }
 
 var error = {
 	status:"false",
 	error :"参数错误"
 }
 /**
  * 退出
  * url:/client/user/exit
  * @type 
  */
 var exit = {
 }
 
 var result = {//有点没必要
 	status:"true"
 }
 
 var result = {
 	status:"false"
 }
 //=========================================================
 //==============评论操作====================================
 /**
  * 注意：1.评论操作已内嵌在各个能评论的的操作中，具体的api大同小异，其实就是添加评论，删除评论，显示评论，回复评论（已去）
  * 
  * @type 
  */
 //==========================================================
 //============状态操作=======================================
 /**
  * 发布状态（微博）
  * 注意：微博的发布分成两种，1是原创的状态，2是转发的状态。转发的状态中，内容是对状态的评论
  *       id值很重要，是每一条信息的唯一标志，尽可能把id保存起来，也就是放在前台，并把id隐藏
  * 例子：  mblog1: I hate history class.
  *        mblog2 follow mblog1 :  the same to me. @ljj I hate history class.
  *        mblog3 follow mblog2 :  I don't think so @qc the same to me @ljj I hate history class
  *        则有：mblog1 content:I hate history class
  *             mblog2 content:the same to me.
  *                    flwMBlogId:mblog1 id
  *             mblog3 content:I don't think so
  *                    flwMBlogId:mblog2 id
  * url:client/mblog/create
  * @type 
  */
 var add_mblog = {
 	content:"what a good mblog"
 }
 
 var add_mblog = {
 	content:"a comment to the mblog",
 	flwMBlogId:34331232//原来的微博id
 }
 var result = {
 	status:"true",
 	id:123//返回的id
 }
 
 /**
  * 删除状态
  * url:client/mblog/delete
  * @type 
  */
 var delete_mblog = {
 	id:34331232
 }
 
 var result_delete_mblog = {
 	status:"true"
 } 
 
 var result_delete_mblog = {
 	status:"false"
 }
 
 /**
  * 获取某个状态的具体信息
  * url:/client/mblog/getMblogDatail
  * @type 
  */
 var ask_get_mblog = {
 	id:123
 }
 
 var result_get_mblog = {
 	status:"false",
 	error:"参数错误"
 }
 
 /**
  * 没有comment时显示comments[]
  * @type 
  */
 var result_get_mblog = {
 		 status:"true",
 		 id:1324, 
 		 createDate:"yyyy-mm-dd HH:mm:ss",         //原创状态
 		 publisher:{name:"ljj",
 		            id:"12323"},
 		 content:"a comment to the mblog", 
 		 flwMBlog:{id:1323,
 		 		   createDate:"yyyy-mm-dd HH:mm:ss",         //转发的微博，并不会显示原来的微博的评论
 		 		   publisher:{name:"ljj",
 		            		  id:12323},
 		 		   content:"what a good mblog"},          
 		 comments:[{id:2326,
 		            content:"what a good comment!",    //没有子评论的评论
 		            createDate:"yyyy-mm-dd HH:mm:ss",
 		            author:{name:"lhh",
 		                    id:1325}},
 		            {id:2327,
 		             content:"what a great comment!",
 		             createDate:"yyyy-mm-dd HH:mm:ss",
 		             author:{name:"jzh",
 		                    id:1326}},
 		            {id:2328,
 		             content:"what a great comment!",  //有子评论的评论
 		             createDate:"yyyy-mm-dd HH:mm:ss",
 		             author:{name:"jzh",
 		                    id:1326}
 		             
 		             /*cmntChilds:[{id:2329,
 		                           content:"a comment for the comment!",
 		                           createDate:"yyyy-mm-dd HH:mm:ss",
 		                           author:{name:"jzh",
 		                                   id:1326}},
 		                           {id:2330,
 		                           content:"another comment for the comment",
 		                           createDate:"yyyy-mm-dd HH:mm:ss",
 		                           author:{name:"jzh",
 		                                   id:1326}}
 		                           ...more comments*///子评论功能以去掉
 		            }]
 }
 
 /**
  * 获取所有人的状态
  * 注意：一个状态可能被转发了n多次，所以mblog下会有flwMBlog，flwMBlog下还可能有flwBlog，
  *      然后组合成一个长状态，所以要判别直到某个flwMBlog没有flwMBlog为止
  *      what a hot mblog @lhh I don't think so @qc the same to me @ljj I hate history class
  *     一个状态的会有一个评论列表，而每一个评论下都会有子评论列表，每个子评论还会有子评论...
  *     由于一次获取所有状态会等待太久，所以要分页获取，每页10条
  *  url:/client/mblog/getAll
  * @type 
  */
 var ask_get_mblogs = {
 	//page:1页数从1开始,此功能已去掉
 }
 
 var get_mblogs = {
 	mblogs:[
 		{id:1323,                           //id的作用是进入具体的微博查看详细的信息
 		 createDate:"yyyy-mm-dd HH:mm:ss",         //原创状态
 		 publisher:{name:"ljj",
 		            id:"12323"},
 		 content:"what a good mblog", 
 		 comments:[{id:2324,
 		            content:"what a good comment!",    //没有子评论的评论
 		            createDate:"yyyy-mm-dd HH:mm:ss",
 		            author:{name:"lhh",
 		                    id:1323}},
 		            {id:2325,
 		             content:"what a great comment!",
 		             createDate:"yyyy-mm-dd HH:mm:ss",
 		             author:{name:"jzh",
 		                     id:1324}}
 		            //........more comments
 		           ]},
 		 /////////////////////////////////////////////////
 		{id:1324, 
 		 createDate:"yyyy-mm-dd HH:mm:ss",         //原创状态
 		 publisher:{name:"ljj",
 		            id:"12323"},
 		 content:"a comment to the mblog", 
 		 flwMBlog:{id:1323,
 		 		   createDate:"yyyy-mm-dd HH:mm:ss",         //转发的微博，并不会显示原来的微博的评论
 		 		   publisher:{name:"ljj",
 		            		  id:"12323"},
 		 		   content:"what a good mblog"},          
 		 comments:[{id:2326,
 		            content:"what a good comment!",    //没有子评论的评论
 		            createDate:"yyyy-mm-dd HH:mm:ss",
 		            author:{name:"lhh",
 		                    id:1325}},
 		            {id:2327,
 		             content:"what a great comment!",
 		             createDate:"yyyy-mm-dd HH:mm:ss",
 		             author:{name:"jzh",
 		                    id:1326}},
 		            {id:2328,
 		             content:"what a great comment!",  //有子评论的评论
 		             createDate:"yyyy-mm-dd HH:mm:ss",
 		             author:{name:"jzh",
 		                    id:1326},
 		             cmntChilds:[{id:2329,
 		                           content:"a comment for the comment!",
 		                           createDate:"yyyy-mm-dd HH:mm:ss",
 		                           author:{name:"jzh",
 		                                   id:1326}},
 		                           {id:2330,
 		                           content:"another comment for the comment",
 		                           createDate:"yyyy-mm-dd HH:mm:ss",
 		                           author:{name:"jzh",
 		                                   id:1326}}
 		                           //...more comments
 		                           ]}
 		            //........
 		           ]}
 		  //////////////////////////////////////////////////////////
 		//more 状态
 	]
 }
 
 /**
  * 
  * url:/client/mblog/delete
  * @type 
  */
 var delete_mblog = {
 	id:123
 }
 
 var result_delete_mblog = {
 	status:"true"
 }
 
 var result_delete_mblog = {
 	status:"false",
 	error:"参数错误"
 }
 
 /**
  * url:/client/mblog/addcomment
  * @type 
  */
 var add_comment = {
 	id:1232,
 	content:"i like this weibo!"
 }
 
 var result_add_comment = {
 	status:"true"
 }
 
 var result_add_comment = {
 	status:"false",
 	error:"这里显示具体出错原因，也许评论的东西不存在，也许api参数错误"
 }
 //==========================================================
 /**
  * 发布文章
  * 注意：1 文章分为自己可见的和他人可见的，当然你可以无视这个功能
  * 	 2 发布文章中包含分享文章，分享的文章可以写一下你分享的理由,分享的文章的title里放分享的理由，而content为空
  * 	 3 首次发布的文章的更新时间是发布时间，而对文章做更改时，就会改成最近一次的修改时间
  * 	 4 文章中默认有一个uncategory分类，当然用户可以删除这个分类
  * 	 5 删除分类会把分类所包含的文章一同删除
  * 
  * url:/client/article/add
  * @type 
  */
 var pulish_article = {//发布原创文章
 	visible:true,//是否对他人可见
 	content:"it is a long article, it is a long article it is a long article ......",
 	title:"My first article",
 	category_name:"dairy"//分类处用name，这是这个name是唯一的，如果不方便，也可以用id，it depends on you!
 	
 }
 
 var result_publish_article = {
 	status:"true"
 }
 
 var result_publish_article = {
 	status:"false",
 	error:"参数错误"
 }
 
 var publish_article = {//分享别人的文章
 	visible:true,//如果这里为false，文章是收藏；如果为true，则为分享。有点巧妙，如果客户端并未提供此功能，那就设为true
 	title:"this article is so good"，//这里填写你分享的理由
 	category_name:"myOwnCategory",//你可以设定分享的文章在哪个分类里面
 	shareArticleId:12312//分享的文章的id
 }
 
 /**
  * url: /client/category/add
  * @type 
  */
 var add_category = {
 	name:"technie"
 }
 
 var result_add_category = {
 	status:"true"
 }
 
 var result_add_category = {
 	status:"false",
 	error:"参数错误"
 }
 
 /**
  * url:/client/category/delete
  * @type 
  */
 var delete_category = {
 	name:"technie"
 }
 
 /**
  * 删除文章
  * 注意：会判断是否是你的文章
  * url:/client/article/delete
  * @type 
  */
 var delete_article = {
 	id:12312
 }
 
 var result_delete_article = {
 	status:"true"//删除成功
 }
 
 var resulat_delete_articel = {
 	status:"false",
 	error:"参数错误"//可能不存在这个id，也有可能并不是你的文章
 }
 
 /**
  * 获取的文章排列：
  * 文章1（最旧的）    |||||||||||||
  * 文章2             | start:11
  * 文章3             | end:15
  * 文章4             |
  * 文章5             |||||||||||||
  * 文章6             |||||||||||||
  * 文章7             | start:6
  * 文章8             | end:10
  * 文章9             |
  * 文章10            ||||||||||||
  * 文章11            ||||||||||||
  * 文章12            | start:1
  * 文章13            | end:5
  * 文章14            |
  * 文章15（最新的）    |||||||||||||
  * @type5 
  */
 var get_article_list = {
 	start:0,
 	end:20//获取最新的20篇文章，
 }
 /**
  * 注意：1.这里获取的文章列表，并不会把所有的文章内容都显示出来，只显示提要，也就是文章的前50字
  * 	 2.如果文章是分享的，则有shareArticleId，指向的是分享的文章的id，如果不是分享的，则没有。如果客户端去掉此功能，则忽略
  * @type 
  */
 var result_get_article_list = {
 	articles:[{id:123,title:"my holiday",createDate:"",updateDate:"",publisherId:123,publisherName:"ljj",content:"... about 50 words... ",shareArticleId:232},
 	          {}]
 }
 
 var get_article_detail = {
 	
 }
 
 var result_get_article_detail = {
 	id:123,
 	title:"",
 	content:"",
 	createDate:"",
 	publisherId:"",
 	updateDate:"",
 	categoryName:"",
 	comments:[]
 }
 
 var change_article_detail = {
 	
 }
 
 //==========================================================
 /**
  * 聊天的api
  * 注意：1.聊天的内容就是悄悄话，只不过把悄悄话变成即时而已，如果该人没有上线，可以给他发送悄悄话，
  *        如果该人上线了，那个人就会即时接收，并且可能回复到你的悄悄话中，然后你也论询悄悄话中有没有人给你信息
  *      2.事实上，要论询的东西有很多，包括即时的消息，包括公告版，这些论询并不打算放在对话轮询中，而是用另外的api
  *        可以设置那些api的论询时间长达1分钟或者5分钟，当然上线时第一时间就先论询一遍
  *      3.聊天过程中，可能出现一个人把话发给对方了，然后对方没收到就下线了，这样，系统已经保存了对方的发话，
  *        却没有机会展示给接收者看，所以建议在上线时论询一次有没有人和你说过话，就像上qq时，会拼命弹出了聊天内容，
  *        其实就是上次聊天时你还没就收的内容
  *      
  * @type 
  */
 /**
  * url:/client/letter/add
  * @type 
  */
 var send_content = {
 	otherId:12323,
 	content:"hi,i am mary"
 }
 
 var result_send_content = {
 	status:"true"
 }//这里只表明收到

 /**
  * url:/client/letter/getall
  * @type 
  */
 var receive_content ={
 	
 }
 
 var result_receive_content = {//论询api，论询间隔时间策略由客户端定义
 	messages:[
 				{senderId:12313,
 				 name:"ljj",
 				 content:"yes i know you",
 				 createDate:""}//createDate 是用来显示服务器接收到的时间，让用户知道发送这实际发送这条消息的时间
 				 //...
 				
 	]
 }
 
 /**
  * 只获取一个人的聊天内容
  * url :/client/letter/getone
  * @type 
  */
 var receive_one_content = {
 	otherID:1234
 }
 
 var result_receive_one_content = {
 	name:"ljj",
 	messages:[{content:"",createDate:""}]
 }
 
 /**
  * 这个获取最近的聊天记录，包括你看过和没看过的
  * @type 
  */
 var get_latest_content = {
 	otherId:12312,
 	start:0,
 	end:19//获取最新的20条聊天记录
 }
 
 var result_latest_content = {
 	name:"ljj",
 	contents:[
 	{content:"",createDate:"",speakerId:123,receiverId:234},//本人说的为真，对方说的为假
 	{},
 	{}
 	//more ...
 	]
 }
 
 /**
  * url：/client/user/getOnline
  * @type 
  */
 var get_online_user = {
 	
 }
 
 var result_get_online_user = {
 	users:[{name:ljj,
 			id:3243,
 			role:"stuent",
 			loginPlace:"仙林"},//如果登录用户不知丁，则显示unknown
 		   {},
 		   {}]
 }
 
 //============================================================
 //======================文件操作===============================
 /**
  * 注意：1.文件上传时，要用post把文件post出去，其中参数是file，值是文件内容，可以使用多个（目前不支持）
  * 	 2.文件描述功能取消 
  * url:/client/file/save/{fileName}
  * @type 
  */
 var file_upload = {//已取消
 	
 }
 
 var result_file_upload = {
 	status:"true"
 }
 
 var result_file_upload = {
 	status:"false",
 	error:"错误说明"
 }
 /**
  * 注意：1.下载地址只给出相对地址，要在前面加上http://2334.34.343.34
  * 	 2.下载直接用get方法即可
  * url : /client/file/getAll
  * @type 
  */
 var get_file_list = {
 	files:[{id:12132,name:"exampe.txt",createDate:"",url:"/upload/12/2011-05/example.txt",description:"无",publisheId:123,publisherName:"ljj"},
 			{}
 			// more ...]
 }
 
 /**
  * url /client/file/delete/{fileId}
  * @type 
  */
 var delete_file = {
 	id:1232
 }
 
 var result_delete_file = {
 	status:"true"
 }
 
 var result_delete_file = {
 	status:"false",
 	error:"该文件不属于你"
 }
 //===========================================================
 //==========================用户相关操作=======================
 /**
  * url:/client/user/getAll
  * @type 
  */
 var get_user_list = {
 	studentList:[
 				{id:123,realName:"ljj"},
 				{id:123,realName:"lhh"}
 				//more ... 
 	],
 	teacherList:[
 				{id:123,realName:"ljj"},
 				{id:123,realName:"lhh"}
 				//more ...
 	],
 	counsellorList:[
 				{id:123,realName:"ljj"},
 				{id:123,realName:"lhh"}
 				//more ...
 	],
 	adminList:[
 				{id:123,realName:"ljj"},
 				{id:123,realName:"lhh"}
 				//more ...
 	],
 	leaderList:[
 				{id:123,realName:"ljj"},
 				{id:123,realName:"lhh"}
 				//more ...
 	]
 	
 }
 //===========================================================
 //================相册相关操作=================================
 /**
  * 注意：1，相册没有描述，相册的名称就是对相册的描述
  * 	 2，相册的名字必须是唯一的
  * @type 
  */
var add_album = {
	name:"Friend"
}

var result_add_album = {
	status:"true"
}

var result_add_album = {
	error:"相册名重复",
	status:"false"
}

/**
 * url:/client/album/delete
 * @type 
 */
var delete_album = {
	albumName:"friend"
}

var result_delete_album = {
	status:"true"
}

var result_delete_album = {
	status:"false",
	error:"不存在该相册"
}

/**
 * url:/client/album/rename
 * @type 
 */
var album_rename = {
	oldName:"frend",
	newName:"dafdsa"
}

var result_album_rename = {
	status:"true"
}

var result_album_rename = {
	status:"false",
	error:"相册名重复"
}

/**
 * url:/client/album/share
 * @type 
 */
var share_album = {
	id:123,
	shareReason:"good thing!"
}

var result_share_album = {
	status:"true"
}

var result_share_album = {
	status:"false",
	error:""
}

/**
 * 注意：1 相册没有名字，相册的名字就是相册的描述
 * url:/client/picture/upload/{albumName}/{descrip}
 * @type 
 */
var result_add_picture = {
	
}

var result_add_picture = {
	
}

var delete_picture = {
	
}

var result_delete_picture = {
	
}

var result_delete_picture = {
	
}

/**
 * url:/client/album/getMy
 * @type 
 */
var get_my_album_list = {
	
}

var result_get_my_album_list = {
	albums:[{id:123,name:"",createDate:"",cover:"url的相对地址"},
			{id:123,name:"",createDate:"",cover:"url的相对地址"}
			//...
			]
}

/**
 * url:/client/album/get
 * @type 
 */
var get_album_list = {
	userId:123	
}

var result_get_album_list = {
	albums:[{id:123,name:"",createDate:"",cover:"url的相对地址"},
			{id:123,name:"",createDate:"",cover:"url的相对地址"}
			//...
			]
}

/**
 * url:/client/album/detail
 * @type 
 */
var get_album_detail = {
	id:123
}

var result_album_detail = {
	name:"",
	ownerId:123,
	createDate:"",
	cover:"",
	pictures:[{id:123,description:"",url:"",createDate:""},
			  {}]
}

/**
 * url:/client/picture/detail
 * @type 
 */
var get_picture_detail = {
	id:123
}

var result_get_picture_detail = {
	description:"",
	url:"",
	createDate:"",
	comments:[]
}




 //===========================================================
 //============通知相关操作=====================================
/**
 * 注意：1.只有老师或辅导员才有发通知的权利，学生只能接收，老师，辅导员之间可以相互发通知
 * 		
 * @type 
 */
var publisth_message_tosome = {
	receivers:[{id:123},
		       {id:124}
		       
		       //more
		       
		       ],
	title:"关于放寒假的通知",
	content:"寒假将要到来，清同学注意。。。"
}

var result_publisth_message_tosome = {
	status:"true"
}

var result_publisth_message_tosome = {
	status:"false",
	error:"..."
}

var publisher_message_tostudent = {//把消息发送给所有学生
	
}

var result_publisher_message_tostudent = {
	status:"true"
}

var result_publisher_message_tostudent = {
	status:"false",
	error:"..."
}

var  publisher_message_group = {//把消息发送给我的某个分组的所有人，要等个人信息的api完成才能写
	
}

var result_publisher_message_group = {
	status:"true"
}

var result_publisher_message_group = {
	status:"false",
	error:"..."
}

var get_my_publish_message_list = {
	
}

var result_get_my_publish_message_list = {
	messages:[{id:123,title:""},//这只是个预览而已，要看详细的信息就要点进去，获取detail
			  {id:123,title:""}
	]
}

var get_my_publish_message_detail = {//你发布的message能获得更多的detail
	id:123
}

var result_get_my_publish_message_detail = {//你发布的message能获得更多的detail
	title:"",
	content:"",
	receivers:[{id:123,name:"梁建均",readed:true},
				{id:133,name:"李浩寰",readed:false}
	]
}
