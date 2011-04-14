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
 * @author leung jianjun
 */
 
//==========================================================
 /**
  * url:client/user/login
  * @type 
  */
 var login = {
 	loginName:"ljj09",
 	password:"123",
 	loginPlace:"xianlin"
 }
 
 var result = {
 	status:ok,
 	sessionId:"dsafdsafdsafdsafd"
 }
 
 var error = {
 	error :"参数错误"
 }
 //==========================================================
 
 //==========================================================