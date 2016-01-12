package it.xpug.todolist;

import java.io.*;

import javax.servlet.http.*;

public class ShowNotFound extends Command {

	private HttpServletResponse response;

	public ShowNotFound() {

	}
	public ShowNotFound(HttpServletResponse response) {
		this.response = response;
    }

	public void service() throws IOException {
		response.setStatus(404);
		response.getWriter().write("Not found");
    }

	public void service(WebRequest webRequest, HttpServletResponse response) throws IOException {
		response.setStatus(404);
		response.getWriter().write("Not found");
    }

}
