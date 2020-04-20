<html>
<head>
<title>Apache Tomcat/7.0.86 - Error report</title>
<style>
<!--
H1 {
	font-family: Tahoma, Arial, sans-serif;
	color: white;
	background-color: #525D76;
	font-size: 22px;
}

H2 {
	font-family: Tahoma, Arial, sans-serif;
	color: white;
	background-color: #525D76;
	font-size: 16px;
}

H3 {
	font-family: Tahoma, Arial, sans-serif;
	color: white;
	background-color: #525D76;
	font-size: 14px;
}

BODY {
	font-family: Tahoma, Arial, sans-serif;
	color: black;
	background-color: white;
}

B {
	font-family: Tahoma, Arial, sans-serif;
	color: white;
	background-color: #525D76;
}

P {
	font-family: Tahoma, Arial, sans-serif;
	background: white;
	color: black;
	font-size: 12px;
}

A {
	color: black;
}

A.name {
	color: black;
}

HR {
	color: #525D76;
}
-->
</style>
</head>
<body>
	<h1>HTTP Status 500 - java.lang.NoSuchMethodException:
		cn.gaowenbonum1.action.UserServlet.execute(javax.servlet.http.HttpServletRequest,
		javax.servlet.http.HttpServletResponse)</h1>
	<HR size="1" noshade="noshade">
	<p>
		<b>type</b> Exception report
	</p>
	<p>
		<b>message</b> <u>java.lang.NoSuchMethodException:
			cn.gaowenbonum1.action.UserServlet.execute(javax.servlet.http.HttpServletRequest,
			javax.servlet.http.HttpServletResponse)</u>
	</p>
	<p>
		<b>description</b> <u>The server encountered an internal error
			that prevented it from fulfilling this request.</u>
	</p>
	<p>
		<b>exception</b>
	<pre>javax.servlet.ServletException: java.lang.NoSuchMethodException: cn.gaowenbonum1.action.UserServlet.execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	cn.gaowenbonum1.utils.BaseServlet.service(BaseServlet.java:42)
	javax.servlet.http.HttpServlet.service(HttpServlet.java:731)
	org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:52)
</pre>
	</p>
	<p>
		<b>root cause</b>
	<pre>java.lang.NoSuchMethodException: cn.gaowenbonum1.action.UserServlet.execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	java.base/java.lang.Class.getMethod(Class.java:2065)
	cn.gaowenbonum1.utils.BaseServlet.service(BaseServlet.java:33)
	javax.servlet.http.HttpServlet.service(HttpServlet.java:731)
	org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:52)
</pre>
	</p>
	<p>
		<b>note</b> <u>The full stack trace of the root cause is available
			in the Apache Tomcat/7.0.86 logs.</u>
	</p>
	<HR size="1" noshade="noshade">
	<h3>Apache Tomcat/7.0.86</h3>
</body>
</html>