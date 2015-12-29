package it.xpug.todolist;

import java.util.regex.*;

import javax.servlet.http.*;

public class WebRequest {

	private HttpServletRequest httpServletRequest;
	private Matcher matcher;

	public WebRequest(HttpServletRequest anHttpServletRequest) {
		this.httpServletRequest = anHttpServletRequest;
    }

	public boolean matches(String regex) {
		matcher = Pattern.compile(regex).matcher(httpServletRequest.getRequestURI());
		return matcher.matches();
    }

	public String getUriParameter(int index) {
	    return matcher.group(index);
    }

	public int getUriParameterAsInteger(int index) {
	    return Integer.valueOf(getUriParameter(index));
    }

}
