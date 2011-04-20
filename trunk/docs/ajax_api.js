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
 * update1：
 * 整个文件格式定义 和 login api的添加
 * 曾加发微博的api
 * 注意中 增加第7条
 * ------------------------------------------
 * @author leung jianjun
 */
 
//==========================================================
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
 	loginPlace:"xianlin",
 	role:""//
 }
 
 var result = {
 	status:"true",
 	sessionId:"dsafdsafdsafdsafd",
 	role:"student"
 	
 }
 
 var error = {
 	status:"error",
 	error :"参数错误"
 }
 //==========================================================
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
 	status:"true"
 }
 
 /**
  * url:client/mblog/delete
  * @type 
  */
 var delete_mblog = {
 	id:34331232
 }
 
 /**
  * 获取某个状态的具体信息
  * 
  * @type 
  */
 var ask_get_mblog = {
 	id:123
 }
 
 var get_mblog = {
 	id:1324, 
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
 		                           ]
 }
 
 /**
  * 获取所有人的状态
  * 注意：一个状态可能被转发了n多次，所以mblog下会有flwMBlog，flwMBlog下还可能有flwBlog，
  *      然后组合成一个长状态，所以要判别直到某个flwMBlog没有flwMBlog为止
  *      what a hot mblog @lhh I don't think so @qc the same to me @ljj I hate history class
  *     一个状态的会有一个评论列表，而每一个评论下都会有子评论列表，每个子评论还会有子评论...
  *  url:/client/mblog/getAll
  * @type 
  */
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
 //==========================================================