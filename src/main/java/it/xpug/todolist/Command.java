package it.xpug.todolist;

import java.io.*;

import javax.servlet.http.*;

public abstract class Command {
	public abstract void service(WebRequest webRequest, HttpServletResponse response) throws IOException;
}
