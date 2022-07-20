<%@ page import="java.util.*" %>
<html>
<body>
	<h2>Welcome to my GAME</h2>
	<div><%= request.getHeader("User-Agent") %></div>
	<div><% int times = Integer.parseInt(request.getParameter("a")); 
		for(int j=0;j<times;j++) { %>
			<div>I will not pull Jenny by her ponytail </div>
		<% } %> 
	</div>
	<div>
    <% Enumeration eNames = request.getHeaderNames();
        while (eNames.hasMoreElements()) {
            String name = (String) eNames.nextElement();
            String value = request.getHeader(name); %>
            <%= name %>
            <%= value %><br/>
    <%

        }
    %>
	</div>
</body>
</html>
